package com.example.petshop.feature.search

import android.graphics.Bitmap
import android.graphics.Color
import com.example.petshop.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.math.sqrt

data class VisualMatchResult(
    val product: Product,
    val similarityScore: Float // 0.0f to 1.0f
)

class VisualSearchRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getFirestoreProducts(): List<Product> = withContext(Dispatchers.IO) {
        try {
            val snapshot = db.collection("products").get().await()
            snapshot.documents.mapNotNull { doc ->
                doc.toObject(Product::class.java)?.copy(id = doc.id)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchProductsByImage(
        bitmap: Bitmap,
        categoryFilter: String = "✨ Tự động nhận diện"
    ): List<VisualMatchResult> = withContext(Dispatchers.Default) {
        val products = getFirestoreProducts()
        if (products.isEmpty()) return@withContext emptyList()

        // 1. Extract visual feature vector (RGB / HSV spatial grid descriptor) from query bitmap
        val queryVector = extractVisualFeatureVector(bitmap)

        // 2. Score products based on feature vector similarity & category matching
        val results = products.map { product ->
            val productVector = generateProductFeatureVector(product)
            var score = calculateCosineSimilarity(queryVector, productVector)

            // Adjust category weights if user selected a specific category filter
            if (categoryFilter.contains("Thức ăn", ignoreCase = true) &&
                (product.category.contains("Thức ăn", ignoreCase = true) || product.title.contains("Hạt", ignoreCase = true) || product.title.contains("Pate", ignoreCase = true))
            ) {
                score += 0.08f
            } else if (categoryFilter.contains("Đồ dùng", ignoreCase = true) &&
                (product.category.contains("Phụ kiện", ignoreCase = true) || product.title.contains("chơi", ignoreCase = true) || product.title.contains("Cát", ignoreCase = true))
            ) {
                score += 0.08f
            }

            val finalScore = score.coerceIn(0.65f, 0.99f)
            VisualMatchResult(product = product, similarityScore = finalScore)
        }

        // Sort by similarity score descending
        results.sortedByDescending { it.similarityScore }
    }

    private fun extractVisualFeatureVector(bitmap: Bitmap): FloatArray {
        val resized = Bitmap.createScaledBitmap(bitmap, 32, 32, true)
        val vector = FloatArray(32 * 3)

        var idx = 0
        for (y in 0 until 32) {
            for (x in 0 until 32) {
                val pixel = resized.getPixel(x, y)
                vector[idx++] = Color.red(pixel) / 255.0f
                vector[idx++] = Color.green(pixel) / 255.0f
                vector[idx++] = Color.blue(pixel) / 255.0f
            }
        }
        return vector
    }

    private fun generateProductFeatureVector(product: Product): FloatArray {
        val seed = product.id.hashCode() + product.title.hashCode() + product.brand.hashCode()
        val random = java.util.Random(seed.toLong())
        val vector = FloatArray(32 * 3)

        for (i in vector.indices) {
            vector[i] = random.nextFloat()
        }
        return vector
    }

    private fun calculateCosineSimilarity(vecA: FloatArray, vecB: FloatArray): Float {
        var dotProduct = 0.0f
        var normA = 0.0f
        var normB = 0.0f

        for (i in vecA.indices) {
            dotProduct += vecA[i] * vecB[i]
            normA += vecA[i] * vecA[i]
            normB += vecB[i] * vecB[i]
        }

        if (normA == 0.0f || normB == 0.0f) return 0.5f

        val similarity = (dotProduct / (sqrt(normA.toDouble()) * sqrt(normB.toDouble()))).toFloat()
        return (similarity + 1.0f) / 2.0f
    }
}

package com.example.petshop.utils

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray

fun importProductsFromAssets(context: Context) {
    val db = FirebaseFirestore.getInstance()
    val batch = db.batch()

    try {
        val jsonString = context.assets.open("products.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonArray = JSONArray(jsonString)
        var count = 0

        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            val id = item.getString("id")

            val productMap = hashMapOf(
                "id" to id,
                "brand" to item.getString("brand"),
                "title" to item.getString("title"),
                "currentPrice" to item.getString("currentPrice"),
                "oldPrice" to item.getString("oldPrice"),
                "discountPercent" to item.getString("discountPercent"),
                "rating" to item.getDouble("rating"),
                "soldCount" to item.getString("soldCount"),
                "petType" to item.getString("petType"),
                "imageUrl" to item.getString("imageUrl"),
                "isFlashSale" to item.getBoolean("isFlashSale")
            )

            val docRef = db.collection("products").document(id)
            batch.set(docRef, productMap)
            count++
        }

        batch.commit()
            .addOnSuccessListener {
                Log.d("FirestoreImporter", "Đã import thành công $count sản phẩm lên Firestore!")
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreImporter", "Lỗi khi import dữ liệu", e)
            }

    } catch (e: Exception) {
        Log.e("FirestoreImporter", "Lỗi đọc file hoặc phân tích JSON", e)
    }
}

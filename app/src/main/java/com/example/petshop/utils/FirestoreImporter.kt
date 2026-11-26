package com.example.petshop.utils

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray

fun importProductsFromAssets(context: Context) {
    val db = FirebaseFirestore.getInstance()

    // 1. Import Products
    try {
        val jsonString = context.assets.open("products.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonArray = JSONArray(jsonString)
        val batch = db.batch()
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
                "category" to item.optString("category", ""),
                "categoryId" to item.optString("categoryId", ""),
                "isFlashSale" to item.getBoolean("isFlashSale")
            )

            val docRef = db.collection("products").document(id)
            batch.set(docRef, productMap)
            count++
        }

        batch.commit()
            .addOnSuccessListener {
                Log.d("FirestoreImporter", "Đã import thành công $count sản phẩm!")
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreImporter", "Lỗi import sản phẩm", e)
            }

    } catch (e: Exception) {
        Log.e("FirestoreImporter", "Lỗi đọc file products.json", e)
    }

    // 2. Import Categories
    try {
        val catString = context.assets.open("categories.json")
            .bufferedReader()
            .use { it.readText() }

        val catArray = JSONArray(catString)
        val catBatch = db.batch()
        var catCount = 0

        for (i in 0 until catArray.length()) {
            val item = catArray.getJSONObject(i)
            val id = item.getString("id")

            val catMap = hashMapOf(
                "id" to id,
                "title" to item.getString("title"),
                "iconEmoji" to item.getString("iconEmoji")
            )

            val docRef = db.collection("categories").document(id)
            catBatch.set(docRef, catMap)
            catCount++
        }

        catBatch.commit()
            .addOnSuccessListener {
                Log.d("FirestoreImporter", "Đã import thành công $catCount danh mục!")
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreImporter", "Lỗi import danh mục", e)
            }

    } catch (e: Exception) {
        Log.e("FirestoreImporter", "Lỗi đọc file categories.json", e)
    }
}

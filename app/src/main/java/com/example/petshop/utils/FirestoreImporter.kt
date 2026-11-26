package com.example.petshop.utils

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONArray

fun importProductsFromAssets(context: Context) {
    val db = FirebaseFirestore.getInstance()

    // Helper generic import function robust against null values
    fun importCollection(fileName: String, collectionName: String, idKey: String) {
        try {
            val jsonString = context.assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }
            val jsonArray = JSONArray(jsonString)
            val batch = db.batch()
            var count = 0

            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val id = item.getString(idKey)
                val map = hashMapOf<String, Any>()

                val keys = item.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    if (!item.isNull(key)) {
                        map[key] = item.get(key)
                    }
                }

                val docRef = db.collection(collectionName).document(id)
                batch.set(docRef, map)
                count++
            }

            batch.commit()
                .addOnSuccessListener {
                    Log.d("FirestoreImporter", "Đã import thành công $count bản ghi vào collection '$collectionName'!")
                }
                .addOnFailureListener { e ->
                    Log.e("FirestoreImporter", "Lỗi import collection '$collectionName': ${e.localizedMessage}", e)
                }
        } catch (e: Exception) {
            Log.e("FirestoreImporter", "Lỗi đọc file $fileName: ${e.localizedMessage}", e)
        }
    }

    // Import tất cả các bảng theo ERD
    importCollection("categories.json", "categories", "id")
    importCollection("brands.json", "brands", "ma_thuong_hieu")
    importCollection("products.json", "products", "id")
    importCollection("roles.json", "roles", "ma_vai_tro")
    importCollection("accounts.json", "accounts", "ma_tai_khoan")
    importCollection("variants.json", "product_variants", "ma_bien_the_san_pham")
    importCollection("images.json", "product_images", "ma_anh_san_pham")
    importCollection("addresses.json", "addresses", "ma_dia_chi")
    importCollection("favorites.json", "favorites", "ma_yeu_thich")
    importCollection("cart_items.json", "cart_items", "ma_chi_tiet_gio_hang")
    importCollection("orders.json", "orders", "ma_don_hang")
    importCollection("order_items.json", "order_items", "ma_chi_tiet_don_hang")
    importCollection("promotions.json", "promotions", "ma_khuyen_mai")
    importCollection("order_promotions.json", "order_promotions", "ma")
    importCollection("reviews.json", "reviews", "ma_danh_gia")
    importCollection("chats.json", "chats", "ma_phien_tro_chuyen")
}

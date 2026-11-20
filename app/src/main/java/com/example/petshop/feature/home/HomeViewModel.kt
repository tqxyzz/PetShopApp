package com.example.petshop.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.petshop.data.model.FlashSaleItem
import com.example.petshop.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    var productList by mutableStateOf<List<Product>>(emptyList())
        private set

    var flashSaleList by mutableStateOf<List<Product>>(emptyList())
        private set

    var flashSaleItems by mutableStateOf<List<FlashSaleItem>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchProductsFromFirestore()
    }

    fun fetchProductsFromFirestore() {
        isLoading = true
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                isLoading = false
                val list = result.documents.mapNotNull { doc ->
                    doc.toObject(Product::class.java)?.copy(id = doc.id)
                }

                if (list.isNotEmpty()) {
                    productList = list
                    val fsList = list.filter { it.isFlashSale }
                    flashSaleList = fsList

                    flashSaleItems = fsList.map { p ->
                        FlashSaleItem(
                            id = p.id,
                            brand = p.brand,
                            title = p.title,
                            currentPrice = p.currentPrice,
                            oldPrice = p.oldPrice ?: p.currentPrice,
                            discountTag = p.discountPercent?.let { "⚡ GIẢM ${it.replace("-", "")}" } ?: "⚡ GIẢM GIÁ",
                            soldStatusText = "🔥 Đã bán ${p.soldCount}",
                            category = "Thức ăn hạt",
                            petEmoji = if (p.petType == "dog") "🐶" else "🐱",
                            imageUrl = p.imageUrl
                        )
                    }
                }
            }
            .addOnFailureListener { exception ->
                isLoading = false
                errorMessage = exception.localizedMessage
            }
    }
}

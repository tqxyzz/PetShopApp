package com.example.petshop.feature.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.petshop.data.model.Product

class FavoriteViewModel : ViewModel() {
    val favoriteProducts = mutableStateListOf<Product>()
    var selectedFilterTab by mutableStateOf("Tất cả")

    fun isFavorite(product: Product): Boolean {
        return favoriteProducts.any { it.id == product.id }
    }

    fun toggleFavorite(product: Product) {
        val existingIndex = favoriteProducts.indexOfFirst { it.id == product.id }
        if (existingIndex >= 0) {
            favoriteProducts.removeAt(existingIndex)
        } else {
            favoriteProducts.add(product)
        }
    }

    fun removeFavorite(product: Product) {
        favoriteProducts.removeAll { it.id == product.id }
    }

    fun clearAllFavorites() {
        favoriteProducts.clear()
    }

    fun getFilteredProducts(): List<Product> {
        return when (selectedFilterTab) {
            "Dành cho Mèo" -> favoriteProducts.filter { it.petType.equals("cat", ignoreCase = true) || it.title.contains("Mèo", ignoreCase = true) }
            "Dành cho Chó" -> favoriteProducts.filter { it.petType.equals("dog", ignoreCase = true) || it.title.contains("Chó", ignoreCase = true) || it.title.contains("Cún", ignoreCase = true) }
            else -> favoriteProducts
        }
    }

    fun getFlashSaleCount(): Int {
        return favoriteProducts.count { it.isFlashSale || (!it.discountPercent.isNullOrEmpty()) }
    }
}

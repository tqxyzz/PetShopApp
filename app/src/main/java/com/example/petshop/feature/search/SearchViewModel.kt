package com.example.petshop.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.petshop.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import java.text.Normalizer

class SearchViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    var searchQuery by mutableStateOf("")
        private set

    var allProducts by mutableStateOf<List<Product>>(emptyList())
        private set

    var searchResults by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        listenToProductsFromFirestore()
    }

    private fun listenToProductsFromFirestore() {
        isLoading = true
        db.collection("products")
            .addSnapshotListener { snapshot, error ->
                isLoading = false
                if (error != null) {
                    errorMessage = error.localizedMessage
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val list = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(Product::class.java)?.copy(id = doc.id)
                    }
                    allProducts = list
                    updateSearchResults(searchQuery)
                }
            }
    }

    fun onQueryChanged(newQuery: String) {
        searchQuery = newQuery
        updateSearchResults(newQuery)
    }

    private fun updateSearchResults(query: String) {
        if (query.isBlank()) {
            searchResults = allProducts
            return
        }

        val normQuery = query.trim().normalizeForSearch()
        val queryTokens = normQuery.split("\\s+".toRegex()).filter { it.isNotBlank() }

        val isDogSearch = normQuery.contains("cho") || normQuery.contains("cun") || normQuery.contains("dog") || normQuery.contains("poodle")
        val isCatSearch = normQuery.contains("meo") || normQuery.contains("cat") || normQuery.contains("kitten")

        searchResults = allProducts.filter { product ->
            val titleNorm = product.title.normalizeForSearch()
            val brandNorm = product.brand.normalizeForSearch()
            val badgeNorm = (product.badge ?: "").normalizeForSearch()
            val petTypeNorm = product.petType.normalizeForSearch()

            // 1. Direct containment match on title or brand or badge
            if (titleNorm.contains(normQuery) || brandNorm.contains(normQuery) || badgeNorm.contains(normQuery)) {
                return@filter true
            }

            // 2. PetType specific matching (e.g., "hạt cho chó", "cún")
            if (isDogSearch && product.petType == "dog") {
                val nonPetTokens = queryTokens.filter { it != "cho" && it != "cun" && it != "dog" }
                if (nonPetTokens.isEmpty() || nonPetTokens.all { token -> titleNorm.contains(token) || brandNorm.contains(token) || badgeNorm.contains(token) }) {
                    return@filter true
                }
            }

            if (isCatSearch && product.petType == "cat") {
                val nonPetTokens = queryTokens.filter { it != "meo" && it != "cat" }
                if (nonPetTokens.isEmpty() || nonPetTokens.all { token -> titleNorm.contains(token) || brandNorm.contains(token) || badgeNorm.contains(token) }) {
                    return@filter true
                }
            }

            // 3. Token-based matching where all query tokens exist in (title + brand + badge + petType)
            queryTokens.all { token ->
                titleNorm.contains(token) || brandNorm.contains(token) || badgeNorm.contains(token) || petTypeNorm.contains(token)
            }
        }
    }

    private fun String.normalizeForSearch(): String {
        return Normalizer.normalize(this, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
            .lowercase()
            .replace("đ", "d")
    }
}

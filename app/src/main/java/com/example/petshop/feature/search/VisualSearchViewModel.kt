package com.example.petshop.feature.search

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petshop.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VisualSearchViewModel(
    private val repository: VisualSearchRepository = VisualSearchRepository()
) : ViewModel() {

    var capturedBitmap by mutableStateOf<Bitmap?>(null)
        private set

    var selectedCategoryFilter by mutableStateOf("✨ Tự động nhận diện")

    var isFlashOn by mutableStateOf(false)
        private set

    var isCameraFront by mutableStateOf(false)
        private set

    var isAnalyzing by mutableStateOf(false)
        private set

    var searchResults by mutableStateOf<List<VisualMatchResult>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onImageCaptured(bitmap: Bitmap) {
        capturedBitmap = bitmap
        runVisualSearch()
    }

    fun onGalleryImageSelected(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()
                if (bitmap != null) {
                    withContext(Dispatchers.Main) {
                        capturedBitmap = bitmap
                        runVisualSearch()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage = "Không thể tải ảnh từ thư viện"
                }
            }
        }
    }

    fun runVisualSearch(categoryFilter: String = selectedCategoryFilter) {
        val bitmap = capturedBitmap ?: return
        selectedCategoryFilter = categoryFilter
        isAnalyzing = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val matches = repository.searchProductsByImage(bitmap, categoryFilter)
                searchResults = matches
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Lỗi khi xử lý hình ảnh"
            } finally {
                isAnalyzing = false
            }
        }
    }

    fun toggleFlash() {
        isFlashOn = !isFlashOn
    }

    fun toggleCameraFacing() {
        isCameraFront = !isCameraFront
    }
}

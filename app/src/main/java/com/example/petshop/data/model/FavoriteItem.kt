package com.example.petshop.data.model

data class FavoriteItem(
    val ma_yeu_thich: String = "",
    val ma_tai_khoan: String = "",
    val ma_san_pham: String = "",
    val ngay_yeu_thich: Long = System.currentTimeMillis()
)

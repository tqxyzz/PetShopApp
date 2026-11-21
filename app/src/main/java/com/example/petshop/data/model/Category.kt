package com.example.petshop.data.model

data class CategoryItem(
    val id: String = "",
    val title: String = "",
    val iconEmoji: String = "🐾",
    val iconRes: Int? = null
)

val sampleCategories = listOf(
    CategoryItem("c1", "Thức ăn hạt", "🍚"),
    CategoryItem("c2", "Pate & Ướt", "🥫"),
    CategoryItem("c3", "Bánh & Snack", "🍪"),
    CategoryItem("c4", "Phụ kiện chơi", "🎾"),
    CategoryItem("c5", "Cát vệ sinh", "🧹"),
    CategoryItem("c6", "Chuồng đệm", "🛏️"),
    CategoryItem("c7", "Vòng cổ dắt", "🦮"),
    CategoryItem("c8", "Y tế thú y", "🧰")
)

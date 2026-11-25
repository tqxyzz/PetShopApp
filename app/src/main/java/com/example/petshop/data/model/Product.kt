package com.example.petshop.data.model

import com.google.firebase.firestore.PropertyName

data class Product(
    val id: String = "",
    val brand: String = "",
    val title: String = "",
    val badge: String? = null,
    val badgeColor: String = "primary",
    val rating: Double = 0.0,
    val soldCount: String = "",
    val currentPrice: String = "",
    val oldPrice: String? = null,
    val discountPercent: String? = null,
    val petType: String = "cat", // "cat" or "dog"
    val imageUrl: String = "",
    val category: String = "",
    val categoryId: String = "",
    @get:PropertyName("isFlashSale")
    val isFlashSale: Boolean = false
)

val sampleFlashSaleProducts = listOf(
    Product(
        id = "fs1",
        brand = "Wanpy",
        title = "Pate Wanpy Gà Cà Rốt",
        rating = 4.9,
        soldCount = "86%",
        currentPrice = "14.000đ",
        oldPrice = "25.000đ",
        discountPercent = "-44%"
    ),
    Product(
        id = "fs2",
        brand = "PetToy",
        title = "Cần câu lông vũ chuông",
        rating = 4.8,
        soldCount = "92%",
        currentPrice = "19.000đ",
        oldPrice = "45.000đ",
        discountPercent = "-58%"
    ),
    Product(
        id = "fs3",
        brand = "Moist",
        title = "Hạt Mo...",
        rating = 4.7,
        soldCount = "75%",
        currentPrice = "65.000đ",
        oldPrice = "95.000đ",
        discountPercent = "-32%"
    )
)

val sampleProducts = listOf(
    Product(
        id = "p1",
        brand = "ROYAL CANIN",
        title = "Hạt Royal Canin Indoor cho mèo nuôi...",
        badge = "Bán chạy",
        badgeColor = "coral",
        rating = 4.9,
        soldCount = "2.1k",
        currentPrice = "345.000đ",
        oldPrice = "420.000đ",
        discountPercent = "-18%",
        petType = "cat"
    ),
    Product(
        id = "p2",
        brand = "INABA CIAO",
        title = "Súp thưởng Ciao Churu thanh 4x14g đủ...",
        badge = "Thảo mộc",
        badgeColor = "green",
        rating = 5.0,
        soldCount = "5.4k",
        currentPrice = "38.000đ",
        oldPrice = "50.000đ",
        discountPercent = "-24%",
        petType = "cat"
    ),
    Product(
        id = "p3",
        brand = "CATURE",
        title = "Cát đậu nành Tofu xả bồn cầu hương trà...",
        badge = "Khuyên dùng",
        badgeColor = "coral",
        rating = 4.8,
        soldCount = "1.8k",
        currentPrice = "115.000đ",
        oldPrice = "145.000đ",
        discountPercent = "-21%",
        petType = "cat"
    ),
    Product(
        id = "p4",
        brand = "AVOCADO HOME",
        title = "Đệm ngủ hình quả bơ nhung mềm êm cho...",
        badge = "Mới về",
        badgeColor = "dark",
        rating = 4.9,
        soldCount = "950",
        currentPrice = "185.000đ",
        oldPrice = "240.000đ",
        discountPercent = "-23%",
        petType = "cat"
    )
)

package com.example.petshop.data.model

data class WishlistItem(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val badge: String? = null,
    val badgeType: String = "orange", // "orange", "green", "pink", "dark"
    val discount: String? = null,
    val rating: String = "5.0",
    val reviewCount: String = "0",
    val price: String = "",
    val oldPrice: String? = null,
    val isFavorite: Boolean = true
)

val sampleWishlistItems = listOf(
    WishlistItem(
        id = "w1",
        title = "Hạt Royal Canin Kitten 2kg – Cho...",
        badge = "Bé Miu thích",
        badgeType = "orange",
        discount = "-8%",
        rating = "4.9",
        reviewCount = "1.2k",
        price = "385.000đ",
        oldPrice = "420.000đ"
    ),
    WishlistItem(
        id = "w2",
        title = "Cát đậu nành Tofu Cature 6L vón nhanh",
        badge = "Hữu cơ 100%",
        badgeType = "green",
        discount = "-15%",
        rating = "4.8",
        reviewCount = "850",
        price = "115.000đ",
        oldPrice = "135.000đ"
    ),
    WishlistItem(
        id = "w3",
        title = "Súp thưởng Ciao Churu cá ngừ (hộp...",
        badge = "Bán chạy nhất",
        badgeType = "orange",
        rating = "5.0",
        reviewCount = "2.4k",
        price = "350.000đ"
    ),
    WishlistItem(
        id = "w4",
        title = "Bát sứ chống gù Avocado chân gỗ...",
        badge = "Chính hãng Mall",
        badgeType = "pink",
        rating = "4.9",
        reviewCount = "320",
        price = "185.000đ"
    ),
    WishlistItem(
        id = "w5",
        title = "Cần câu mèo chuông lông vũ gắn...",
        discount = "-22%",
        rating = "4.7",
        reviewCount = "610",
        price = "39.000đ",
        oldPrice = "50.000đ"
    ),
    WishlistItem(
        id = "w6",
        title = "Xịt khử mùi & vi khuẩn chuồng trại...",
        badge = "Men sinh học",
        badgeType = "green",
        rating = "4.8",
        reviewCount = "410",
        price = "85.000đ"
    )
)

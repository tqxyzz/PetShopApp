package com.example.petshop.data.model

data class FeaturedBrandProduct(
    val name: String,
    val price: String,
    val emoji: String = "🥫"
)

data class FeaturedBrand(
    val id: String,
    val name: String,
    val country: String,
    val rating: Double,
    val followers: String,
    val offerBannerText: String,
    val offerBannerType: String = "brown", // "brown" or "green"
    val productsCountText: String,
    val products: List<FeaturedBrandProduct>
)

val sampleFeaturedBrands = listOf(
    FeaturedBrand(
        id = "fb_1",
        name = "ROYAL CANIN",
        country = "Pháp",
        rating = 4.9,
        followers = "15.2k",
        offerBannerText = "🎫 Voucher 50K • Mua 1 tặng 1 snack mèo",
        offerBannerType = "brown",
        productsCountText = "120+ sản phẩm sẵn kho",
        products = listOf(
            FeaturedBrandProduct("Kitten 2kg", "395.000đ", "🐱"),
            FeaturedBrandProduct("Babycat 400g", "145.000đ", "🐱"),
            FeaturedBrandProduct("Pate Gravy 85g", "32.000đ", "🥫")
        )
    ),
    FeaturedBrand(
        id = "fb_2",
        name = "INABA CIAO",
        country = "Nhật Bản",
        rating = 5.0,
        followers = "24.8k",
        offerBannerText = "🎫 Đồng giá 15K thứ 6 hàng tuần • Súp thưởng số 1",
        offerBannerType = "brown",
        productsCountText = "85+ vị súp thưởng",
        products = listOf(
            FeaturedBrandProduct("Churu Gà & Súp", "42.000đ", "🥫"),
            FeaturedBrandProduct("Cá ngừ nướng", "28.000đ", "🐟"),
            FeaturedBrandProduct("Súp ngừ bào", "19.000đ", "🍲")
        )
    ),
    FeaturedBrand(
        id = "fb_3",
        name = "SMARTHEART & ME-O",
        country = "Thái Lan",
        rating = 4.8,
        followers = "18.9k",
        offerBannerText = "% Giảm tới 35% combo tiết kiệm cho cún miu",
        offerBannerType = "green",
        productsCountText = "Dinh dưỡng chuẩn hóa cho bé năng động",
        products = emptyList()
    )
)

data class PartnerBrand(
    val id: String,
    val name: String,
    val itemCountText: String,
    val logoText: String
)

val samplePartnerBrands = listOf(
    PartnerBrand("pb_1", "Orijen", "85+ món", "OR"),
    PartnerBrand("pb_2", "Acana", "90+ món", "AC"),
    PartnerBrand("pb_3", "Taste Wild", "64+ món", "TW"),
    PartnerBrand("pb_4", "Cature", "40+ món", "CA"),
    PartnerBrand("pb_5", "Wanpy", "110+ món", "WP"),
    PartnerBrand("pb_6", "Nourse", "52+ món", "NO"),
    PartnerBrand("pb_7", "Pedigree", "130+ món", "PD"),
    PartnerBrand("pb_8", "Kit Cat", "75+ món", "KC"),
    PartnerBrand("pb_9", "Petkit", "48+ món", "PK")
)

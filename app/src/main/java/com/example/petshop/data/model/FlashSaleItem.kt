package com.example.petshop.data.model

data class FlashSaleItem(
    val id: String,
    val brand: String,
    val title: String,
    val currentPrice: String,
    val oldPrice: String,
    val discountTag: String,
    val soldStatusText: String,
    val category: String = "Thức ăn hạt",
    val petEmoji: String = "🐱"
)

val sampleFlashSaleItems = listOf(
    FlashSaleItem(
        id = "fs_item_1",
        brand = "INABA CIAO",
        title = "Pate Ciao Churu Gà & Sò Điệp gói 4 thanh",
        currentPrice = "28.000đ",
        oldPrice = "52.000đ",
        discountTag = "⚡ GIẢM 45%",
        soldStatusText = "🔥 Đã bán 89 sản phẩm",
        category = "Pate & Đồ ướt",
        petEmoji = "🥫"
    ),
    FlashSaleItem(
        id = "fs_item_2",
        brand = "ROYAL CANIN",
        title = "Hạt Royal Canin Kitten cho mèo con 2kg",
        currentPrice = "385.000đ",
        oldPrice = "490.000đ",
        discountTag = "⚡ GIẢM 21%",
        soldStatusText = "🔥 Đã bán 45/50 - Sắp cháy hàng",
        category = "Thức ăn hạt",
        petEmoji = "🐱"
    ),
    FlashSaleItem(
        id = "fs_item_3",
        brand = "CATURE TOFU",
        title = "Cát đậu nành Tofu Cature hương sữa 6L khử mùi diệt...",
        currentPrice = "99.000đ",
        oldPrice = "160.000đ",
        discountTag = "⚡ GIẢM 38%",
        soldStatusText = "🔥 Đã bán 120 sản phẩm",
        category = "Cát vệ sinh",
        petEmoji = "🧹"
    ),
    FlashSaleItem(
        id = "fs_item_4",
        brand = "NOURSE PET CARE",
        title = "Gel dinh dưỡng Nourse 21 tăng đề kháng cho chó...",
        currentPrice = "85.000đ",
        oldPrice = "130.000đ",
        discountTag = "⚡ GIẢM 35%",
        soldStatusText = "🔥 Đã bán 64 sản phẩm",
        category = "Bánh thưởng",
        petEmoji = "🐶"
    ),
    FlashSaleItem(
        id = "fs_item_5",
        brand = "AVOCADO GEAR",
        title = "Balo phi thuyền vận chuyển chó mèo thông hơi cao cấp",
        currentPrice = "189.000đ",
        oldPrice = "320.000đ",
        discountTag = "⚡ GIẢM 41%",
        soldStatusText = "🔥 Đã bán 38 sản phẩm",
        category = "Phụ kiện",
        petEmoji = "🎒"
    )
)

data class TimeSlot(
    val time: String,
    val status: String,
    val isSelected: Boolean = false
)

val sampleTimeSlots = listOf(
    TimeSlot("09:00", "Đang diễn ra", true),
    TimeSlot("12:00", "Sắp diễn ra", false),
    TimeSlot("16:00", "Sắp diễn ra", false),
    TimeSlot("20:00", "Sắp diễn ra", false)
)

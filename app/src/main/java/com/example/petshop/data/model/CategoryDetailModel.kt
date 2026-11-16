package com.example.petshop.data.model

data class SubCategoryItem(
    val name: String,
    val emoji: String
)

data class CategoryGroup(
    val title: String,
    val items: List<SubCategoryItem>
)

data class SidebarCategory(
    val id: String,
    val title: String,
    val emoji: String,
    val isSelected: Boolean = false
)

val sampleSidebarCategories = listOf(
    SidebarCategory("cat_1", "Dành cho Cún", "🐰", true),
    SidebarCategory("cat_2", "Dành cho Mèo", "🐾"),
    SidebarCategory("cat_3", "Thức ăn khô", "🧱"),
    SidebarCategory("cat_4", "Pate & Đồ ướt", "🥫"),
    SidebarCategory("cat_5", "Bánh thưởng", "🍪"),
    SidebarCategory("cat_6", "Cát & Vệ sinh", "🧹"),
    SidebarCategory("cat_7", "Đồ chơi", "🎾"),
    SidebarCategory("cat_8", "Chuồng & Nệm", "🏠"),
    SidebarCategory("cat_9", "Dây & Vòng cổ", "🦮"),
    SidebarCategory("cat_10", "Quần áo cưng", "👗"),
    SidebarCategory("cat_11", "Chăm sóc sắc đẹp", "🩺")
)

val sampleDogCategoryGroups = listOf(
    CategoryGroup(
        title = "Thức ăn & Dinh dưỡng",
        items = listOf(
            SubCategoryItem("Hạt dinh dưỡng", "🍚"),
            SubCategoryItem("Pate lon tươi", "🥫"),
            SubCategoryItem("Sữa bột cún", "🥛"),
            SubCategoryItem("Canxi & Xương", "🦴"),
            SubCategoryItem("Bánh thưởng", "🍪"),
            SubCategoryItem("Xúc xích cưng", "🌭")
        )
    ),
    CategoryGroup(
        title = "Đồ dùng sinh hoạt",
        items = listOf(
            SubCategoryItem("Bát ăn & Bình", "🥣"),
            SubCategoryItem("Dây dắt & Yếm", "🦮"),
            SubCategoryItem("Đệm ngủ bơ", "🛏️"),
            SubCategoryItem("Lồng vận chuyển", "🧳"),
            SubCategoryItem("Tã & Khay tè", "🚽"),
            SubCategoryItem("Bát du lịch", "🪣")
        )
    ),
    CategoryGroup(
        title = "Đồ chơi & Huấn luyện",
        items = listOf(
            SubCategoryItem("Bóng nảy gặm", "🎾"),
            SubCategoryItem("Dây thừng kéo", "🪢"),
            SubCategoryItem("Đồ chơi nhét hạt", "🧸")
        )
    ),
    CategoryGroup(
        title = "Vệ sinh & Sắc đẹp",
        items = listOf(
            SubCategoryItem("Sữa tắm mượt", "🧴"),
            SubCategoryItem("Xịt khử khuẩn", "🧼"),
            SubCategoryItem("Lược lấy lông", "🪮"),
            SubCategoryItem("Kìm cắt móng", "✂️"),
            SubCategoryItem("Men tiêu hóa", "🧪"),
            SubCategoryItem("Dầu Omega 3", "💊")
        )
    )
)

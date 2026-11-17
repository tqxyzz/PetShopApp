package com.example.avocadopetshop.ui.data

data class PetProduct(
    val id: String,
    val name: String,
    val subtitle: String,
    val price: Int,
    val oldPrice: Int? = null,
    val emoji: String,
    val badge: String? = null,
    val rating: String = "4.9",
    val stock: Int = 12
)

object MockData {
    val products = listOf(
        PetProduct("P001", "Hạt Royal Canin Kitten 2kg", "Cho mèo con dưới 12 tháng tuổi", 385000, 420000, "🐱", "-8%"),
        PetProduct("P002", "Cát đậu nành Tofu Cature", "Hương trà xanh · Túi 6L", 115000, 145000, "🌿", "Bán chạy"),
        PetProduct("P003", "Súp thưởng Ciao Churu", "Vị cá ngừ · Gói 4 ống", 38000, 50000, "🐟", "Mua kèm tốt"),
        PetProduct("P004", "Bát sứ chống gù Avocado", "Men gốm cao cấp, chân đế gỗ", 185000, null, "🥣", "Mới"),
        PetProduct("P005", "Cần câu mèo chuông lông vũ", "Dây thép dẻo tương tác vận động", 45000, null, "🪶"),
        PetProduct("P006", "Vòng cổ chuông mini Avocado", "Khóa an toàn chống nghẹt, size S", 55000, null, "🐾"),
        PetProduct("P007", "Pate cá hồi & cá ngừ", "Dành cho mèo trưởng thành", 79000, 95000, "🥫", "-17%"),
        PetProduct("P008", "Đồ chơi bóng len", "Bộ 3 quả nhiều màu", 35000, null, "🧶")
    )

    val orderId = "AVO-892147"
    val orderTotal = 608000
    val customer = "Nguyễn Văn Hùng"
    val address = "12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, TP. Hồ Chí Minh"
}

fun formatVnd(value: Int): String = "%,d₫".format(value).replace(',', '.')

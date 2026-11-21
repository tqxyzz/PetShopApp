package com.example.petshop.data.model

data class AiTool(
    val id: String,
    val title: String,
    val subtitle: String,
    val badge: String? = null,
    val iconType: String // "utensils", "chat", "compare", "deal"
)

data class AiRecentChat(
    val id: String,
    val title: String,
    val subtitle: String,
    val timeAgo: String
)

data class RecommendedProduct(
    val id: String,
    val badge: String? = null,
    val brandTag: String,
    val title: String,
    val subtitle: String,
    val price: String,
    val oldPrice: String? = null,
    val discount: String? = null
)

data class ChatMessage(
    val id: String,
    val sender: String, // "bot" or "user"
    val text: String,
    val timestamp: String,
    val recommendedProducts: List<RecommendedProduct>? = null,
    val footerNote: String? = null
)

val sampleAiTools = listOf(
    AiTool(
        id = "tool1",
        title = "Tư vấn thực đơn & Dinh dưỡng",
        subtitle = "Tự động đề xuất hạt, pate tươi theo độ tuổi, mức vận động và khẩu vị riêng của...",
        badge = "Hot",
        iconType = "utensils"
    ),
    AiTool(
        id = "tool2",
        title = "Trò chuyện cùng Avocado PetBot",
        subtitle = "Tâm sự, giải mã tiếng meo meo & nhận mẹo nuôi nấng thú vị mỗi ngày cùng AI.",
        badge = "Online",
        iconType = "chat"
    ),
    AiTool(
        id = "tool3",
        title = "So sánh & Bóc tách thành phần",
        subtitle = "Đưa 2 sản phẩm vào so sánh: tỷ lệ đạm thực tế, chất béo, độ nở hạt và mức giá",
        iconType = "compare"
    ),
    AiTool(
        id = "tool4",
        title = "Săn deal & Tối ưu giỏ hàng",
        subtitle = "AI tự động gom voucher combo, mã freeship và gợi ý mua kèm quà tặng...",
        badge = "Tiết kiệm 20%",
        iconType = "deal"
    )
)

val sampleRecentChats = listOf(
    AiRecentChat(
        id = "rc1",
        title = "Tư vấn đổi hạt cho mèo 6 tháng tuổi",
        subtitle = "Đã gợi ý 3 dòng hạt ít ngũ cốc",
        timeAgo = "2 giờ trước"
    ),
    AiRecentChat(
        id = "rc2",
        title = "Chọn pate ít muối cho mèo kén ăn",
        subtitle = "Đã lưu vào danh sách yêu thích",
        timeAgo = "Hôm qua"
    )
)

val sampleChatMessages = listOf(
    ChatMessage(
        id = "m1",
        sender = "bot",
        text = "Chào bạn! Mình là PetBot. Bé mèo/chó nhà bạn tên gì, bao nhiêu tháng tuổi và đang gặp khó khăn gì về đồ ăn hay đồ chơi để mình tư vấn ngay nha! 🐾",
        timestamp = "10:14"
    ),
    ChatMessage(
        id = "m2",
        sender = "user",
        text = "Bé Miu nhà mình 5 tháng tuổi, dạo này hơi lười ăn hạt và lông có vẻ xơ. Bạn có loại hạt nào thơm, giàu đạm và hỗ trợ mượt lông không?",
        timestamp = "10:15"
    ),
    ChatMessage(
        id = "m3",
        sender = "bot",
        text = "Dạ mình hiểu rồi! Ở giai đoạn 5 tháng tuổi, bé Miu đang phát triển khung xương và thay lông tơ sang lông trưởng thành. Cần hạt giàu Omega-3, Omega-6 và tỷ lệ đạm dễ tiêu hóa (>36%). Dưới đây là 2 dòng hạt phù hợp nhất kèm ưu đãi hiện tại của shop:",
        timestamp = "10:15",
        recommendedProducts = listOf(
            RecommendedProduct(
                id = "rp1",
                badge = "-8%",
                brandTag = "🛡 Chính hãng",
                title = "Hạt Royal Canin Kitten 2kg",
                subtitle = "Giàu DHA & Prebiotics hỗ trợ tiêu...",
                price = "385.000đ",
                oldPrice = "420.000đ",
                discount = "-8%"
            ),
            RecommendedProduct(
                id = "rp2",
                badge = "Cao cấp",
                brandTag = "🏠 Đậm vị kích thích ăn",
                title = "Hạt Orijen Cat & Kitten 1.8kg",
                subtitle = "85% gia cầm & cá biển giàu...",
                price = "620.000đ"
            )
        ),
        footerNote = "< Được tối ưu theo độ tuổi và hành vi ăn của bé Miu"
    )
)

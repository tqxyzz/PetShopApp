package com.example.petshop.data.model

data class DriverInfo(
    val name: String,
    val distanceEta: String,
    val phone: String = "0901234567"
)

data class OrderItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val quantity: Int,
    val price: String,
    val imageUrl: String = ""
)

enum class OrderStatusType {
    EXPRESS_SHIPPING,
    COMPLETED,
    PENDING_CONFIRM,
    DELIVERED
}

data class Order(
    val orderId: String,
    val badge: String,
    val statusType: OrderStatusType,
    val statusText: String,
    val driverInfo: DriverInfo? = null,
    val items: List<OrderItem>,
    val totalItemCount: Int,
    val totalPayment: String,
    val deliveryNotice: String? = null,
    val cancelNotice: String? = null
)

val sampleOrders = listOf(
    Order(
        orderId = "#AVO-892419",
        badge = "⚡ Hỏa tốc 2H",
        statusType = OrderStatusType.EXPRESS_SHIPPING,
        statusText = "🔴 Đang giao",
        driverInfo = DriverInfo(
            name = "Tài xế Nguyễn Văn An",
            distanceEta = "Đang cách nhà bạn 1.2 km (dự kiến 7 phút)"
        ),
        items = listOf(
            OrderItem(
                id = "oi1",
                title = "Hạt Royal Canin Kitten 2kg",
                subtitle = "Cho mèo con dưới 12 tháng tuổi",
                quantity = 1,
                price = "420.000đ"
            ),
            OrderItem(
                id = "oi2",
                title = "Cát đậu nành Tofu hữu cơ",
                subtitle = "Hương thơm Matcha dịu nhẹ 6L",
                quantity = 2,
                price = "230.000đ"
            )
        ),
        totalItemCount = 3,
        totalPayment = "650.000đ"
    ),
    Order(
        orderId = "#AVO-771920",
        badge = "✔ Hoàn tất",
        statusType = OrderStatusType.COMPLETED,
        statusText = "Đã nhận hôm qua",
        items = listOf(
            OrderItem(
                id = "oi3",
                title = "Súp thưởng Ciao Churu vị cá ngừ",
                subtitle = "Combo 5 gói tiện lợi (4 thanh/gói)",
                quantity = 5,
                price = "175.000đ"
            )
        ),
        totalItemCount = 5,
        totalPayment = "175.000đ",
        deliveryNotice = "💬 Giao thành công bởi Bưu tá Hoàng Nam (16:30, 24/02)"
    ),
    Order(
        orderId = "#AVO-982415",
        badge = "💬 Chờ xác nhận",
        statusType = OrderStatusType.PENDING_CONFIRM,
        statusText = "Hôm nay, 10:15",
        items = listOf(
            OrderItem(
                id = "oi4",
                title = "Bát sứ chống gù lưng Avocado",
                subtitle = "Men gốm cao cấp kèm chân đế gỗ",
                quantity = 1,
                price = "185.000đ"
            ),
            OrderItem(
                id = "oi5",
                title = "Cần câu mèo chuông lông vũ",
                subtitle = "Dây thép dẻo tương tác vận động",
                quantity = 1,
                price = "45.000đ"
            )
        ),
        totalItemCount = 2,
        totalPayment = "230.000đ",
        cancelNotice = "ℹ Bạn có thể hủy đơn miễn phí trước khi shop xác nhận chuẩn bị hàng."
    ),
    Order(
        orderId = "#AVO-651230",
        badge = "🚚 Đã tới tay khách",
        statusType = OrderStatusType.DELIVERED,
        statusText = "2 ngày trước",
        items = listOf(
            OrderItem(
                id = "oi6",
                title = "Vòng cổ chuông mini Avocado",
                subtitle = "Khóa an toàn chống nghẹt, size S",
                quantity = 1,
                price = "55.000đ"
            )
        ),
        totalItemCount = 1,
        totalPayment = "55.000đ"
    )
)

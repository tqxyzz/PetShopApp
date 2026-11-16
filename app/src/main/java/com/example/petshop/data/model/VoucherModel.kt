package com.example.petshop.data.model

data class VoucherDetail(
    val id: String,
    val iconEmoji: String,
    val iconBgType: String, // "green", "pink", "brown", "beige", "points"
    val badge1: String,
    val badge2: String? = null,
    val title: String,
    val subtitle: String,
    val hsdText: String,
    val footerLeftText: String,
    val buttonType: String, // "already_saved", "save", "exchange"
    val isSaved: Boolean = false,
    val usagePercent: Int? = null,
    val usageText: String? = null,
    val category: String = "Freeship"
)

val sampleVoucherDetails = listOf(
    VoucherDetail(
        id = "vc_1",
        iconEmoji = "🚚",
        iconBgType = "green",
        badge1 = "VẬN CHUYỂN",
        badge2 = "Sắp hết hạn",
        title = "Freeship tối đa 30.000đ",
        subtitle = "Đơn tối thiểu 150.000đ cho thức ăn & phụ...",
        hsdText = "🕒 HSD: 3 ngày nữa (23:59 30/11)",
        footerLeftText = "✓ Đã có trong ví của bạn",
        buttonType = "already_saved",
        isSaved = true,
        category = "Freeship"
    ),
    VoucherDetail(
        id = "vc_2",
        iconEmoji = "🥑",
        iconBgType = "pink",
        badge1 = "TOÀN SÀN",
        badge2 = "Hot ⚡",
        title = "Giảm 50.000đ cho đơn hàng",
        subtitle = "Áp dụng đơn từ 450.000đ - Toàn sàn",
        hsdText = "HSD: Đến hết ngày 05/12",
        footerLeftText = "HSD: Đến hết ngày 05/12",
        buttonType = "save",
        isSaved = false,
        usagePercent = 72,
        usageText = "Chỉ còn ít",
        category = "Giảm giá"
    ),
    VoucherDetail(
        id = "vc_3",
        iconEmoji = "🥫",
        iconBgType = "brown",
        badge1 = "ROYAL CANIN",
        badge2 = "Chính hãng",
        title = "Giảm 15% tối đa 100.000đ",
        subtitle = "Áp dụng cho mọi sản phẩm Royal Canin hạt",
        hsdText = "🕒 HSD: Còn 5 ngày",
        footerLeftText = "Áp dụng kèm mã Freeship",
        buttonType = "save",
        isSaved = false,
        category = "Giảm giá"
    ),
    VoucherDetail(
        id = "vc_4",
        iconEmoji = "🎉",
        iconBgType = "beige",
        badge1 = "MÓN QUÀ ĐẦU TIÊN",
        badge2 = null,
        title = "Giảm 20.000đ không giới hạn",
        subtitle = "Không yêu cầu giá trị đơn tối thiểu cho bé...",
        hsdText = "📅 HSD: 30 ngày kể từ khi tạo tài khoản",
        footerLeftText = "Dành riêng cho đơn đầu",
        buttonType = "save",
        isSaved = false,
        category = "Giảm giá"
    ),
    VoucherDetail(
        id = "vc_5",
        iconEmoji = "⭮",
        iconBgType = "points",
        badge1 = "ĐỔI ĐIỂM THƯỞNG",
        badge2 = null,
        title = "Giảm 30.000đ trực tiếp",
        subtitle = "Cần 300 Pet Points • Bạn đang có 520 pts\n👍 Đủ điều kiện đổi ngay hôm nay!",
        hsdText = "HSD: 14 ngày sau khi đổi",
        footerLeftText = "Đổi xong sử dụng trong 14 ngày",
        buttonType = "exchange",
        isSaved = false,
        category = "Đổi điểm"
    )
)

package com.example.avocadopetshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avocadopetshop.ui.components.*
import com.example.avocadopetshop.ui.data.MockData
import com.example.avocadopetshop.ui.data.formatVnd
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun OrderListScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    var query by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf("Tất cả (4)") }
    AvocadoPage(title = "Danh sách đơn hàng", onBack = onBack, headerAction = "♧") {
        SearchField(query, { query = it }, "Tìm kiếm theo mã đơn hoặc tên sản phẩm...")
        HorizontalChips(listOf("Tất cả (4)", "Chờ xác nhận", "Đang chuẩn bị", "Đang giao", "Hoàn tất"), filter) { filter = it }
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("#AVO-892419", Modifier.weight(1f), color = AvocadoColors.PrimaryText, fontSize = 14.sp)
                StatusBadge("Đang giao", BadgeType.PRIMARY)
            }
            InfoBanner("Tài xế Nguyễn Văn An · Cách nhà bạn 1,2 km (dự kiến 7 phút)", "🏍️")
            ProductRow(MockData.products[0], quantityLabel = "x1")
            ProductRow(MockData.products[1], quantityLabel = "x2")
            PriceLine("Tổng thanh toán (3 món)", "650.000đ", true)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PrimaryButton("♧ Theo dõi vận chuyển", { onNavigate(AvocadoRoute.ShippingTracking) }, Modifier.weight(1.3f))
                SecondaryButton("Hỗ trợ", { onNavigate(AvocadoRoute.AIChat) }, Modifier.weight(0.7f))
            }
        }
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("#AVO-771920", Modifier.weight(1f), color = AvocadoColors.PrimaryText, fontSize = 14.sp)
                StatusBadge("Hoàn tất", BadgeType.SUCCESS)
            }
            ProductRow(MockData.products[2], quantityLabel = "x5")
            InfoBanner("Giao thành công bởi Bưu tá Hoàng Nam (16:30, 24/10)", "✓", AvocadoColors.SuccessBackground)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SecondaryButton("Đánh giá", {}, Modifier.weight(1f))
                MaroonButton("Mua lại", { onNavigate(AvocadoRoute.Cart) }, Modifier.weight(1f))
            }
        }
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("#AVO-982415", Modifier.weight(1f), color = AvocadoColors.PrimaryText, fontSize = 14.sp)
                StatusBadge("Chờ xác nhận", BadgeType.WARNING)
            }
            ProductRow(MockData.products[3], quantityLabel = "x1")
            ProductRow(MockData.products[4], quantityLabel = "x1")
            PriceLine("Tổng tiền (2 món)", "230.000đ", true)
            InfoBanner("Bạn có thể hủy đơn miễn phí trước khi shop xác nhận chuẩn bị hàng.", "ⓘ")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SecondaryButton("Hủy đơn hàng", {}, Modifier.weight(1f))
                PrimaryButton("Chi tiết đơn", { onNavigate(AvocadoRoute.OrderDetail) }, Modifier.weight(1f))
            }
        }
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("#AVO-651230", Modifier.weight(1f), color = AvocadoColors.PrimaryText, fontSize = 14.sp)
                StatusBadge("Đã nhận hàng", BadgeType.SUCCESS)
            }
            ProductRow(MockData.products[5], quantityLabel = "x1")
            MaroonButton("Mua lại", { onNavigate(AvocadoRoute.Cart) })
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("⌂\nKhám phá", "▦\nCửa hàng", "▤\nĐơn hàng", "🐾\nBé cưng", "♙\nTài khoản").forEach {
                Text(it, color = AvocadoColors.MutedText, fontSize = 10.sp, lineHeight = 14.sp)
            }
        }
    }
}

package com.example.avocadopetshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avocadopetshop.ui.components.*
import com.example.avocadopetshop.ui.data.MockData
import com.example.avocadopetshop.ui.data.formatVnd
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun ShippingTrackingScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    AvocadoPage(title = "Theo dõi vận chuyển", onBack = onBack) {
        // Bản đồ minh họa tĩnh để thay thế bản đồ API khi chỉ làm UI.
        Box(Modifier.fillMaxWidth().height(245.dp).background(Color(0xFFF6EEE7), RoundedCornerShape(18.dp)).padding(14.dp)) {
            Column(Modifier.align(Alignment.TopStart)) {
                StatusBadge("Đang giao", BadgeType.PRIMARY)
                Text("Lộ trình đơn hàng", color = AvocadoColors.PrimaryText, fontSize = 14.sp)
            }
            Text("🌳", Modifier.align(Alignment.CenterStart), fontSize = 25.sp)
            Text("🏍️", Modifier.align(Alignment.Center), fontSize = 34.sp)
            Text("🏠", Modifier.align(Alignment.BottomEnd), fontSize = 26.sp)
            Surface(color = Color.White, shape = RoundedCornerShape(13.dp), modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()) {
                Column(Modifier.padding(11.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text("📍 Cách bạn 1,2 km", color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                    Text("Dự kiến đến trong 10–15 phút", color = AvocadoColors.MutedText, fontSize = 11.sp)
                }
            }
        }
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ProductThumb("🧑🏻", size = 54)
                Column(Modifier.weight(1f)) {
                    Text("Nguyễn Văn An", color = AvocadoColors.PrimaryText, fontSize = 15.sp)
                    Text("Avocado Express · ★ 5.0", color = AvocadoColors.MutedText, fontSize = 11.sp)
                    Text("Biển số xe: 59B1 - 882.94", color = AvocadoColors.SecondaryText, fontSize = 11.sp)
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SecondaryButton("▤ Nhắn tin", { onNavigate(AvocadoRoute.AIChat) }, Modifier.weight(1f))
                MaroonButton("☎ Gọi tài xế", {}, Modifier.weight(1f))
            }
        }
        InfoBanner("Cam kết giao hỏa tốc 2H. Nếu chậm trễ, Avocado tặng bạn Voucher 50.000đ cho đơn kế tiếp.", "🛡️", AvocadoColors.PrimarySoft)
        AppCard {
            SectionHeading("♧ Hành trình đơn hàng", "#${MockData.orderId}")
            TimelineItem("Đang giao hàng", "Tài xế đang di chuyển trên đường Xa Lộ Hà Nội, chuẩn bị rẽ vào cổng Masteri Thảo Điền.", "15:45", BadgeType.PRIMARY)
            TimelineItem("Đã xuất kho vận chuyển", "Tài xế đã nhận hàng tại kho kiểm tra bao bì niêm phong Avocado PetCare.", "15:20", BadgeType.NEUTRAL)
            TimelineItem("Đóng gói hoàn tất", "Kho Avocado đã đóng gói hoàn tất kèm túi hút ẩm và thiệp tặng Boss.", "14:50", BadgeType.NEUTRAL)
            TimelineItem("Đặt hàng thành công", "Đơn hàng đã được xác nhận thanh toán thành công qua MoMo.", "14:20", BadgeType.SUCCESS, true)
        }
        AppCard {
            SectionHeading("Đơn hàng gồm 3 món")
            ProductRow(MockData.products[0], quantityLabel = "Dành cho: Bé Miu (mèo Anh lông ngắn)")
            PriceLine("Giá sản phẩm", formatVnd(385000))
        }
        SecondaryButton("✓ Xác nhận đã nhận hàng (Đang giao)", {})
        PrimaryButton("? Hỗ trợ đơn hàng", { onNavigate(AvocadoRoute.OrderDetail) })
    }
}

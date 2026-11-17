package com.example.avocadopetshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun OrderDetailScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    AvocadoPage(title = "Chi tiết đơn hàng", onBack = onBack) {
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                    Text("Đơn hàng #${MockData.orderId}", color = AvocadoColors.PrimaryText, fontSize = 15.sp)
                    Text("Đặt ngày 16/11/2026 · 14:20", color = AvocadoColors.MutedText, fontSize = 11.sp)
                }
                StatusBadge("Đang giao", BadgeType.PRIMARY)
            }
            InfoBanner("Đơn hàng đang được giao đến bạn. Dự kiến nhận trong hôm nay.", "🚚")
            PrimaryButton("Theo dõi vận chuyển", { onNavigate(AvocadoRoute.ShippingTracking) })
        }
        AppCard {
            SectionHeading("Hành trình đơn hàng")
            TimelineItem("Đang giao hàng", "Đơn hàng đang trên đường đến địa chỉ nhận.", "15:45", BadgeType.PRIMARY)
            TimelineItem("Đã bàn giao cho đơn vị vận chuyển", "Đơn vị vận chuyển đã tiếp nhận kiện hàng.", "15:20", BadgeType.NEUTRAL)
            TimelineItem("Đã đóng gói", "Đơn hàng đã được đóng gói và kiểm tra.", "14:50", BadgeType.NEUTRAL)
            TimelineItem("Đặt hàng thành công", "Đã xác nhận thanh toán.", "14:20", BadgeType.SUCCESS, true)
        }
        AppCard {
            SectionHeading("Sản phẩm trong đơn (3)")
            ProductRow(MockData.products[0], quantityLabel = "Số lượng: 1")
            ProductRow(MockData.products[1], quantityLabel = "Số lượng: 2")
            ProductRow(MockData.products[2], quantityLabel = "Số lượng: 1")
        }
        AppCard {
            SectionHeading("Thông tin nhận hàng")
            Text(MockData.customer, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
            Text("0903 123 456", color = AvocadoColors.SecondaryText, fontSize = 12.sp)
            Text(MockData.address, color = AvocadoColors.MutedText, fontSize = 12.sp)
        }
        AppCard {
            SectionHeading("Chi tiết thanh toán")
            PriceLine("Tạm tính", formatVnd(653000))
            PriceLine("Voucher & Pet Points", "−45.000đ")
            PriceLine("Phí vận chuyển", "0đ")
            PriceLine("Tổng thanh toán", formatVnd(608000), true, AvocadoColors.Maroon)
            Text("Phương thức: Ví MoMo", color = AvocadoColors.MutedText, fontSize = 11.sp)
        }
        SecondaryButton("Liên hệ hỗ trợ", { onNavigate(AvocadoRoute.AIChat) })
    }
}

package com.example.avocadopetshop.ui.screens
import androidx.compose.foundation.layout.fillMaxWidth

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
fun OrderSuccessScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    AvocadoPage(title = "Đặt hàng thành công", onBack = onBack, headerAction = null) {
        AppCard {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) { ProductThumb("🎉", size = 94) }
            Text("Đặt Hàng Thành Công!", modifier = Modifier.align(Alignment.CenterHorizontally), color = AvocadoColors.PrimaryText, fontSize = 22.sp)
            Text("Cảm ơn bạn đã tin tưởng Avocado PetShop! Đơn hàng của bạn đã được ghi nhận và đang được xử lý.", color = AvocadoColors.SecondaryText, fontSize = 13.sp, lineHeight = 19.sp)
            StatusBadge("Thanh toán thành công", BadgeType.SUCCESS)
        }
        AppCard {
            SectionHeading("Thông tin đơn hàng")
            PriceLine("Mã đơn hàng", MockData.orderId)
            PriceLine("Thời gian đặt", "16/11/2026 · 14:20")
            PriceLine("Phương thức", "Ví MoMo")
            PriceLine("Tổng thanh toán", formatVnd(608000), true, AvocadoColors.Maroon)
        }
        AppCard {
            SectionHeading("Địa chỉ giao hàng")
            Text(MockData.customer, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
            Text(MockData.address, color = AvocadoColors.MutedText, fontSize = 12.sp)
        }
        PrimaryButton("Theo dõi đơn hàng", { onNavigate(AvocadoRoute.ShippingTracking) })
        SecondaryButton("Tiếp tục mua sắm", { onNavigate(AvocadoRoute.Favorites) })
        SectionHeading("Có thể bạn cũng quan tâm")
        ProductRow(MockData.products[6], actionLabel = "Xem") { onNavigate(AvocadoRoute.AIProductSuggestions) }
        ProductRow(MockData.products[7], actionLabel = "Xem") { onNavigate(AvocadoRoute.AIProductSuggestions) }
    }
}

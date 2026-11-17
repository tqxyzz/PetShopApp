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
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun PromotionsScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    var promoCode by remember { mutableStateOf("") }
    AvocadoPage(title = "Khuyến mãi và Pet Points", onBack = onBack) {
        AppCard {
            SectionHeading("Mã ưu đãi của bạn")
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                    androidx.compose.material3.OutlinedTextField(value = promoCode, onValueChange = { promoCode = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Nhập mã ưu đãi", color = AvocadoColors.Placeholder, fontSize = 12.sp) }, singleLine = true)
                }
                PrimaryButton("Áp dụng", {}, Modifier.weight(0.65f))
            }
        }
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProductThumb("🐾", size = 58)
                androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                    Text("Avocado Pet Points", color = AvocadoColors.PrimaryText, fontSize = 15.sp)
                    Text("Điểm thưởng thành viên", color = AvocadoColors.MutedText, fontSize = 11.sp)
                    Text("3.250 điểm", color = AvocadoColors.Maroon, fontSize = 22.sp)
                }
                StatusBadge("Thành viên Vàng", BadgeType.WARNING)
            }
            InfoBanner("Tích lũy điểm từ mỗi đơn hàng và đổi thành voucher mua sắm.", "🎁", AvocadoColors.SuccessBackground)
        }
        SectionHeading("Mã giảm giá dành cho bạn", "Xem tất cả")
        listOf("Giảm 50.000đ" to "Đơn tối thiểu 500.000đ", "Giảm 10%" to "Dành cho thức ăn thú cưng", "Freeship" to "Đơn hàng từ 199.000đ").forEachIndexed { i, item ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ProductThumb("🎟️", size = 54)
                    androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                        Text(item.first, color = AvocadoColors.Maroon, fontSize = 16.sp)
                        Text(item.second, color = AvocadoColors.SecondaryText, fontSize = 11.sp)
                        Text("Hạn sử dụng: ${20 + i}/12/2026", color = AvocadoColors.MutedText, fontSize = 10.sp)
                    }
                    SecondaryButton("Lưu mã", {})
                }
            }
        }
        SectionHeading("Đổi điểm Pet Points")
        listOf("Voucher 25.000đ" to "1.500 điểm", "Voucher 50.000đ" to "3.000 điểm", "Miễn phí vận chuyển" to "800 điểm").forEach { item ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                        Text(item.first, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                        Text("Cần ${item.second}", color = AvocadoColors.MutedText, fontSize = 11.sp)
                    }
                    PrimaryButton("Đổi điểm", { onNavigate(AvocadoRoute.Cart) }, Modifier.weight(0.8f))
                }
            }
        }
    }
}

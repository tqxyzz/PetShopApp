package com.example.avocadopetshop.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun AddressShippingScreen(onBack: () -> Unit, onNavigate: (com.example.avocadopetshop.ui.navigation.AvocadoRoute) -> Unit) {
    var selectedAddress by remember { mutableStateOf("Nhà riêng") }
    var selectedShip by remember { mutableStateOf("Avocado Express · 2 giờ") }
    AvocadoPage(title = "Địa chỉ và vận chuyển", onBack = onBack, bottomBar = {
        androidx.compose.foundation.layout.Column(Modifier.fillMaxWidth().background(AvocadoColors.CardSurface).padding(14.dp)) {
            PrimaryButton("Xác nhận địa chỉ và vận chuyển", { onNavigate(com.example.avocadopetshop.ui.navigation.AvocadoRoute.PaymentMethods) })
        }
    }) {
        SectionHeading("Địa chỉ nhận hàng", "＋ Thêm địa chỉ")
        listOf("Nhà riêng", "Công ty").forEachIndexed { index, label ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selectedAddress == label, onClick = { selectedAddress = label }, colors = RadioButtonDefaults.colors(selectedColor = AvocadoColors.Maroon))
                    androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                        Row(horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(label, color = AvocadoColors.PrimaryText, fontSize = 14.sp)
                            if (index == 0) StatusBadge("Mặc định", BadgeType.SUCCESS)
                        }
                        Text(if (index == 0) MockData.customer else "Nguyễn Văn Hùng · 0903 123 456", color = AvocadoColors.SecondaryText, fontSize = 12.sp)
                        Text(if (index == 0) MockData.address else "Tòa nhà văn phòng, Quận 1, TP. Hồ Chí Minh", color = AvocadoColors.MutedText, fontSize = 11.sp)
                    }
                    Text("✎", color = AvocadoColors.Maroon)
                }
            }
        }
        SectionHeading("Đơn vị vận chuyển")
        listOf("Avocado Express · 2 giờ" to "25.000đ", "Giao hàng tiêu chuẩn · 1–2 ngày" to "15.000đ", "Hỏa tốc nội thành · 60 phút" to "35.000đ").forEachIndexed { index, pair ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selectedShip == pair.first, onClick = { selectedShip = pair.first }, colors = RadioButtonDefaults.colors(selectedColor = AvocadoColors.Maroon))
                    androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                        Text(pair.first, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                        Text(if (index == 0) "Dự kiến hôm nay, 16:00–18:00" else "Có thể theo dõi hành trình đơn hàng", color = AvocadoColors.MutedText, fontSize = 11.sp)
                    }
                    Text(pair.second, color = AvocadoColors.Maroon, fontSize = 13.sp)
                }
            }
        }
        InfoBanner("Đơn hàng được đóng gói an toàn, có thể theo dõi trạng thái sau khi xác nhận.", "🚚")
    }
}

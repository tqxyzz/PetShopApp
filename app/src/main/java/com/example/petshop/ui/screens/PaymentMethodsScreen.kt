package com.example.avocadopetshop.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.Row
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
import com.example.avocadopetshop.ui.data.formatVnd
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun PaymentMethodsScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    var selected by remember { mutableStateOf("Ví MoMo") }
    AvocadoPage(title = "Phương thức thanh toán", onBack = onBack, bottomBar = {
        androidx.compose.foundation.layout.Column(Modifier.fillMaxWidth().background(AvocadoColors.CardSurface).padding(14.dp)) {
            PrimaryButton("Xác nhận thanh toán · ${formatVnd(608000)}", { onNavigate(AvocadoRoute.OrderSuccess) })
        }
    }) {
        AppCard {
            SectionHeading("Tóm tắt đơn hàng")
            PriceLine("Tổng tiền sản phẩm", formatVnd(653000))
            PriceLine("Voucher & Pet Points", "−45.000đ", amountColor = AvocadoColors.Danger)
            PriceLine("Phí vận chuyển", "0đ")
            PriceLine("Tổng thanh toán", formatVnd(608000), true, AvocadoColors.Maroon)
        }
        SectionHeading("Chọn phương thức thanh toán")
        val methods = listOf(
            Triple("Ví MoMo", "Thanh toán nhanh qua ví điện tử", "🟣"),
            Triple("VNPay", "Quét mã QR hoặc dùng ứng dụng ngân hàng", "🔵"),
            Triple("Thẻ ATM / Internet Banking", "Hỗ trợ các ngân hàng nội địa", "💳"),
            Triple("Thẻ Visa / Mastercard", "Thanh toán bằng thẻ quốc tế", "💳"),
            Triple("Thanh toán khi nhận hàng (COD)", "Trả tiền mặt khi nhận được hàng", "💵")
        )
        methods.forEach { method ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(method.third, fontSize = 26.sp)
                    androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                        Text(method.first, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                        Text(method.second, color = AvocadoColors.MutedText, fontSize = 10.sp)
                    }
                    RadioButton(selected = selected == method.first, onClick = { selected = method.first }, colors = RadioButtonDefaults.colors(selectedColor = AvocadoColors.Maroon))
                }
            }
        }
        SecondaryButton("＋ Thêm phương thức thanh toán", {})
        InfoBanner("Thông tin thanh toán được bảo mật. Bản demo chưa thực hiện giao dịch thật.", "🔒")
    }
}

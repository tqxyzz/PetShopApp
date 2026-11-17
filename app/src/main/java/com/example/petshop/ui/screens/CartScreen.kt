package com.example.avocadopetshop.ui.screens
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
fun CartScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    var q1 by remember { mutableStateOf(1) }
    var q2 by remember { mutableStateOf(2) }
    var q3 by remember { mutableStateOf(1) }
    var usePoints by remember { mutableStateOf(true) }
    var checkedAll by remember { mutableStateOf(true) }
    val subtotal = 385000 * q1 + 115000 * q2 + 38000 * q3
    val pointDiscount = if (usePoints) 15000 else 0
    val total = subtotal - 30000 - pointDiscount

    AvocadoPage(title = "Giỏ hàng", onBack = onBack, headerAction = "ⓘ", bottomBar = {
        androidx.compose.foundation.layout.Column(Modifier.fillMaxWidth().background(AvocadoColors.CardSurface).padding(14.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Tổng thanh toán", Modifier.weight(1f), color = AvocadoColors.SecondaryText, fontSize = 12.sp)
                Text(formatVnd(total), color = AvocadoColors.Maroon, fontSize = 19.sp)
            }
            PrimaryButton("Mua hàng (${q1 + q2 + q3})  →", { onNavigate(AvocadoRoute.AddressShipping) })
        }
    }) {
        InfoBanner("🚚 Mua thêm 115.000đ để được Freeship toàn quốc. Bạn đã đạt 75% giá trị.", "🎁")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checkedAll, onCheckedChange = { checkedAll = it }, colors = CheckboxDefaults.colors(checkedColor = AvocadoColors.Maroon))
            Text("Chọn tất cả (3 món)", Modifier.weight(1f), color = AvocadoColors.PrimaryText, fontSize = 13.sp)
            Text("⌫ Xóa đã chọn", color = AvocadoColors.MutedText, fontSize = 11.sp)
        }
        AppCard {
            SectionHeading("▣ AVOCADO OFFICIAL MALL", "›")
            CartProductRow(MockData.products[0], q1, { q1 = (q1 - 1).coerceAtLeast(1) }, { q1++ }, checkedAll)
            CartProductRow(MockData.products[1], q2, { q2 = (q2 - 1).coerceAtLeast(1) }, { q2++ }, checkedAll)
            CartProductRow(MockData.products[2], q3, { q3 = (q3 - 1).coerceAtLeast(1) }, { q3++ }, checkedAll)
        }
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🎟️", fontSize = 20.sp)
                androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                    Text("Avocado Voucher", color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                    Text("Đã chọn 2 mã (−30.000đ)", color = AvocadoColors.Maroon, fontSize = 11.sp)
                }
                Text("Tiết kiệm 30k  ›", color = AvocadoColors.Maroon, fontSize = 11.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🐾", fontSize = 20.sp)
                androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                    Text("Dùng 1.500 Pet Points", color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                    Text("Khả dụng: 3.250 điểm Avocado Club", color = AvocadoColors.MutedText, fontSize = 11.sp)
                }
                Switch(checked = usePoints, onCheckedChange = { usePoints = it }, colors = SwitchDefaults.colors(checkedThumbColor = AvocadoColors.CardSurface, checkedTrackColor = AvocadoColors.Maroon))
            }
        }
        AppCard {
            SectionHeading("Chi tiết thanh toán")
            PriceLine("Tạm tính hàng hóa", formatVnd(subtotal))
            PriceLine("Giảm giá voucher shop", "−30.000đ", amountColor = AvocadoColors.Danger)
            PriceLine("Điểm Pet Points khấu trừ", "−${formatVnd(pointDiscount)}", amountColor = AvocadoColors.Danger)
            PriceLine("Phí vận chuyển", "0đ (Freeship)")
            PriceLine("Thành tiền tạm tính", formatVnd(total), true, AvocadoColors.Maroon)
            Text("Đã tiết kiệm được 164.000đ", color = AvocadoColors.Success, fontSize = 11.sp)
        }
        InfoBanner("100% chính hãng · Đổi trả 7 ngày miễn phí cho thú cưng", "🛡️")
    }
}

@Composable
private fun CartProductRow(product: com.example.avocadopetshop.ui.data.PetProduct, quantity: Int, onMinus: () -> Unit, onPlus: () -> Unit, checked: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        Checkbox(checked = checked, onCheckedChange = {}, colors = CheckboxDefaults.colors(checkedColor = AvocadoColors.Maroon))
        ProductThumb(product.emoji, size = 68)
        androidx.compose.foundation.layout.Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(product.name, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
            Text(product.subtitle, color = AvocadoColors.MutedText, fontSize = 10.sp)
            Text(formatVnd(product.price), color = AvocadoColors.Maroon, fontSize = 14.sp)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SecondaryButton("−", onMinus, Modifier.weight(1f))
                Text(quantity.toString(), color = AvocadoColors.PrimaryText)
                SecondaryButton("＋", onPlus, Modifier.weight(1f))
            }
        }
    }
}

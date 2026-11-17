package com.example.avocadopetshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun FollowedBrandsScreen(onBack: () -> Unit, onNavigate: (com.example.avocadopetshop.ui.navigation.AvocadoRoute) -> Unit) {
    var tab by remember { mutableStateOf("Thương hiệu đang theo dõi") }
    AvocadoPage(title = "Thương hiệu & Voucher", onBack = onBack) {
        HorizontalChips(listOf("Thương hiệu đang theo dõi", "Voucher đã lưu"), tab) { tab = it }
        if (tab == "Thương hiệu đang theo dõi") {
            InfoBanner("Theo dõi thương hiệu để nhận thông báo khi có sản phẩm mới và ưu đãi dành riêng cho bạn.", "🔔")
            listOf("Avocado Official Mall" to "Chăm sóc thú cưng chính hãng", "Royal Canin" to "Dinh dưỡng khoa học cho chó mèo", "Cature" to "Cát vệ sinh và sản phẩm thân thiện", "PetCare House" to "Phụ kiện và đồ chơi thú cưng").forEachIndexed { index, item ->
                AppCard {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        ProductThumb(listOf("🥑", "🐈", "🌿", "🐾")[index], size = 56)
                        androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                            Text(item.first, color = AvocadoColors.PrimaryText, fontSize = 14.sp)
                            Text(item.second, color = AvocadoColors.MutedText, fontSize = 11.sp)
                            Text("${12 + index * 8} sản phẩm · ${4 + index} ưu đãi", color = AvocadoColors.Maroon, fontSize = 10.sp)
                        }
                        SecondaryButton("Đang theo dõi", {}, Modifier.weight(0.85f))
                    }
                }
            }
        } else {
            InfoBanner("Voucher đã lưu sẽ được hiển thị tại đây để bạn áp dụng nhanh khi thanh toán.", "🎟️")
            listOf("Giảm 50.000đ cho đơn từ 500.000đ" to "AVO50K", "Giảm 10% thức ăn cho mèo" to "MEOW10", "Miễn phí vận chuyển đơn từ 199.000đ" to "SHIP0").forEachIndexed { index, item ->
                AppCard {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        ProductThumb("🎟️", size = 50)
                        androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                            Text(item.first, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                            Text("Mã: ${item.second}", color = AvocadoColors.Maroon, fontSize = 11.sp)
                            Text("HSD: ${20 + index}/12/2026", color = AvocadoColors.MutedText, fontSize = 10.sp)
                        }
                        PrimaryButton("Dùng ngay", { onNavigate(com.example.avocadopetshop.ui.navigation.AvocadoRoute.Cart) }, Modifier.weight(0.8f))
                    }
                }
            }
        }
    }
}

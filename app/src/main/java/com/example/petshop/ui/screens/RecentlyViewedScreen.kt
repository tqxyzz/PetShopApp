package com.example.avocadopetshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
fun RecentlyViewedScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    var sort by remember { mutableStateOf("Gần đây nhất") }
    AvocadoPage(title = "Sản phẩm đã xem gần đây", onBack = onBack) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Bạn đã xem ${MockData.products.size} sản phẩm", Modifier.weight(1f), color = AvocadoColors.MutedText, fontSize = 12.sp)
            HorizontalChips(listOf("Gần đây nhất", "Giá thấp"), sort) { sort = it }
        }
        InfoBanner("Danh sách này chỉ lưu lịch sử xem trong bản demo hiện tại.", "🕘")
        val list = if (sort == "Giá thấp") MockData.products.sortedBy { it.price } else MockData.products.reversed()
        list.forEachIndexed { index, product ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ProductThumb(product.emoji, size = 82)
                    androidx.compose.foundation.layout.Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        StatusBadge(if (index == 0) "Vừa xem" else "Đã xem ${index + 1} giờ trước")
                        Text(product.name, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                        Text(product.subtitle, color = AvocadoColors.MutedText, fontSize = 10.sp)
                        Text("★ ${product.rating} · Còn hàng", color = AvocadoColors.Success, fontSize = 10.sp)
                        Text(formatVnd(product.price), color = AvocadoColors.Maroon, fontSize = 14.sp)
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SecondaryButton("♡ Yêu thích", { onNavigate(AvocadoRoute.Favorites) }, Modifier.weight(1f))
                    PrimaryButton("Thêm vào giỏ", { onNavigate(AvocadoRoute.Cart) }, Modifier.weight(1f))
                }
            }
        }
    }
}

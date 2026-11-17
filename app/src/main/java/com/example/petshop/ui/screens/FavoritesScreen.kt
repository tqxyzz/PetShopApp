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
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun FavoritesScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    var category by remember { mutableStateOf("Tất cả") }
    var query by remember { mutableStateOf("") }
    AvocadoPage(title = "Sản phẩm yêu thích", onBack = onBack) {
        SearchField(value = query, onValueChange = { query = it }, placeholder = "Tìm trong danh sách yêu thích")
        HorizontalChips(listOf("Tất cả", "Thức ăn", "Phụ kiện", "Đồ chơi"), category) { category = it }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("12 sản phẩm yêu thích", Modifier.weight(1f), color = AvocadoColors.MutedText, fontSize = 12.sp)
            Text("Sắp xếp: Mới nhất⌄", color = AvocadoColors.Maroon, fontSize = 11.sp)
        }
        val products = when (category) {
            "Thức ăn" -> listOf(MockData.products[0], MockData.products[2], MockData.products[6])
            "Phụ kiện" -> listOf(MockData.products[3], MockData.products[5])
            "Đồ chơi" -> listOf(MockData.products[4], MockData.products[7])
            else -> MockData.products
        }.filter { it.name.contains(query, ignoreCase = true) }
        products.forEach { product ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ProductThumb(product.emoji, size = 82)
                    androidx.compose.foundation.layout.Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text(product.name, color = AvocadoColors.PrimaryText, fontSize = 13.sp)
                        Text(product.subtitle, color = AvocadoColors.MutedText, fontSize = 10.sp)
                        Text("★ ${product.rating} · Đã bán 120+", color = AvocadoColors.MutedText, fontSize = 10.sp)
                        Text("${product.price}đ", color = AvocadoColors.Maroon, fontSize = 14.sp)
                    }
                    Text("♥", color = AvocadoColors.Primary, fontSize = 22.sp)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SecondaryButton("Bỏ yêu thích", {}, Modifier.weight(1f))
                    PrimaryButton("＋ Thêm giỏ", { onNavigate(AvocadoRoute.Cart) }, Modifier.weight(1f))
                }
            }
        }
    }
}

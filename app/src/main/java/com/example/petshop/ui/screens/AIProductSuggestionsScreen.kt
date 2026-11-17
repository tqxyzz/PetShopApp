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
import com.example.avocadopetshop.ui.data.MockData
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun AIProductSuggestionsScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    var category by remember { mutableStateOf("Phù hợp nhất") }
    AvocadoPage(title = "Gợi ý sản phẩm từ AI", onBack = onBack) {
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ProductThumb("🤖", size = 48)
                androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                    Text("Gợi ý dành riêng cho bé Miu", color = AvocadoColors.PrimaryText, fontSize = 15.sp)
                    Text("Dựa trên độ tuổi 5 tháng, sở thích và lịch sử mua hàng", color = AvocadoColors.MutedText, fontSize = 11.sp)
                }
            }
            StatusBadge("Phân tích hoàn tất · Độ phù hợp 96%", BadgeType.SUCCESS)
        }
        HorizontalChips(listOf("Phù hợp nhất", "Thức ăn", "Đồ chơi", "Chăm sóc"), category) { category = it }
        InfoBanner("PetBot ưu tiên sản phẩm phù hợp với giai đoạn phát triển và nhu cầu dinh dưỡng của bé.", "✨")
        val list = when (category) {
            "Đồ chơi" -> listOf(MockData.products[4], MockData.products[7])
            "Chăm sóc" -> listOf(MockData.products[1], MockData.products[5])
            "Thức ăn" -> listOf(MockData.products[0], MockData.products[2], MockData.products[6])
            else -> MockData.products.take(5)
        }
        list.forEachIndexed { index, product ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SectionHeading("${index + 1}. ${if (index == 0) "Rất phù hợp" else "Đề xuất cho bé"}")
                    StatusBadge(if (index == 0) "96%" else "${91 - index * 3}%", BadgeType.SUCCESS)
                }
                ProductRow(product, actionLabel = "Thêm giỏ") { onNavigate(AvocadoRoute.Cart) }
                Text("Vì sao AI gợi ý? Phù hợp với độ tuổi, nhu cầu sử dụng và mức giá bạn thường lựa chọn.", color = AvocadoColors.MutedText, fontSize = 11.sp)
                SecondaryButton("Xem phân tích chi tiết", { onNavigate(AvocadoRoute.AIProductComparison) })
            }
        }
        PrimaryButton("Trò chuyện thêm với PetBot", { onNavigate(AvocadoRoute.AIChat) })
    }
}

package com.example.avocadopetshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avocadopetshop.ui.components.*
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun AIAssistantScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    AvocadoPage(title = "Trợ lý AI PetBot", onBack = onBack, headerAction = "AI") {
        AppCard {
            ProductThumb("🐶", size = 78)
            Text("Xin chào! Mình là PetBot 🐾", color = AvocadoColors.PrimaryText, fontSize = 21.sp)
            Text("Trợ lý chăm sóc thú cưng thông minh của Avocado PetShop. Mình có thể giúp bạn chọn sản phẩm, tư vấn dinh dưỡng và giải đáp các thắc mắc.", color = AvocadoColors.SecondaryText, fontSize = 13.sp, lineHeight = 20.sp)
            StatusBadge("PetBot đang sẵn sàng hỗ trợ", BadgeType.SUCCESS)
        }
        SectionHeading("Bạn muốn PetBot giúp gì hôm nay?")
        val actions = listOf(
            Triple("🥣", "Tư vấn dinh dưỡng", "Chọn thức ăn phù hợp theo tuổi và cân nặng"),
            Triple("🛍️", "Gợi ý sản phẩm", "Tìm sản phẩm phù hợp với bé cưng"),
            Triple("⇄", "So sánh sản phẩm", "So sánh giá, thành phần và công dụng"),
            Triple("💬", "Trò chuyện với PetBot", "Đặt câu hỏi trực tiếp cho trợ lý AI"),
            Triple("📋", "Kiểm tra lịch sử mua hàng", "Xem các sản phẩm đã đặt và gợi ý mua lại")
        )
        actions.forEachIndexed { index, action ->
            AppCard {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(11.dp)) {
                    ProductThumb(action.first, size = 48)
                    androidx.compose.foundation.layout.Column(Modifier.weight(1f)) {
                        Text(action.second, color = AvocadoColors.PrimaryText, fontSize = 14.sp)
                        Text(action.third, color = AvocadoColors.MutedText, fontSize = 11.sp)
                    }
                    Text("›", color = AvocadoColors.Maroon, fontSize = 25.sp)
                }
                SecondaryButton("Bắt đầu", {
                    when (index) {
                        1 -> onNavigate(AvocadoRoute.AIProductSuggestions)
                        2 -> onNavigate(AvocadoRoute.AIProductComparison)
                        3 -> onNavigate(AvocadoRoute.AIChat)
                        4 -> onNavigate(AvocadoRoute.OrderList)
                        else -> onNavigate(AvocadoRoute.AIChat)
                    }
                })
            }
        }
        InfoBanner("Các câu trả lời trong bản demo là nội dung mẫu được gán cứng, chưa kết nối mô hình AI thật.", "ℹ️")
    }
}

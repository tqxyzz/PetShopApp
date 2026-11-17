package com.example.avocadopetshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
fun AIChatScreen(onBack: () -> Unit, onNavigate: (com.example.avocadopetshop.ui.navigation.AvocadoRoute) -> Unit) {
    var message by remember { mutableStateOf("") }
    var sentMessages by remember { mutableStateOf(listOf<String>()) }
    AvocadoPage(title = "Trò chuyện với AI PetBot", onBack = onBack, headerAction = "⋮") {
        AppCard {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ProductThumb("🐾", size = 46)
                Column(Modifier.weight(1f)) {
                    Text("Avocado PetBot AI", color = AvocadoColors.PrimaryText, fontSize = 14.sp)
                    StatusBadge("Đang hoạt động", BadgeType.SUCCESS)
                    Text("Trợ lý mua sắm & dinh dưỡng", color = AvocadoColors.MutedText, fontSize = 11.sp)
                }
                Text("♧  ⋮", color = AvocadoColors.MutedText)
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) { StatusBadge("Hôm nay, 10:14") }
        ChatBubble("Chào bạn! Mình là PetBot. Bé mèo/chó nhà bạn tên gì, bao nhiêu tháng tuổi và đang gặp khó khăn gì về đồ ăn hay đồ chơi để mình tư vấn ngay nha! 🐾", false)
        ChatBubble("Bé Miu nhà mình 5 tháng tuổi, dạo này hơi lười ăn hạt và lông có vẻ xơ. Bạn có loại hạt nào thơm, giàu đạm và hỗ trợ mượt lông không?", true)
        ChatBubble("Dạ mình hiểu rồi! Ở giai đoạn 5 tháng tuổi, bé Miu đang phát triển khung xương và thay lông. Cần hạt giàu Omega-3, Omega-6 và dễ tiêu hóa. Dưới đây là 2 dòng hạt phù hợp nhất:", false)
        AppCard {
            ProductRow(MockData.products[0], actionLabel = "Thêm vào giỏ") { onNavigate(com.example.avocadopetshop.ui.navigation.AvocadoRoute.Cart) }
            SecondaryButton("Xem chi tiết", {})
        }
        AppCard {
            ProductRow(MockData.products[1], actionLabel = "Thêm vào giỏ") { onNavigate(com.example.avocadopetshop.ui.navigation.AvocadoRoute.Cart) }
            SecondaryButton("Xem chi tiết", {})
        }
        Text("Gợi ý câu hỏi tiếp theo cho bạn:", color = AvocadoColors.MutedText, fontSize = 12.sp)
        listOf("⇄ So sánh 2 loại hạt này", "♨ Gợi ý thêm súp thưởng kích thích vị giác", "♧ Cách tập bé ăn hạt mới").forEach { question ->
            SecondaryButton(question, { message = question })
        }
        if (sentMessages.isNotEmpty()) sentMessages.forEach { ChatBubble(it, true) }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Nhập câu hỏi cho PetBot...", color = AvocadoColors.Placeholder, fontSize = 12.sp) },
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = AvocadoColors.Primary, unfocusedBorderColor = AvocadoColors.Border, focusedContainerColor = AvocadoColors.CardSurface, unfocusedContainerColor = AvocadoColors.CardSurface)
            )
            PrimaryButton("➤", {
                if (message.isNotBlank()) {
                    sentMessages = sentMessages + message.trim()
                    message = ""
                }
            }, Modifier.weight(0.35f))
        }
    }
}

@Composable
private fun ChatBubble(text: String, isUser: Boolean) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start) {
        Column(
            Modifier.fillMaxWidth(0.88f).background(if (isUser) AvocadoColors.Primary else AvocadoColors.CardSurface, RoundedCornerShape(18.dp)).padding(13.dp)
        ) {
            if (!isUser) Text("Avocado PetBot", color = AvocadoColors.Maroon, fontSize = 11.sp)
            Text(text, color = if (isUser) AvocadoColors.OnPrimary else AvocadoColors.PrimaryText, fontSize = 13.sp, lineHeight = 19.sp)
        }
    }
}

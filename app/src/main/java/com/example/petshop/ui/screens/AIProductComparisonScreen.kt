package com.example.avocadopetshop.ui.screens
import androidx.compose.foundation.layout.fillMaxWidth

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
fun AIProductComparisonScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    var preference by remember { mutableStateOf("Dinh dưỡng") }
    AvocadoPage(title = "So sánh sản phẩm bằng AI", onBack = onBack) {
        AppCard {
            Text("PetBot sẽ giúp bạn chọn sản phẩm phù hợp", color = AvocadoColors.PrimaryText, fontSize = 17.sp)
            Text("Chọn tiêu chí bạn quan tâm để nhận phân tích dễ hiểu.", color = AvocadoColors.MutedText, fontSize = 12.sp)
            HorizontalChips(listOf("Dinh dưỡng", "Giá tốt", "Thành phần", "Độ tuổi"), preference) { preference = it }
        }
        SectionHeading("Hai sản phẩm được chọn")
        Row(horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            AppCard(Modifier.weight(1f)) {
                ProductThumb(MockData.products[0].emoji, size = 58)
                Text("Royal Canin Kitten", color = AvocadoColors.PrimaryText, fontSize = 12.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Text("385.000đ", color = AvocadoColors.Maroon, fontSize = 13.sp)
            }
            AppCard(Modifier.weight(1f)) {
                ProductThumb("🐈", size = 58)
                Text("Orijen Cat & Kitten", color = AvocadoColors.PrimaryText, fontSize = 12.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Text("620.000đ", color = AvocadoColors.Maroon, fontSize = 13.sp)
            }
        }
        AppCard {
            SectionHeading("Kết quả phân tích của PetBot")
            StatusBadge("Đã phân tích · 92% phù hợp", BadgeType.SUCCESS)
            CompareLine("Tiêu chí", "Royal Canin", "Orijen", true)
            CompareLine("Độ tuổi phù hợp", "Mèo con", "Mọi độ tuổi")
            CompareLine("Hàm lượng đạm", "34%", "40%")
            CompareLine("Hỗ trợ tiêu hóa", "Tốt", "Rất tốt")
            CompareLine("Mức giá", "Tiết kiệm hơn", "Cao hơn")
            CompareLine("Khối lượng", "2 kg", "1,8 kg")
        }
        AppCard {
            Text("💡 Nhận xét của AI", color = AvocadoColors.PrimaryText, fontSize = 15.sp)
            Text(
                when (preference) {
                    "Giá tốt" -> "Royal Canin có mức giá dễ tiếp cận hơn, phù hợp khi bạn ưu tiên chi phí và cần thức ăn chuyên biệt cho mèo con."
                    "Thành phần" -> "Orijen có tỷ lệ đạm cao hơn; hãy cân nhắc khả năng thích nghi và nhu cầu dinh dưỡng thực tế của bé."
                    "Độ tuổi" -> "Royal Canin Kitten được thiết kế cho giai đoạn mèo con, phù hợp với bé Miu 5 tháng tuổi trong dữ liệu mẫu."
                    else -> "Cả hai đều là lựa chọn đáng cân nhắc. Với mèo 5 tháng tuổi, Royal Canin Kitten là phương án cân bằng giữa độ tuổi phù hợp và mức giá."
                },
                color = AvocadoColors.SecondaryText,
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
            StatusBadge("PetBot đề xuất: Royal Canin Kitten", BadgeType.SUCCESS)
            PrimaryButton("Xem sản phẩm đề xuất", { onNavigate(AvocadoRoute.AIProductSuggestions) })
        }
        SecondaryButton("Trò chuyện thêm với PetBot", { onNavigate(AvocadoRoute.AIChat) })
    }
}

@Composable
private fun CompareLine(label: String, left: String, right: String, header: Boolean = false) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, Modifier.weight(1f), color = if (header) AvocadoColors.PrimaryText else AvocadoColors.MutedText, fontSize = 10.sp, fontWeight = if (header) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal)
        Text(left, Modifier.weight(1f), color = AvocadoColors.SecondaryText, fontSize = 10.sp, fontWeight = if (header) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Medium)
        Text(right, Modifier.weight(1f), color = AvocadoColors.SecondaryText, fontSize = 10.sp, fontWeight = if (header) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Medium)
    }
}

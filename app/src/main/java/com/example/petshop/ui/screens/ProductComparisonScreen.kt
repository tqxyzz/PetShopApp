package com.example.avocadopetshop.ui.screens
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avocadopetshop.ui.components.*
import com.example.avocadopetshop.ui.data.MockData
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

@Composable
fun ProductComparisonScreen(onBack: () -> Unit, onNavigate: (AvocadoRoute) -> Unit) {
    AvocadoPage(title = "So sánh sản phẩm", onBack = onBack) {
        InfoBanner("Bạn có thể so sánh tối đa 3 sản phẩm để chọn món phù hợp nhất cho bé.", "⇄")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AppCard(Modifier.weight(1f)) {
                ProductThumb(MockData.products[0].emoji, size = 64)
                Text("Royal Canin Kitten", color = AvocadoColors.PrimaryText, fontSize = 12.sp)
                Text("385.000đ", color = AvocadoColors.Maroon, fontSize = 14.sp)
                StatusBadge("Đang chọn", BadgeType.PRIMARY)
            }
            AppCard(Modifier.weight(1f)) {
                ProductThumb("🐈", size = 64)
                Text("Orijen Cat & Kitten", color = AvocadoColors.PrimaryText, fontSize = 12.sp)
                Text("620.000đ", color = AvocadoColors.Maroon, fontSize = 14.sp)
                StatusBadge("Đang chọn", BadgeType.PRIMARY)
            }
        }
        SecondaryButton("＋ Thêm sản phẩm để so sánh", { onNavigate(AvocadoRoute.AIProductSuggestions) })
        AppCard {
            SectionHeading("Bảng so sánh chi tiết")
            CompareTableRow("Thông tin", "Royal Canin", "Orijen", true)
            CompareTableRow("Thương hiệu", "Royal Canin", "Orijen")
            CompareTableRow("Khối lượng", "2 kg", "1,8 kg")
            CompareTableRow("Độ tuổi", "Mèo con", "Mọi độ tuổi")
            CompareTableRow("Protein", "34%", "40%")
            CompareTableRow("Đánh giá", "4,9 ★", "4,8 ★")
            CompareTableRow("Giá bán", "385.000đ", "620.000đ")
            CompareTableRow("Giá / kg", "192.500đ", "344.444đ")
        }
        AppCard {
            Text("Kết luận nhanh", color = AvocadoColors.PrimaryText, fontSize = 15.sp)
            Text("Royal Canin Kitten có giá tốt và công thức dành riêng cho mèo con. Orijen có hàm lượng đạm cao hơn và mức giá cao hơn.", color = AvocadoColors.SecondaryText, fontSize = 12.sp, lineHeight = 18.sp)
            StatusBadge("Phù hợp với bé Miu: Royal Canin", BadgeType.SUCCESS)
        }
        PrimaryButton("Nhờ AI phân tích sâu hơn", { onNavigate(AvocadoRoute.AIProductComparison) })
    }
}

@Composable
private fun CompareTableRow(label: String, left: String, right: String, header: Boolean = false) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, Modifier.weight(1f), color = AvocadoColors.PrimaryText, fontSize = 10.sp, fontWeight = if (header) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal)
        Text(left, Modifier.weight(1f), color = AvocadoColors.SecondaryText, fontSize = 10.sp, fontWeight = if (header) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Medium)
        Text(right, Modifier.weight(1f), color = AvocadoColors.SecondaryText, fontSize = 10.sp, fontWeight = if (header) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Medium)
    }
}

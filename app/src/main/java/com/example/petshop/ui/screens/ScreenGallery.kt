package com.example.avocadopetshop.ui.screens
import androidx.compose.foundation.Image
import com.example.petshop.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avocadopetshop.ui.components.AppCard
import com.example.avocadopetshop.ui.components.AvocadoPage
import com.example.avocadopetshop.ui.components.InfoBanner
import com.example.avocadopetshop.ui.navigation.AvocadoRoute
import com.example.avocadopetshop.ui.theme.AvocadoColors

/** Temporary developer hub. It is not one of the 16 Figma reference screens. */
@Composable
fun ScreenGallery(onNavigate: (AvocadoRoute) -> Unit) {
    AvocadoPage(title = "Avocado PetShop · UI Demo", onBack = {}, headerActionIcon = R.drawable.logo) {
        Text("Thư viện 16 màn hình", color = AvocadoColors.PrimaryText, fontSize = 22.sp)
        Text("Chọn một màn hình để xem giao diện mẫu. Dữ liệu hiện đang được gán cứng.", color = AvocadoColors.MutedText, fontSize = 12.sp)
//        InfoBanner("Đây là menu tạm để kiểm thử. Khi tích hợp dự án, nhóm có thể đổi màn hình khởi đầu trong AvocadoApp.kt.", "🧭")
        val routes = AvocadoRoute.values().filter { it != AvocadoRoute.Gallery }
        routes.chunked(2).forEach { pair ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                pair.forEach { route ->
                    Surface(onClick = { onNavigate(route) }, modifier = Modifier.weight(1f), color = AvocadoColors.CardSurface, shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp), border = androidx.compose.foundation.BorderStroke(0.7.dp, AvocadoColors.Border)) {
                        androidx.compose.foundation.layout.Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.login),
                                contentDescription = "Avocado PetShop Logo",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .height(42.dp) // Chỉnh độ cao theo ý muốn (tầm 36.dp - 48.dp là vừa đẹp thẻ)
                                    .wrapContentWidth() // Để chiều ngang tự giãn ra theo đúng tỉ lệ gốc của ảnh
                            )
                            Text(route.title, color = AvocadoColors.PrimaryText, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Text("Mở màn hình  →", color = AvocadoColors.Maroon, fontSize = 10.sp)
                        }
                    }
                }
                if (pair.size == 1) androidx.compose.foundation.layout.Spacer(Modifier.weight(1f))
            }
        }
    }
}

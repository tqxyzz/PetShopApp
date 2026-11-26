package com.example.petshop.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.theme.*

@Composable
fun NgoaiTuyenScreen(
    onRetry: () -> Unit,
    onViewOfflineCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Offline Illustration Circle
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(SecondarySurface)
                .border(3.dp, DangerText, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_paw),
                contentDescription = null,
                tint = DangerText,
                modifier = Modifier.size(56.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(DangerBg)
                .padding(horizontal = 12.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lightning),
                    contentDescription = null,
                    tint = DangerText,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "MẤT KẾT NỐI MẠNG",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = DangerText
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Ôi Không, Mạng Đang Chạy Đi Chơi Rồi!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 26.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Không thể kết nối với máy chủ Avocado PetShop. Đừng lo, giỏ hàng các món hạt và pate yêu thích của Boss vẫn được lưu an toàn trên thiết bị của Sen.",
            fontSize = 13.sp,
            color = SecondaryText,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 19.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Quick checklist card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text(
                    text = "💡 Gợi ý kiểm tra nhanh",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                val tips = listOf(
                    "Kiểm tra lại kết nối Wi-Fi hoặc dữ liệu di động 4G/5G của điện thoại.",
                    "Thử tắt và bật lại Chế độ máy bay (Airplane Mode) để làm mới mạng.",
                    "Đảm bảo ứng dụng Avocado PetShop đã được cấp quyền truy cập dữ liệu nền."
                )

                tips.forEachIndexed { idx, tip ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(SecondarySoft),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${idx + 1}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                        Text(
                            text = tip,
                            fontSize = 12.sp,
                            color = SecondaryText,
                            lineHeight = 17.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { onRetry() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryCTA)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lightning),
                        contentDescription = null,
                        tint = OnPrimaryText,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Thử kết nối lại",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimaryText
                    )
                }
            }

            Button(
                onClick = { onViewOfflineCart() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SecondarySurface)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shopping_bag),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Xem giỏ hàng đã lưu ngoại tuyến",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_headset),
                contentDescription = null,
                tint = MutedText,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = "Cần trợ giúp khẩn cấp? Gọi tổng đài:",
                fontSize = 12.sp,
                color = MutedText
            )
            Text(
                text = "1900 8888",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryCTA,
                modifier = Modifier.clickable { /* Call */ }
            )
        }
    }
}

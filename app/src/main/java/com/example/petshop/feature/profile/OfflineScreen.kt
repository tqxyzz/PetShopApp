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
fun OfflineScreen(
    onRetry: () -> Unit,
    onViewOfflineCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Illustration Badge / Circle Banner
        Box(
            modifier = Modifier
                .size(240.dp)
                .clip(CircleShape)
                .background(SecondarySurface)
                .border(2.dp, PrimaryCTA.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_paw),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(64.dp)
                )
                Text(
                    text = "Avocado PetShop",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MutedText
                )
                Text(
                    text = "Mất kết nối mạng",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            // Offline alert badge on top right
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-20).dp, y = 20.dp)
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(DangerBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ticket),
                    contentDescription = null,
                    tint = DangerText,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Offline Status Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(DangerBg)
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(DangerText)
                )
                Text(
                    text = "Chế độ ngoại tuyến",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DangerText
                )
            }
        }

        // Heading & Description
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Ôi! Mất kết nối mạng rồi Sen ơi...",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Text(
                text = "Dường như đường truyền Internet hoặc dữ liệu di động của bạn đang bị gián đoạn. Đừng lo, các món đồ chơi và pate yêu thích trong giỏ hàng của Boss vẫn được lưu an toàn!",
                fontSize = 13.sp,
                color = SecondaryText,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 19.sp
            )
        }

        // Quick Check Card ("Gợi ý kiểm tra nhanh")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(18.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bulb),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Gợi ý kiểm tra nhanh",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                val suggestions = listOf(
                    Triple("Kiểm tra Wifi hoặc mạng 4G / 5G", "Đảm bảo thiết bị đã bật kết nối mạng ổn định", R.drawable.ic_store),
                    Triple("Bật và tắt lại Chế độ máy bay", "Giúp modem di động đặt lại chu kỳ tìm sóng", R.drawable.ic_truck),
                    Triple("Quyền dữ liệu ứng dụng", "Đảm bảo Avocado PetShop được phép dùng dữ liệu nền", R.drawable.ic_shield_check)
                )

                suggestions.forEach { (title, subtitle, iconRes) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(SecondarySurface)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(CardSurface),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = null,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Spacer(modifier = Modifier.height(1.dp))
                            Text(
                                text = subtitle,
                                fontSize = 11.sp,
                                color = MutedText
                            )
                        }
                    }
                }
            }
        }

        // Action Buttons
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
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = null,
                        tint = OnPrimaryText,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Thử kết nối lại",
                        fontSize = 15.sp,
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
                        tint = SecondaryText,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Xem giỏ hàng đã lưu ngoại tuyến",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Support Footer
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
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
                    text = "Cần hỗ trợ? Hotline miễn cước: 1900 8888",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }
            Text(
                text = "Đội ngũ chăm sóc Avocado Pet luôn sẵn sàng 24/7",
                fontSize = 11.sp,
                color = MutedText
            )
        }
    }
}

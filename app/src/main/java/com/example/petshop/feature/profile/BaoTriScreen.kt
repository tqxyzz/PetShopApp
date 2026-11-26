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
fun BaoTriScreen(
    onRetry: () -> Unit,
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

        // Illustration Circle
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(SecondarySurface)
                .border(3.dp, PrimaryCTA, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_paw),
                contentDescription = null,
                tint = PrimaryCTA,
                modifier = Modifier.size(56.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(WarningBg)
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
                    tint = WarningText,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "BẢO TRÌ NÂNG CẤP HỆ THỐNG",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = WarningText
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Avocado PetShop Đang Nâng Cấp Trải Nghiệm",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 26.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Chúng tôi đang bổ sung thêm nhiều kho hạt dinh dưỡng mới, nâng cấp hệ thống trợ lý AI và tối ưu hóa tốc độ giao hàng hỏa tốc 2H cho Sen và Boss.",
            fontSize = 13.sp,
            color = SecondaryText,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            lineHeight = 19.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Countdown Timer Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "⏱ Thời gian dự kiến hoàn tất",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MutedText
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TimeBox("01", "Giờ")
                    TimeBox("42", "Phút")
                    TimeBox("15", "Giây")
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Progress bar
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Tiến độ đồng bộ dữ liệu", fontSize = 11.sp, color = MutedText)
                        Text(text = "85%", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryCTA)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(SecondarySurface)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .fillMaxHeight()
                                .background(PrimaryCTA, RoundedCornerShape(4.dp))
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // What's new card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(24.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Có gì mới sau bảo trì?",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "• Mở kho hạt hữu cơ nhập khẩu Đức & Ý\n• Nâng cấp tính năng đổi Pet Points nhận quà thật\n• Tối ưu định vị GPS giao hàng chuẩn từng mét",
                        fontSize = 11.sp,
                        color = SecondaryText,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

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
                    text = "Kiểm tra kết nối lại",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnPrimaryText
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                text = "Tổng đài khẩn cấp 24/7:",
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

@Composable
fun TimeBox(number: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
        }
        Text(
            text = label,
            fontSize = 11.sp,
            color = MutedText,
            fontWeight = FontWeight.Medium
        )
    }
}

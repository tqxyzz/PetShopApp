package com.example.petshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText

@Composable
fun OnboardingBanner(
    pageIndex: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
            .shadow(12.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(24.dp))
            .background(CardSurface)
            .border(1.5.dp, BorderDivider, RoundedCornerShape(24.dp))
    ) {
        // Soft gradient warm background illustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFF5EF),
                            Color(0xFFFFE8DD)
                        )
                    )
                )
        )

        // Center pet illustration (Compose drawing representing cute pets & avocado gifts)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 40.dp, start = 20.dp, end = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFFF9F5))
                .border(1.dp, Color(0xFFFFECE4), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                // Cat illustration circle
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFE5DB)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🐱", fontSize = 36.sp)
                        Text(
                            text = "Mèo cưng",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Gift box & Avocado icon
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(PrimaryCTA),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Dog illustration circle
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFE5DB)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🐶", fontSize = 36.sp)
                        Text(
                            text = "Cún cưng",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }
                }
            }
        }

        // Top-Left Badge: "✓ Chính hãng 100%"
        Box(
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp)
                .align(Alignment.TopStart)
                .clip(RoundedCornerShape(20.dp))
                .background(SuccessBg)
                .border(1.dp, SuccessText.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shield_check),
                    contentDescription = null,
                    tint = SuccessText,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Chính hãng 100%",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = SuccessText
                )
            }
        }

        // Top-Right Tag: Page Title info
        Box(
            modifier = Modifier
                .padding(top = 12.dp, end = 12.dp)
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = "${pageIndex + 1}/3 - Giới thiệu ứng dụng",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryText
            )
        }

        // Bottom-Right Badge: "🚚 Freeship mọi đơn"
        Box(
            modifier = Modifier
                .padding(bottom = 12.dp, end = 12.dp)
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(20.dp))
                .background(PrimaryCTA)
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_truck),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Freeship mọi đơn",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

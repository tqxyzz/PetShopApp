package com.example.petshop.feature.onboarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.components.OnboardingBanner
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.IconPrimaryBg
import com.example.petshop.ui.theme.IconPrimaryTint
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PetShopTheme
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText
import kotlinx.coroutines.launch

data class OnboardingPageData(
    val badgeText: String,
    val title: String,
    val description: String
)

val onboardingPages = listOf(
    OnboardingPageData(
        badgeText = "♡ Chăm sóc chuẩn hoàng gia",
        title = "Mua sắm thông minh cho Boss cưng",
        description = "Hàng ngàn sản phẩm thức ăn hạt, pate tươi, đồ chơi và phụ kiện chính hãng chất lượng cao cùng ưu đãi đặc quyền mỗi ngày."
    ),
    OnboardingPageData(
        badgeText = "⚡ Giao hàng hỏa tốc 2H",
        title = "Chăm sóc toàn diện cho bé cưng",
        description = "Cam kết 100% sản phẩm chính hãng, nguồn gốc rõ ràng, bảo vệ sức khỏe và mang lại niềm vui cho thú cưng của bạn."
    ),
    OnboardingPageData(
        badgeText = "🎁 Tích điểm & Nhận quà",
        title = "Dịch vụ tận tâm & Bác sĩ 24/7",
        description = "Tư vấn sức khỏe, dinh dưỡng miễn phí từ đội ngũ bác sĩ thú y giàu kinh nghiệm cùng nhiều ưu đãi hấp dẫn dành riêng cho Sen."
    )
)

@Composable
fun OnboardingScreen(
    onFinishOnboarding: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Navigation Header Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // App Pill Tag Left
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Avocado PetShop",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }

            // Skip Button Right
            Text(
                text = "Bỏ qua >",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = SecondaryText,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onFinishOnboarding() }
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Onboarding Pager Banner
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            OnboardingBanner(pageIndex = page)
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Page Indicator Dots
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(onboardingPages.size) { index ->
                val isSelected = pagerState.currentPage == index
                val width by animateDpAsState(
                    targetValue = if (isSelected) 28.dp else 8.dp,
                    animationSpec = tween(durationMillis = 300),
                    label = "DotWidth"
                )
                val color = if (isSelected) PrimaryCTA else BorderDivider

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(8.dp)
                        .width(width)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Current Page Text Content
        val currentPageData = onboardingPages[pagerState.currentPage]

        // Subhead Pill Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySoft)
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Text(
                text = currentPageData.badgeText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SecondarySoftText
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Title
        Text(
            text = currentPageData.title,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = PrimaryText,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Description
        Text(
            text = currentPageData.description,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
            color = SecondaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 3 Feature Cards Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Feature 1
            FeatureItemCard(
                iconRes = R.drawable.ic_lightning,
                iconBg = IconPrimaryBg,
                iconTint = IconPrimaryTint,
                title = "Giao siêu tốc\n2H",
                subtitle = "Nội thành\nnhanh chóng",
                modifier = Modifier.weight(1f)
            )

            // Feature 2
            FeatureItemCard(
                iconRes = R.drawable.ic_piggy,
                iconBg = IconPrimaryBg,
                iconTint = IconPrimaryTint,
                title = "Tích Pet Points",
                subtitle = "Đổi quà mê ly",
                modifier = Modifier.weight(1f)
            )

            // Feature 3
            FeatureItemCard(
                iconRes = R.drawable.ic_headset,
                iconBg = SuccessBg,
                iconTint = SuccessText,
                title = "Tư vấn tận tâm",
                subtitle = "Bác sĩ thú y\n24/7",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Primary CTA Button
        Button(
            onClick = {
                if (pagerState.currentPage < onboardingPages.size - 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    onFinishOnboarding()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(27.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryCTA,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = if (pagerState.currentPage == onboardingPages.size - 1) "Bắt đầu ngay →" else "Tiếp theo →",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Footer Auth Link: "Đã có tài khoản? Đăng nhập"
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MutedText, fontWeight = FontWeight.Normal)) {
                    append("Đã có tài khoản? ")
                }
                withStyle(style = SpanStyle(color = SecondarySoftText, fontWeight = FontWeight.Bold)) {
                    append("Đăng nhập")
                }
            },
            fontSize = 14.sp,
            modifier = Modifier
                .clickable { onNavigateToLogin() }
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun FeatureItemCard(
    iconRes: Int,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
            .padding(vertical = 14.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = subtitle,
                fontSize = 10.sp,
                lineHeight = 13.sp,
                fontWeight = FontWeight.Normal,
                color = MutedText,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    PetShopTheme {
        OnboardingScreen(
            onFinishOnboarding = {},
            onNavigateToLogin = {}
        )
    }
}

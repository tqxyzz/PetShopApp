package com.example.petshop.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
import com.example.petshop.ui.components.AvocadoLogoCircle
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PetShopTheme
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessText
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2200, easing = LinearEasing)
        )
        delay(300)
        onNavigateToOnboarding()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .padding(horizontal = 24.dp, vertical = 36.dp)
    ) {
        // Decorative background paw print icons
        Icon(
            painter = painterResource(id = R.drawable.ic_paw),
            contentDescription = null,
            tint = BorderDivider.copy(alpha = 0.35f),
            modifier = Modifier
                .size(48.dp)
                .offset(x = 16.dp, y = 32.dp)
                .alpha(0.6f)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_paw),
            contentDescription = null,
            tint = BorderDivider.copy(alpha = 0.35f),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(40.dp)
                .offset(x = (-16).dp, y = 36.dp)
                .alpha(0.6f)
        )

        // Top Header Tag
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(30.dp))
                .padding(horizontal = 18.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_leaf),
                    contentDescription = null,
                    tint = SuccessText,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "TỰ NHIÊN • AN LÀNH • CHU ĐÁO",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryText,
                    letterSpacing = 0.5.sp
                )
            }
        }

        // Center Mascot & Title
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AvocadoLogoCircle(
                circleSize = 190.dp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // App Name: "Avocado PetShop"
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = PrimaryText, fontWeight = FontWeight.ExtraBold)) {
                        append("Avocado ")
                    }
                    withStyle(style = SpanStyle(color = PrimaryCTA, fontWeight = FontWeight.ExtraBold)) {
                        append("PetShop")
                    }
                },
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Subtitle
            Text(
                text = "Thế giới mua sắm tuyệt vời cho bé\ncưng của bạn",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Normal,
                color = MutedText,
                textAlign = TextAlign.Center
            )
        }

        // Bottom Progress & Version Footer
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Loading Progress Line
            LinearProgressIndicator(
                progress = { progress.value },
                modifier = Modifier
                    .width(220.dp)
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = PrimaryCTA,
                trackColor = BorderDivider.copy(alpha = 0.4f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Status message
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "↻",
                    fontSize = 14.sp,
                    color = PrimaryCTA,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Gần xong rồi sen ơi...",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = SecondaryText
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Version info tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "v1.0.0 Android",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MutedText
                )
                Text(
                    text = " • ",
                    fontSize = 11.sp,
                    color = MutedText
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_shield_check),
                    contentDescription = null,
                    tint = SuccessText,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = "Chính hãng",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = SecondaryText
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        // Decorative background paw print icons at bottom
        Icon(
            painter = painterResource(id = R.drawable.ic_paw),
            contentDescription = null,
            tint = BorderDivider.copy(alpha = 0.35f),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(36.dp)
                .offset(x = 10.dp, y = (-10).dp)
                .alpha(0.5f)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_paw),
            contentDescription = null,
            tint = BorderDivider.copy(alpha = 0.35f),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(42.dp)
                .offset(x = (-10).dp, y = (-10).dp)
                .alpha(0.5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    PetShopTheme {
        SplashScreen(onNavigateToOnboarding = {})
    }
}

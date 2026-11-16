package com.example.petshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.feature.onboarding.OnboardingScreen
import com.example.petshop.feature.splash.SplashScreen
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.PetShopTheme
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondaryText

sealed class AppScreen {
    object Splash : AppScreen()
    object Onboarding : AppScreen()
    object Login : AppScreen()
    object Home : AppScreen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetShopTheme {
                var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Splash) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen) {
                            is AppScreen.Splash -> {
                                SplashScreen(
                                    onNavigateToOnboarding = {
                                        currentScreen = AppScreen.Onboarding
                                    }
                                )
                            }
                            is AppScreen.Onboarding -> {
                                OnboardingScreen(
                                    onFinishOnboarding = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToLogin = {
                                        currentScreen = AppScreen.Login
                                    }
                                )
                            }
                            is AppScreen.Login -> {
                                PlaceholderScreen(
                                    title = "Màn hình Đăng nhập",
                                    subtitle = "Tính năng đang được phát triển",
                                    onBack = { currentScreen = AppScreen.Onboarding }
                                )
                            }
                            is AppScreen.Home -> {
                                PlaceholderScreen(
                                    title = "Trang chủ Avocado PetShop",
                                    subtitle = "Chào mừng bạn đến với Avocado PetShop!",
                                    onBack = { currentScreen = AppScreen.Onboarding }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlaceholderScreen(
    title: String,
    subtitle: String,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = SecondaryText
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onBack,
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryCTA)
            ) {
                Text(text = "Quay lại Onboarding", color = Color.White)
            }
        }
    }
}

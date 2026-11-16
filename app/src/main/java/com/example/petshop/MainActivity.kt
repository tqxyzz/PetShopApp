package com.example.petshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.petshop.feature.auth.ForgotPasswordDialog
import com.example.petshop.feature.auth.LoginScreen
import com.example.petshop.feature.auth.RegisterScreen
import com.example.petshop.feature.flashsale.FlashSaleScreen
import com.example.petshop.feature.home.HomeScreen
import com.example.petshop.feature.onboarding.OnboardingScreen
import com.example.petshop.feature.splash.SplashScreen
import com.example.petshop.ui.theme.PetShopTheme

sealed class AppScreen {
    object Splash : AppScreen()
    object Onboarding : AppScreen()
    object Login : AppScreen()
    object Register : AppScreen()
    object Home : AppScreen()
    object FlashSale : AppScreen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetShopTheme {
                var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Splash) }
                var showForgotPasswordDialog by remember { mutableStateOf(false) }

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
                                        currentScreen = AppScreen.Login
                                    },
                                    onNavigateToLogin = {
                                        currentScreen = AppScreen.Login
                                    }
                                )
                            }
                            is AppScreen.Login -> {
                                LoginScreen(
                                    onNavigateToRegister = {
                                        currentScreen = AppScreen.Register
                                    },
                                    onNavigateToHome = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onForgotPassword = {
                                        showForgotPasswordDialog = true
                                    }
                                )
                            }
                            is AppScreen.Register -> {
                                RegisterScreen(
                                    onNavigateToLogin = {
                                        currentScreen = AppScreen.Login
                                    },
                                    onNavigateToHome = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onBack = {
                                        currentScreen = AppScreen.Login
                                    }
                                )
                            }
                            is AppScreen.Home -> {
                                HomeScreen(
                                    onNavigateToFlashSale = {
                                        currentScreen = AppScreen.FlashSale
                                    }
                                )
                            }
                            is AppScreen.FlashSale -> {
                                FlashSaleScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    }
                                )
                            }
                        }

                        if (showForgotPasswordDialog) {
                            ForgotPasswordDialog(
                                onDismiss = { showForgotPasswordDialog = false },
                                onSubmit = { email ->
                                    // Password recovery flow
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

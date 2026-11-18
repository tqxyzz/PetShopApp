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
import com.example.petshop.feature.brand.OfficialBrandsScreen
import com.example.petshop.feature.category.CategoryProductListScreen
import com.example.petshop.feature.category.CategoryScreen
import com.example.petshop.feature.flashsale.FlashSaleScreen
import com.example.petshop.feature.home.HomeScreen
import com.example.petshop.feature.onboarding.OnboardingScreen
import com.example.petshop.feature.splash.SplashScreen
import com.example.petshop.feature.voucher.VoucherCenterScreen
import com.example.petshop.feature.cart.CartScreen
import com.example.petshop.feature.cart.VoucherPetPointsScreen
import com.example.petshop.feature.checkout.OrderSuccessScreen
import com.example.petshop.feature.checkout.PaymentMethodScreen
import com.example.petshop.feature.checkout.ShippingAddressScreen
import com.example.petshop.feature.product.ProductDetailScreen
import com.example.petshop.feature.search.CameraScanScreen
import com.example.petshop.feature.search.ImageSearchResultScreen
import com.example.petshop.feature.search.SearchHistoryScreen
import com.example.petshop.ui.theme.PetShopTheme

sealed class AppScreen {
    object Splash : AppScreen()
    object Onboarding : AppScreen()
    object Login : AppScreen()
    object Register : AppScreen()
    object Home : AppScreen()
    object FlashSale : AppScreen()
    object Voucher : AppScreen()
    object Brand : AppScreen()
    object Category : AppScreen()
    object CategoryProductList : AppScreen()
    object SearchHistory : AppScreen()
    object CameraScan : AppScreen()
    object ImageSearchResult : AppScreen()
    object ProductDetail : AppScreen()
    object Cart : AppScreen()
    object VoucherPetPoints : AppScreen()
    object ShippingAddress : AppScreen()
    object PaymentMethod : AppScreen()
    object OrderSuccess : AppScreen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetShopTheme {
                var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.Splash) }
                var showForgotPasswordDialog by remember { mutableStateOf(false) }
                var activeCategoryTitle by remember { mutableStateOf("Đồ Dùng & Thức Ăn Cho Chó") }

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
                                    },
                                    onNavigateToVoucher = {
                                        currentScreen = AppScreen.Voucher
                                    },
                                    onNavigateToCategory = {
                                        currentScreen = AppScreen.Category
                                    },
                                    onNavigateToBrand = {
                                        currentScreen = AppScreen.Brand
                                    },
                                    onNavigateToCart = {
                                        currentScreen = AppScreen.Cart
                                    },
                                    onNavigateToProductDetail = {
                                        currentScreen = AppScreen.ProductDetail
                                    },
                                    onNavigateToSearchHistory = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onNavigateToCameraScan = {
                                        currentScreen = AppScreen.CameraScan
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
                            is AppScreen.Voucher -> {
                                VoucherCenterScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    }
                                )
                            }
                            is AppScreen.Brand -> {
                                OfficialBrandsScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    }
                                )
                            }
                            is AppScreen.Category -> {
                                CategoryScreen(
                                    onNavigateToHome = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToCategoryProductList = { categoryTitle ->
                                        activeCategoryTitle = categoryTitle
                                        currentScreen = AppScreen.CategoryProductList
                                    }
                                )
                            }
                            is AppScreen.CategoryProductList -> {
                                CategoryProductListScreen(
                                    categoryTitle = activeCategoryTitle,
                                    onBack = {
                                        currentScreen = AppScreen.Category
                                    }
                                )
                            }
                            is AppScreen.SearchHistory -> {
                                SearchHistoryScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToCameraScan = {
                                        currentScreen = AppScreen.CameraScan
                                    },
                                    onSearchSubmit = { query ->
                                        currentScreen = AppScreen.ImageSearchResult
                                    }
                                )
                            }
                            is AppScreen.CameraScan -> {
                                CameraScanScreen(
                                    onBack = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onClose = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToHistory = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onCapture = {
                                        currentScreen = AppScreen.ImageSearchResult
                                    }
                                )
                            }
                            is AppScreen.ImageSearchResult -> {
                                ImageSearchResultScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CameraScan
                                    },
                                    onClose = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToCameraScan = {
                                        currentScreen = AppScreen.CameraScan
                                    },
                                    onAddToCart = {
                                        currentScreen = AppScreen.Cart
                                    },
                                    onBuyNow = {
                                        currentScreen = AppScreen.ProductDetail
                                    }
                                )
                            }
                            is AppScreen.ProductDetail -> {
                                ProductDetailScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToCart = {
                                        currentScreen = AppScreen.Cart
                                    },
                                    onBuyNow = {
                                        currentScreen = AppScreen.ShippingAddress
                                    }
                                )
                            }
                            is AppScreen.Cart -> {
                                CartScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToVoucherSelect = {
                                        currentScreen = AppScreen.VoucherPetPoints
                                    },
                                    onNavigateToCheckout = {
                                        currentScreen = AppScreen.ShippingAddress
                                    }
                                )
                            }
                            is AppScreen.VoucherPetPoints -> {
                                VoucherPetPointsScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Cart
                                    },
                                    onConfirm = {
                                        currentScreen = AppScreen.Cart
                                    }
                                )
                            }
                            is AppScreen.ShippingAddress -> {
                                ShippingAddressScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Cart
                                    },
                                    onContinueToPayment = {
                                        currentScreen = AppScreen.PaymentMethod
                                    }
                                )
                            }
                            is AppScreen.PaymentMethod -> {
                                PaymentMethodScreen(
                                    onBack = {
                                        currentScreen = AppScreen.ShippingAddress
                                    },
                                    onPlaceOrder = {
                                        currentScreen = AppScreen.OrderSuccess
                                    }
                                )
                            }
                            is AppScreen.OrderSuccess -> {
                                OrderSuccessScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToHome = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onTrackOrder = {
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

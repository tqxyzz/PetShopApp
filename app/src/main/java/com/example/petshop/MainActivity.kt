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
import com.example.petshop.feature.cart.CartViewModel
import com.example.petshop.feature.checkout.CheckoutViewModel
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
                var activeSearchQuery by remember { mutableStateOf("hạt cho chó") }
                var selectedProduct by remember { mutableStateOf<com.example.petshop.data.model.Product?>(null) }
                val cartViewModel: CartViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
                val checkoutViewModel: CheckoutViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

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
                                    onNavigateToProductDetail = { prod ->
                                        selectedProduct = prod
                                        currentScreen = AppScreen.ProductDetail
                                    },
                                    onNavigateToSearchHistory = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onNavigateToCameraScan = {
                                        currentScreen = AppScreen.CameraScan
                                    },
                                    onNavigateToCategoryProductList = { categoryTitle ->
                                        activeCategoryTitle = categoryTitle
                                        currentScreen = AppScreen.CategoryProductList
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
                                        currentScreen = AppScreen.Home
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
                                        activeSearchQuery = if (query.isNotBlank()) query else "hạt cho chó"
                                        currentScreen = AppScreen.ImageSearchResult
                                    },
                                    onProductClick = { prod ->
                                        selectedProduct = prod
                                        currentScreen = AppScreen.ProductDetail
                                    },
                                    onAddToCart = { prod ->
                                        cartViewModel.addToCart(prod)
                                        currentScreen = AppScreen.Cart
                                    }
                                )
                            }
                            is AppScreen.CameraScan -> {
                                CameraScanScreen(
                                    onBack = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onClose = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onNavigateToHistory = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onCapture = {
                                        activeSearchQuery = "hạt cho chó"
                                        currentScreen = AppScreen.ImageSearchResult
                                    }
                                )
                            }
                            is AppScreen.ImageSearchResult -> {
                                ImageSearchResultScreen(
                                    searchQuery = activeSearchQuery,
                                    onBack = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onClose = {
                                        currentScreen = AppScreen.SearchHistory
                                    },
                                    onNavigateToCameraScan = {
                                        currentScreen = AppScreen.CameraScan
                                    },
                                    onNavigateToCart = {
                                        currentScreen = AppScreen.Cart
                                    },
                                    onAddToCart = { prod ->
                                        cartViewModel.addToCart(prod)
                                        currentScreen = AppScreen.Cart
                                    },
                                    onProductClick = { prod ->
                                        selectedProduct = prod
                                        currentScreen = AppScreen.ProductDetail
                                    }
                                )
                            }
                            is AppScreen.ProductDetail -> {
                                ProductDetailScreen(
                                    product = selectedProduct,
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToCart = {
                                        currentScreen = AppScreen.Cart
                                    },
                                    onAddToCart = { prod ->
                                        cartViewModel.addToCart(prod)
                                    },
                                    onBuyNow = {
                                        if (selectedProduct != null) {
                                            checkoutViewModel.setDirectBuyNow(selectedProduct!!)
                                        }
                                        currentScreen = AppScreen.ShippingAddress
                                    }
                                )
                            }
                            is AppScreen.Cart -> {
                                CartScreen(
                                    viewModel = cartViewModel,
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToVoucherSelect = {
                                        currentScreen = AppScreen.VoucherPetPoints
                                    },
                                    onNavigateToCheckout = {
                                        checkoutViewModel.setCartCheckout(cartViewModel.cartItems)
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
                                    viewModel = checkoutViewModel,
                                    onBack = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onContinueToPayment = {
                                        currentScreen = AppScreen.PaymentMethod
                                    }
                                )
                            }
                            is AppScreen.PaymentMethod -> {
                                PaymentMethodScreen(
                                    viewModel = checkoutViewModel,
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
                                    viewModel = checkoutViewModel,
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

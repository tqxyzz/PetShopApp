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
import com.google.firebase.auth.FirebaseAuth
import com.example.petshop.feature.search.ImageSearchResultScreen
import com.example.petshop.feature.search.SearchHistoryScreen
import com.example.petshop.feature.profile.CaNhanScreen
import com.example.petshop.feature.profile.ChinhSuaHoSoScreen
import com.example.petshop.feature.profile.SoDiaChiScreen
import com.example.petshop.feature.profile.DoiMatKhauScreen
import com.example.petshop.feature.profile.ChiTietBossScreen
import com.example.petshop.feature.profile.ChinhSuaBossScreen
import com.example.petshop.feature.profile.DanhSachBossScreen
import com.example.petshop.feature.profile.ThemBossScreen
import com.example.petshop.feature.profile.HangThanhVienScreen
import com.example.petshop.feature.profile.ViPetPointsScreen
import com.example.petshop.feature.profile.LichSuPetPointsScreen
import com.example.petshop.feature.profile.TrungTamTroGiupScreen
import com.example.petshop.feature.profile.CaiDatThongBaoScreen
import com.example.petshop.feature.profile.NgonNguGiaoDienScreen
import com.example.petshop.feature.profile.BaoTriScreen
import com.example.petshop.feature.profile.NgoaiTuyenScreen
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
    object CaNhan : AppScreen()
    object ChinhSuaHoSo : AppScreen()
    object SoDiaChi : AppScreen()
    object DoiMatKhau : AppScreen()
    object ChiTietBoss : AppScreen()
    object ChinhSuaBoss : AppScreen()
    object DanhSachBoss : AppScreen()
    object ThemBoss : AppScreen()
    object HangThanhVien : AppScreen()
    object ViPetPoints : AppScreen()
    object LichSuPetPoints : AppScreen()
    object TrungTamTroGiup : AppScreen()
    object CaiDatThongBao : AppScreen()
    object NgonNguGiaoDien : AppScreen()
    object BaoTri : AppScreen()
    object NgoaiTuyen : AppScreen()
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

                val checkAuthAndProceed: (() -> Unit) -> Unit = { action ->
                    if (FirebaseAuth.getInstance().currentUser == null) {
                        currentScreen = AppScreen.Login
                    } else {
                        action()
                    }
                }

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
                                        checkAuthAndProceed { currentScreen = AppScreen.Voucher }
                                    },
                                    onNavigateToCategory = {
                                        currentScreen = AppScreen.Category
                                    },
                                    onNavigateToBrand = {
                                        currentScreen = AppScreen.Brand
                                    },
                                    onNavigateToCart = {
                                        checkAuthAndProceed { currentScreen = AppScreen.Cart }
                                    },
                                    onNavigateToProfile = {
                                        checkAuthAndProceed { currentScreen = AppScreen.CaNhan }
                                    },
                                    onNavigateToAssistant = {
                                        checkAuthAndProceed { /* Handle assistant */ }
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
                                    onBack = { currentScreen = AppScreen.Home },
                                    onNavigateToCameraScan = { currentScreen = AppScreen.CameraScan }
                                )
                            }
                            is AppScreen.CameraScan -> {
                                CameraScanScreen(
                                    onBack = { currentScreen = AppScreen.SearchHistory }
                                )
                            }
                            is AppScreen.ImageSearchResult -> {
                                ImageSearchResultScreen(
                                    searchQuery = activeSearchQuery,
                                    onBack = { currentScreen = AppScreen.SearchHistory }
                                )
                            }
                            is AppScreen.ProductDetail -> {
                                if (selectedProduct != null) {
                                    ProductDetailScreen(
                                        product = selectedProduct!!,
                                        onBack = { currentScreen = AppScreen.Home },
                                        onAddToCart = { cartViewModel.addToCart(selectedProduct!!) },
                                        onBuyNow = { currentScreen = AppScreen.Cart }
                                    )
                                } else {
                                    HomeScreen(
                                        onNavigateToFlashSale = { currentScreen = AppScreen.FlashSale },
                                        onNavigateToVoucher = { currentScreen = AppScreen.Voucher },
                                        onNavigateToCategory = { currentScreen = AppScreen.Category },
                                        onNavigateToBrand = { currentScreen = AppScreen.Brand },
                                        onNavigateToCart = { currentScreen = AppScreen.Cart },
                                        onNavigateToProductDetail = { p ->
                                            selectedProduct = p
                                            currentScreen = AppScreen.ProductDetail
                                        },
                                        onNavigateToProfile = { currentScreen = AppScreen.CaNhan }
                                    )
                                }
                            }
                            is AppScreen.Cart -> {
                                CartScreen(
                                    onBack = { currentScreen = AppScreen.Home },
                                    onNavigateToVoucherSelect = { currentScreen = AppScreen.VoucherPetPoints },
                                    onNavigateToCheckout = { currentScreen = AppScreen.ShippingAddress }
                                )
                            }
                            is AppScreen.VoucherPetPoints -> {
                                VoucherPetPointsScreen(
                                    onBack = { currentScreen = AppScreen.Cart }
                                )
                            }
                            is AppScreen.ShippingAddress -> {
                                ShippingAddressScreen(
                                    onBack = { currentScreen = AppScreen.Cart },
                                    onContinueToPayment = { currentScreen = AppScreen.PaymentMethod }
                                )
                            }
                            is AppScreen.PaymentMethod -> {
                                PaymentMethodScreen(
                                    onBack = { currentScreen = AppScreen.ShippingAddress },
                                    onPlaceOrder = { currentScreen = AppScreen.OrderSuccess }
                                )
                            }
                            is AppScreen.OrderSuccess -> {
                                OrderSuccessScreen(
                                    onNavigateToHome = { currentScreen = AppScreen.Home }
                                )
                            }
                            is AppScreen.CaNhan -> {
                                CaNhanScreen(
                                    onNavigateToEditProfile = {
                                        currentScreen = AppScreen.ChinhSuaHoSo
                                    },
                                    onNavigateToAddressBook = {
                                        currentScreen = AppScreen.SoDiaChi
                                    },
                                    onNavigateToChangePassword = {
                                        currentScreen = AppScreen.DoiMatKhau
                                    },
                                    onNavigateToPetDetail = {
                                        currentScreen = AppScreen.ChiTietBoss
                                    },
                                    onNavigateToPetList = {
                                        currentScreen = AppScreen.DanhSachBoss
                                    },
                                    onNavigateToMembership = {
                                        currentScreen = AppScreen.HangThanhVien
                                    },
                                    onNavigateToPetPoints = {
                                        currentScreen = AppScreen.ViPetPoints
                                    },
                                    onNavigateToHelpCenter = {
                                        currentScreen = AppScreen.TrungTamTroGiup
                                    },
                                    onNavigateToNotificationSettings = {
                                        currentScreen = AppScreen.CaiDatThongBao
                                    },
                                    onNavigateToLanguageAppearance = {
                                        currentScreen = AppScreen.NgonNguGiaoDien
                                    },
                                    onNavigateToHome = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToCategory = {
                                        currentScreen = AppScreen.Category
                                    },
                                    onNavigateToAssistant = {
                                        // Assistant
                                    },
                                    onLogout = {
                                        currentScreen = AppScreen.Login
                                    }
                                )
                            }
                            is AppScreen.ChinhSuaHoSo -> {
                                ChinhSuaHoSoScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    },
                                    onSaveSuccess = {
                                        currentScreen = AppScreen.CaNhan
                                    }
                                )
                            }
                            is AppScreen.SoDiaChi -> {
                                SoDiaChiScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    }
                                )
                            }
                            is AppScreen.DoiMatKhau -> {
                                DoiMatKhauScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    },
                                    onPasswordUpdated = {
                                        currentScreen = AppScreen.CaNhan
                                    }
                                )
                            }
                            is AppScreen.ChinhSuaBoss -> {
                                ChinhSuaBossScreen(
                                    onBack = {
                                        currentScreen = AppScreen.ChiTietBoss
                                    },
                                    onSaveSuccess = {
                                        currentScreen = AppScreen.ChiTietBoss
                                    }
                                )
                            }
                            is AppScreen.ChiTietBoss -> {
                                ChiTietBossScreen(
                                    onBack = {
                                        currentScreen = AppScreen.DanhSachBoss
                                    },
                                    onNavigateToEdit = {
                                        currentScreen = AppScreen.ChinhSuaBoss
                                    }
                                )
                            }
                            is AppScreen.DanhSachBoss -> {
                                DanhSachBossScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    },
                                    onNavigateToAddPet = {
                                        currentScreen = AppScreen.ThemBoss
                                    },
                                    onNavigateToPetDetail = {
                                        currentScreen = AppScreen.ChiTietBoss
                                    }
                                )
                            }
                            is AppScreen.ThemBoss -> {
                                ThemBossScreen(
                                    onBack = {
                                        currentScreen = AppScreen.DanhSachBoss
                                    },
                                    onPetAdded = {
                                        currentScreen = AppScreen.DanhSachBoss
                                    }
                                )
                            }
                            is AppScreen.HangThanhVien -> {
                                HangThanhVienScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    }
                                )
                            }
                            is AppScreen.ViPetPoints -> {
                                ViPetPointsScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    },
                                    onNavigateToHistory = {
                                        currentScreen = AppScreen.LichSuPetPoints
                                    }
                                )
                            }
                            is AppScreen.LichSuPetPoints -> {
                                LichSuPetPointsScreen(
                                    onBack = {
                                        currentScreen = AppScreen.ViPetPoints
                                    }
                                )
                            }
                            is AppScreen.CaiDatThongBao -> {
                                CaiDatThongBaoScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    }
                                )
                            }
                            is AppScreen.NgonNguGiaoDien -> {
                                NgonNguGiaoDienScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    },
                                    onSaveSuccess = {
                                        currentScreen = AppScreen.CaNhan
                                    }
                                )
                            }
                            is AppScreen.TrungTamTroGiup -> {
                                TrungTamTroGiupScreen(
                                    onBack = {
                                        currentScreen = AppScreen.CaNhan
                                    }
                                )
                            }
                            is AppScreen.BaoTri -> {
                                BaoTriScreen(
                                    onRetry = {
                                        currentScreen = AppScreen.CaNhan
                                    }
                                )
                            }
                            is AppScreen.NgoaiTuyen -> {
                                NgoaiTuyenScreen(
                                    onRetry = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onViewOfflineCart = {
                                        currentScreen = AppScreen.Home
                                    }
                                )
                            }
                        }

                        if (showForgotPasswordDialog) {
                            ForgotPasswordDialog(
                                onDismiss = { showForgotPasswordDialog = false },
                                onSubmit = { email ->
                                    // Password recovery
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

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
import com.example.petshop.feature.profile.AddPetScreen
import com.example.petshop.feature.profile.AddressBookScreen
import com.example.petshop.feature.profile.ChangePasswordScreen
import com.example.petshop.feature.profile.EditPetProfileScreen
import com.example.petshop.feature.profile.EditProfileScreen
import com.example.petshop.feature.profile.HelpCenterScreen
import com.example.petshop.feature.profile.LanguageAppearanceScreen
import com.example.petshop.feature.profile.MaintenanceScreen
import com.example.petshop.feature.profile.MembershipScreen
import com.example.petshop.feature.profile.NotificationSettingsScreen
import com.example.petshop.feature.profile.OfflineScreen
import com.example.petshop.feature.profile.PetListScreen
import com.example.petshop.feature.profile.PetPointsHistoryScreen
import com.example.petshop.feature.profile.PetPointsWalletScreen
import com.example.petshop.feature.profile.PetProfileDetailScreen
import com.example.petshop.feature.profile.ProfileScreen
import com.example.petshop.feature.splash.SplashScreen
import com.example.petshop.feature.voucher.VoucherCenterScreen
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
    object EditProfile : AppScreen()
    object Profile : AppScreen()
    object AddressBook : AppScreen()
    object ChangePassword : AppScreen()
    object EditPetProfile : AppScreen()
    object PetProfileDetail : AppScreen()
    object PetList : AppScreen()
    object AddPet : AppScreen()
    object Membership : AppScreen()
    object PetPointsWallet : AppScreen()
    object PetPointsHistory : AppScreen()
    object HelpCenter : AppScreen()
    object NotificationSettings : AppScreen()
    object LanguageAppearance : AppScreen()
    object Maintenance : AppScreen()
    object Offline : AppScreen()
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
                                    onNavigateToProfile = {
                                        currentScreen = AppScreen.Profile
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
                            is AppScreen.EditProfile -> {
                                EditProfileScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    },
                                    onSaveSuccess = {
                                        currentScreen = AppScreen.Profile
                                    }
                                )
                            }
                            is AppScreen.Profile -> {
                                ProfileScreen(
                                    onNavigateToEditProfile = {
                                        currentScreen = AppScreen.EditProfile
                                    },
                                    onNavigateToAddressBook = {
                                        currentScreen = AppScreen.AddressBook
                                    },
                                    onNavigateToChangePassword = {
                                        currentScreen = AppScreen.ChangePassword
                                    },
                                    onNavigateToPetDetail = {
                                        currentScreen = AppScreen.PetProfileDetail
                                    },
                                    onNavigateToPetList = {
                                        currentScreen = AppScreen.PetList
                                    },
                                    onNavigateToMembership = {
                                        currentScreen = AppScreen.Membership
                                    },
                                    onNavigateToPetPoints = {
                                        currentScreen = AppScreen.PetPointsWallet
                                    },
                                    onNavigateToHelpCenter = {
                                        currentScreen = AppScreen.HelpCenter
                                    },
                                    onNavigateToNotificationSettings = {
                                        currentScreen = AppScreen.NotificationSettings
                                    },
                                    onNavigateToLanguageAppearance = {
                                        currentScreen = AppScreen.LanguageAppearance
                                    },
                                    onNavigateToHome = {
                                        currentScreen = AppScreen.Home
                                    },
                                    onNavigateToCategory = {
                                        currentScreen = AppScreen.Category
                                    },
                                    onNavigateToAssistant = {
                                        // Optional
                                    },
                                    onLogout = {
                                        currentScreen = AppScreen.Login
                                    }
                                )
                            }
                            is AppScreen.AddressBook -> {
                                AddressBookScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    }
                                )
                            }
                            is AppScreen.ChangePassword -> {
                                ChangePasswordScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    },
                                    onPasswordUpdated = {
                                        currentScreen = AppScreen.Profile
                                    }
                                )
                            }
                            is AppScreen.EditPetProfile -> {
                                EditPetProfileScreen(
                                    onBack = {
                                        currentScreen = AppScreen.PetProfileDetail
                                    },
                                    onSaveSuccess = {
                                        currentScreen = AppScreen.PetProfileDetail
                                    }
                                )
                            }
                            is AppScreen.PetProfileDetail -> {
                                PetProfileDetailScreen(
                                    onBack = {
                                        currentScreen = AppScreen.PetList
                                    },
                                    onNavigateToEdit = {
                                        currentScreen = AppScreen.EditPetProfile
                                    }
                                )
                            }
                            is AppScreen.PetList -> {
                                PetListScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    },
                                    onNavigateToAddPet = {
                                        currentScreen = AppScreen.AddPet
                                    },
                                    onNavigateToPetDetail = {
                                        currentScreen = AppScreen.PetProfileDetail
                                    }
                                )
                            }
                            is AppScreen.AddPet -> {
                                AddPetScreen(
                                    onBack = {
                                        currentScreen = AppScreen.PetList
                                    },
                                    onPetAdded = {
                                        currentScreen = AppScreen.PetList
                                    }
                                )
                            }
                            is AppScreen.Membership -> {
                                MembershipScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    }
                                )
                            }
                            is AppScreen.PetPointsWallet -> {
                                PetPointsWalletScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    },
                                    onNavigateToHistory = {
                                        currentScreen = AppScreen.PetPointsHistory
                                    }
                                )
                            }
                            is AppScreen.PetPointsHistory -> {
                                PetPointsHistoryScreen(
                                    onBack = {
                                        currentScreen = AppScreen.PetPointsWallet
                                    }
                                )
                            }
                            is AppScreen.NotificationSettings -> {
                                NotificationSettingsScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    }
                                )
                            }
                            is AppScreen.LanguageAppearance -> {
                                LanguageAppearanceScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    },
                                    onSaveSuccess = {
                                        currentScreen = AppScreen.Profile
                                    }
                                )
                            }
                            is AppScreen.HelpCenter -> {
                                HelpCenterScreen(
                                    onBack = {
                                        currentScreen = AppScreen.Profile
                                    }
                                )
                            }
                            is AppScreen.Maintenance -> {
                                MaintenanceScreen(
                                    onRetry = {
                                        currentScreen = AppScreen.Profile
                                    }
                                )
                            }
                            is AppScreen.Offline -> {
                                OfflineScreen(
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

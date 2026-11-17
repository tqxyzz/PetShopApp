package com.example.avocadopetshop.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.avocadopetshop.ui.screens.*
import com.example.avocadopetshop.ui.theme.AvocadoTheme

@Composable
fun AvocadoApp() {
    var currentRoute by remember { mutableStateOf(AvocadoRoute.Gallery) }
    val navigate: (AvocadoRoute) -> Unit = { currentRoute = it }
    val back: () -> Unit = { currentRoute = AvocadoRoute.Gallery }

    BackHandler(enabled = currentRoute != AvocadoRoute.Gallery) { back() }

    AvocadoTheme {
        when (currentRoute) {
            AvocadoRoute.Gallery -> ScreenGallery(onNavigate = navigate)
            AvocadoRoute.ShippingTracking -> ShippingTrackingScreen(back, navigate)
            AvocadoRoute.FollowedBrands -> FollowedBrandsScreen(back, navigate)
            AvocadoRoute.AIChat -> AIChatScreen(back, navigate)
            AvocadoRoute.AddressShipping -> AddressShippingScreen(back, navigate)
            AvocadoRoute.Cart -> CartScreen(back, navigate)
            AvocadoRoute.AIProductSuggestions -> AIProductSuggestionsScreen(back, navigate)
            AvocadoRoute.Promotions -> PromotionsScreen(back, navigate)
            AvocadoRoute.AIAssistant -> AIAssistantScreen(back, navigate)
            AvocadoRoute.PaymentMethods -> PaymentMethodsScreen(back, navigate)
            AvocadoRoute.AIProductComparison -> AIProductComparisonScreen(back, navigate)
            AvocadoRoute.OrderDetail -> OrderDetailScreen(back, navigate)
            AvocadoRoute.OrderList -> OrderListScreen(back, navigate)
            AvocadoRoute.OrderSuccess -> OrderSuccessScreen(back, navigate)
            AvocadoRoute.RecentlyViewed -> RecentlyViewedScreen(back, navigate)
            AvocadoRoute.Favorites -> FavoritesScreen(back, navigate)
            AvocadoRoute.ProductComparison -> ProductComparisonScreen(back, navigate)
        }
    }
}

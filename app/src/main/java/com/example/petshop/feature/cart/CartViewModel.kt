package com.example.petshop.feature.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.petshop.data.model.Product

data class CartItem(
    val product: Product,
    var quantity: Int = 1,
    var selectedVariant: String = "Tiêu chuẩn"
)

class CartViewModel : ViewModel() {
    val cartItems = mutableStateListOf<CartItem>()

    var isUsePoints by mutableStateOf(true)

    init {
        // Initial default sample items if cart is empty
        if (cartItems.isEmpty()) {
            cartItems.add(
                CartItem(
                    product = Product(
                        id = "rc1",
                        brand = "ROYAL CANIN",
                        title = "Hạt Royal Canin Kitten cho mèo",
                        currentPrice = "385.000đ",
                        oldPrice = "490.000đ",
                        petType = "cat",
                        badge = "Bán chạy #1"
                    ),
                    quantity = 1,
                    selectedVariant = "Túi 2kg + Tặng Pate Ciao"
                )
            )
            cartItems.add(
                CartItem(
                    product = Product(
                        id = "cat1",
                        brand = "CATURE",
                        title = "Cát đậu nành Tofu Cature...",
                        currentPrice = "115.000đ",
                        oldPrice = "145.000đ",
                        petType = "cat"
                    ),
                    quantity = 2,
                    selectedVariant = "Hương Trà Xanh 7L"
                )
            )
            cartItems.add(
                CartItem(
                    product = Product(
                        id = "ciao1",
                        brand = "INABA CIAO",
                        title = "Súp thưởng Ciao Churu cá ngừ...",
                        currentPrice = "38.000đ",
                        oldPrice = "50.000đ",
                        petType = "cat"
                    ),
                    quantity = 1,
                    selectedVariant = "Vị Cá ngừ 4 gói"
                )
            )
        }
    }

    fun addToCart(product: Product, quantity: Int = 1) {
        val existing = cartItems.find { it.product.id == product.id || (it.product.title == product.title && product.title.isNotEmpty()) }
        if (existing != null) {
            existing.quantity += quantity
        } else {
            cartItems.add(CartItem(product = product, quantity = quantity))
        }
    }

    fun updateQuantity(cartItem: CartItem, newQty: Int) {
        if (newQty <= 0) {
            removeFromCart(cartItem)
        } else {
            val index = cartItems.indexOf(cartItem)
            if (index != -1) {
                cartItems[index] = cartItems[index].copy(quantity = newQty)
            }
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        cartItems.remove(cartItem)
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun calculateSubtotal(): Int {
        return cartItems.sumOf { parsePrice(it.product.currentPrice) * it.quantity }
    }

    fun calculateDiscount(): Int {
        var discount = 30000 // Shop voucher
        if (isUsePoints) {
            discount += 15000 // Pet Points
        }
        return discount
    }

    fun calculateShippingFee(): Int {
        return if (calculateSubtotal() > 300000) 0 else 25000
    }

    fun calculateTotalPayment(): Int {
        val total = calculateSubtotal() - calculateDiscount() + calculateShippingFee()
        return if (total < 0) 0 else total
    }

    companion object {
        fun parsePrice(priceStr: String?): Int {
            if (priceStr.isNullOrEmpty()) return 0
            val cleaned = priceStr.replace("[^0-9]".toRegex(), "")
            return cleaned.toIntOrNull() ?: 0
        }

        fun formatPrice(amount: Int): String {
            val formatter = java.text.DecimalFormat("#,###đ")
            return formatter.format(amount).replace(",", ".")
        }
    }
}

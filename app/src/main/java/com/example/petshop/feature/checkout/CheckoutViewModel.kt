package com.example.petshop.feature.checkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.petshop.data.model.Product
import com.example.petshop.feature.cart.CartItem
import com.example.petshop.feature.cart.CartViewModel

class CheckoutViewModel : ViewModel() {
    val checkoutItems = mutableStateListOf<CartItem>()

    var recipientName by mutableStateOf("Nguyễn Minh Châu")
    var recipientPhone by mutableStateOf("0908 123 456")
    var addressType by mutableStateOf("Nhà riêng")
    var fullAddress by mutableStateOf("Căn hộ 12.04, Tháp B, Chung cư Masteri Thảo Điền, 159 Xa Lộ Hà Nội, Phường Thảo Điền, Thành phố Thủ Đức, TP. Hồ Chí Minh")
    var shipperNote by mutableStateOf("")

    var selectedShippingOptionIndex by mutableStateOf(0) // 0: Hỏa tốc 2h
    var selectedPaymentMethodIndex by mutableStateOf(1) // 1: MoMo / ZaloPay

    fun setDirectBuyNow(product: Product) {
        checkoutItems.clear()
        checkoutItems.add(CartItem(product = product, quantity = 1, selectedVariant = "Tiêu chuẩn"))
    }

    fun setCartCheckout(items: List<CartItem>) {
        checkoutItems.clear()
        checkoutItems.addAll(items)
    }

    fun updateAddress(name: String, phone: String, address: String, type: String = "Nhà riêng") {
        recipientName = name
        recipientPhone = phone
        fullAddress = address
        addressType = type
    }

    fun calculateSubtotal(): Int {
        return checkoutItems.sumOf { CartViewModel.parsePrice(it.product.currentPrice) * it.quantity }
    }

    fun calculateShippingFee(): Int {
        return when (selectedShippingOptionIndex) {
            0 -> 10000 // Hỏa tốc 2H
            else -> 0
        }
    }

    fun calculateDiscount(): Int {
        return 30000 + 15000 // Voucher + Pet Points
    }

    fun calculateTotalPayment(): Int {
        val total = calculateSubtotal() - calculateDiscount() + calculateShippingFee()
        return if (total < 0) 0 else total
    }
}

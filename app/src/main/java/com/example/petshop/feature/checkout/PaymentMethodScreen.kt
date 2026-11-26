package com.example.petshop.feature.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.petshop.R
import com.example.petshop.feature.cart.BreakdownRow
import com.example.petshop.feature.cart.CartViewModel
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessText

data class PaymentOption(
    val index: Int,
    val iconEmoji: String,
    val title: String,
    val subtitle: String,
    val badge: String? = null
)

@Composable
fun PaymentMethodScreen(
    onBack: () -> Unit = {},
    onPlaceOrder: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    viewModel: CheckoutViewModel = viewModel()
) {
    val paymentOptions = listOf(
        PaymentOption(0, "💵", "Thanh toán khi nhận hàng (COD)", "Thanh toán tiền mặt trực tiếp cho shipper"),
        PaymentOption(1, "📱", "Ví MoMo / ZaloPay", "Liên kết 1 chạm, nhận voucher -10k", "Khuyên dùng"),
        PaymentOption(2, "🏦", "Thẻ ATM / Quét mã VietQR", "Chuyển khoản tức thì qua 40+ ngân hàng 24/7"),
        PaymentOption(3, "💳", "Thẻ Quốc Tế (Visa, Master, JCB)", "Bảo mật OTP 3D-Secure chống gian lận"),
        PaymentOption(4, "🐷", "Ví PetPay Trả Sau", "Mua trước trả sau 0% lãi suất", "Lãi 0%")
    )

    val subtotal = viewModel.calculateSubtotal()
    val discount = viewModel.calculateDiscount()
    val shippingFee = viewModel.calculateShippingFee()
    val totalPayment = viewModel.calculateTotalPayment()

    val subtotalFormatted = CartViewModel.formatPrice(subtotal)
    val discountFormatted = "-${CartViewModel.formatPrice(discount)}"
    val shippingFeeFormatted = CartViewModel.formatPrice(shippingFee)
    val totalPaymentFormatted = CartViewModel.formatPrice(totalPayment)

    val items = viewModel.checkoutItems

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(12.dp)
                    .background(CardSurface)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "Tổng thanh toán: ",
                                fontSize = 12.sp,
                                color = SecondaryText
                            )
                            Text(
                                text = totalPaymentFormatted,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = DangerText
                            )
                        }
                        Text(
                            text = "+120 Pet Points sẽ tích lũy",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(PrimaryCTA)
                            .clickable { onPlaceOrder() }
                            .padding(horizontal = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Đặt hàng ngay 🐾",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SecondarySurface)
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Quay lại",
                        tint = PrimaryText,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = "Phương Thức Thanh Toán",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(AdminMaroon)
                        .clickable { onNavigateToProfile() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = "Tài khoản",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // SECTION 1: Kiểm Tra Sản Phẩm Đã Chọn (Detailed Product List)
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Kiểm tra sản phẩm (${items.size} món)",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Text(
                        text = "Thay đổi",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText,
                        modifier = Modifier.clickable { onBack() }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        items.forEachIndexed { index, item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Product Thumbnail Image
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFFFF7F2))
                                        .border(1.dp, BorderDivider, RoundedCornerShape(12.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (item.product.imageUrl.isNotEmpty()) {
                                        AsyncImage(
                                            model = item.product.imageUrl,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    } else {
                                        Text(text = if (item.product.petType == "dog") "🐶" else "🐱", fontSize = 28.sp)
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                // Product Info
                                Column(modifier = Modifier.weight(1f)) {
                                    if (item.product.brand.isNotEmpty()) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(SecondarySoft)
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = item.product.brand,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = SecondarySoftText
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(2.dp))
                                    }

                                    Text(
                                        text = item.product.title,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Spacer(modifier = Modifier.height(2.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Phân loại: ${item.selectedVariant}",
                                            fontSize = 11.sp,
                                            color = MutedText
                                        )

                                        Text(
                                            text = "x${item.quantity}",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AdminMaroon
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(2.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = item.product.currentPrice,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = PrimaryText
                                        )
                                        if (!item.product.oldPrice.isNullOrEmpty()) {
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = item.product.oldPrice,
                                                fontSize = 10.sp,
                                                color = MutedText,
                                                textDecoration = TextDecoration.LineThrough
                                            )
                                        }
                                    }
                                }
                            }

                            if (index < items.size - 1) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .padding(vertical = 4.dp)
                                        .background(BorderDivider)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Shipping Destination Summary Tag
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(SecondarySurface)
                                .padding(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_location),
                                    contentDescription = null,
                                    tint = PrimaryCTA,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = "Giao đến: ${viewModel.recipientName} • ${viewModel.recipientPhone}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                    Text(
                                        text = viewModel.fullAddress,
                                        fontSize = 10.sp,
                                        color = MutedText,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // SECTION 2: Chọn Phương Thức Thanh Toán
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "💳 Chọn phương thức thanh toán",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Spacer(modifier = Modifier.height(10.dp))

                paymentOptions.forEach { option ->
                    val isSelected = option.index == viewModel.selectedPaymentMethodIndex
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .shadow(2.dp, RoundedCornerShape(14.dp), ambientColor = BorderDivider)
                            .clip(RoundedCornerShape(14.dp))
                            .background(CardSurface)
                            .border(
                                1.dp,
                                if (isSelected) AdminMaroon else BorderDivider,
                                RoundedCornerShape(14.dp)
                            )
                            .clickable { viewModel.selectedPaymentMethodIndex = option.index }
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SecondarySurface),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = option.iconEmoji, fontSize = 20.sp)
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = option.title,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                    if (option.badge != null) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(Color(0xFFFEE2E2))
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = option.badge,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = DangerText
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = option.subtitle,
                                    fontSize = 11.sp,
                                    color = MutedText
                                )
                            }

                            RadioButton(
                                selected = isSelected,
                                onClick = { viewModel.selectedPaymentMethodIndex = option.index },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = AdminMaroon,
                                    unselectedColor = BorderDivider
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // SECTION 3: Chi Tiết Thanh Toán Minh Bạch
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Text(
                        text = "🧾 Chi tiết thanh toán",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    BreakdownRow(label = "Tạm tính (${items.size} món)", value = subtotalFormatted)
                    BreakdownRow(label = "Phí vận chuyển", value = shippingFeeFormatted)
                    BreakdownRow(label = "Giảm giá voucher", value = discountFormatted, isDiscount = true)

                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(BorderDivider)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tổng thanh toán",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )

                        Text(
                            text = totalPaymentFormatted,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = AdminMaroon
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

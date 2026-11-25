package com.example.petshop.feature.cart

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText

@Composable
fun CartScreen(
    onBack: () -> Unit = {},
    onNavigateToVoucherSelect: () -> Unit = {},
    onNavigateToCheckout: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    viewModel: CartViewModel = viewModel()
) {
    val cartItems = viewModel.cartItems
    val subtotal = viewModel.calculateSubtotal()
    val discount = viewModel.calculateDiscount()
    val shippingFee = viewModel.calculateShippingFee()
    val totalPayment = viewModel.calculateTotalPayment()

    val subtotalFormatted = CartViewModel.formatPrice(subtotal)
    val discountFormatted = "-${CartViewModel.formatPrice(discount)}"
    val totalPaymentFormatted = CartViewModel.formatPrice(totalPayment)

    Scaffold(
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                // Bottom Checkout Bar
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(CircleShape)
                                    .background(AdminMaroon),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_check),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Tất cả ",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )
                                    Text(
                                        text = "(${cartItems.size})",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondaryText
                                    )
                                }
                                Row(verticalAlignment = Alignment.Bottom) {
                                    Text(
                                        text = "Tổng: ",
                                        fontSize = 12.sp,
                                        color = SecondaryText
                                    )
                                    Text(
                                        text = totalPaymentFormatted,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = AdminMaroon
                                    )
                                }
                                Text(
                                    text = "Tiết kiệm ${CartViewModel.formatPrice(discount)}",
                                    fontSize = 10.sp,
                                    color = SuccessText,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // Checkout Button
                        Box(
                            modifier = Modifier
                                .height(48.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(AdminMaroon)
                                .clickable { onNavigateToCheckout() }
                                .padding(horizontal = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Mua hàng (${cartItems.size}) ➔",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
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
                // Back Button
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
                    text = "Giỏ Hàng",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "?", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(AdminMaroon)
                            .clickable { onNavigateToProfile() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = "Tài khoản",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            if (cartItems.isEmpty()) {
                // EMPTY CART VIEW
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🛒", fontSize = 64.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Giỏ hàng của bạn đang trống!",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Hãy chọn thêm sản phẩm từ Avocado PetShop nhé.",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(PrimaryCTA)
                                .clickable { onBack() }
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = "Tiếp tục mua sắm ➔",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            } else {
                // Top Freeship Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🚚", fontSize = 18.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (subtotal >= 300000) "Đơn hàng của bạn đã đủ điều kiện Freeship!" else "Mua thêm ${CartViewModel.formatPrice(300000 - subtotal)} để được Freeship",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFFEE2E2))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Tối đa 30k",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DangerText
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Progress Bar
                        val progress = if (subtotal >= 300000) 1.0f else (subtotal.toFloat() / 300000f)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(BorderDivider)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(progress)
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(AdminMaroon)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Đã đạt ${(progress * 100).toInt()}% giá trị",
                                fontSize = 11.sp,
                                color = MutedText
                            )
                            Text(
                                text = if (subtotal >= 300000) "Đã nhận Freeship 🐾" else "Còn 1 bước nữa 🐾",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Select All Header & Clear Cart
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(AdminMaroon),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tất cả sản phẩm (${cartItems.size} món)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { viewModel.clearCart() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_trash),
                            contentDescription = "Xóa tất cả",
                            tint = DangerText,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Xóa tất cả",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = DangerText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // SELLER ITEMS CONTAINER CARD
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        // Seller Title Row
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(AdminMaroon),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_check),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(10.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFFFEE2E2))
                                    .padding(horizontal = 4.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "MALL",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DangerText
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Avocado Official Mall ✔",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_chevron_right),
                                contentDescription = null,
                                tint = MutedText,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Dynamic Cart Items List
                        cartItems.forEachIndexed { index, item ->
                            DynamicCartItemRow(
                                item = item,
                                onQtyIncrease = { viewModel.updateQuantity(item, item.quantity + 1) },
                                onQtyDecrease = { viewModel.updateQuantity(item, item.quantity - 1) },
                                onDelete = { viewModel.removeFromCart(item) }
                            )

                            if (index < cartItems.size - 1) {
                                Spacer(modifier = Modifier.height(14.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(BorderDivider.copy(alpha = 0.5f))
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // VOUCHER & PET POINTS SECTION CARD
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        // Voucher Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNavigateToVoucherSelect() },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_ticket),
                                    contentDescription = null,
                                    tint = PrimaryCTA,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Avocado Voucher",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                    Text(
                                        text = "Đã áp dụng giảm giá",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DangerText
                                    )
                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(SecondarySoft)
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "Giảm ${CartViewModel.formatPrice(discount)}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondarySoftText
                                    )
                                }
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_chevron_right),
                                    contentDescription = null,
                                    tint = MutedText,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Pet Points Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_paw),
                                    contentDescription = null,
                                    tint = SuccessText,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "Dùng Pet Points ",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryText
                                        )
                                        Text(
                                            text = "(-15.000đ)",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = DangerText
                                        )
                                    }
                                    Text(
                                        text = "Khả dụng: 3.250 điểm Avocado Club",
                                        fontSize = 11.sp,
                                        color = MutedText
                                    )
                                }
                            }

                            Switch(
                                checked = viewModel.isUsePoints,
                                onCheckedChange = { viewModel.isUsePoints = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = AdminMaroon
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // PAYMENT BREAKDOWN CARD
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Chi tiết thanh toán",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        BreakdownRow(label = "Tạm tính hàng hóa", value = subtotalFormatted)
                        BreakdownRow(label = "Giảm giá voucher & tích điểm", value = discountFormatted, isDiscount = true)
                        BreakdownRow(
                            label = "Phí vận chuyển ước tính",
                            value = if (shippingFee == 0) "0đ (Freeship)" else CartViewModel.formatPrice(shippingFee),
                            oldValue = if (shippingFee == 0) "25.000đ" else null
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(BorderDivider)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Thành tiền tạm tính",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                                Text(
                                    text = "Đã tiết kiệm được ${CartViewModel.formatPrice(discount)}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SuccessText
                                )
                            }

                            Text(
                                text = totalPaymentFormatted,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = AdminMaroon
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Footer Commitment text
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = SuccessText,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "100% chính hãng • Đổi trả 7 ngày miễn phí cho thú cưng",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun DynamicCartItemRow(
    item: CartItem,
    onQtyIncrease: () -> Unit,
    onQtyDecrease: () -> Unit,
    onDelete: () -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(AdminMaroon),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(10.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Product Image Thumbnail
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFFFF7F2)),
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
                    Text(
                        text = if (item.product.petType == "dog") "🐶" else "🐱",
                        fontSize = 36.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.product.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    // Delete trash icon
                    Icon(
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "Xóa khỏi giỏ",
                        tint = DangerText,
                        modifier = Modifier
                            .size(16.dp)
                            .clickable { onDelete() }
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Variant selector dropdown tag
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SecondarySurface)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = item.selectedVariant,
                        fontSize = 10.sp,
                        color = SecondarySoftText,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.product.currentPrice,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryCTA
                    )
                    if (!item.product.oldPrice.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = item.product.oldPrice,
                            fontSize = 11.sp,
                            color = MutedText,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Stepper Quantity Control
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(12.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onQtyDecrease() },
                    contentAlignment = Alignment.Center
                ) { Text("-", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryText) }

                Text(
                    text = "${item.quantity}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onQtyIncrease() },
                    contentAlignment = Alignment.Center
                ) { Text("+", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryText) }
            }
        }
    }
}

@Composable
fun BreakdownRow(
    label: String,
    value: String,
    oldValue: String? = null,
    isDiscount: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 12.sp, color = SecondaryText)
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (oldValue != null) {
                Text(
                    text = oldValue,
                    fontSize = 11.sp,
                    color = MutedText,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDiscount) DangerText else PrimaryText
            )
        }
    }
}

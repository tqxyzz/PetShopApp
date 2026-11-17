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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.feature.cart.BreakdownRow
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText

@Composable
fun PaymentMethodScreen(
    onBack: () -> Unit = {},
    onPlaceOrder: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    var selectedPaymentMethodIndex by remember { mutableStateOf(1) } // 1: MoMo / ZaloPay
    var isVatInvoiceRequested by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            // Fixed Bottom Checkout Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(12.dp)
                    .background(CardSurface)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Column {
                    // Assurance Badges Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🛡 Bảo mật PCI-DSS • 🔄 Đổi trả 7 ngày • ✔ Chính hãng 100%",
                            fontSize = 10.sp,
                            color = MutedText
                        )
                    }

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
                                    text = "618.000đ",
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

                        // Place Order Button
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
                    text = "Phuong Thuc Thanh Toan",
                    fontSize = 17.sp,
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

            // Security SSL Banner Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondarySurface)
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = DangerText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Thanh toán bảo mật chuẩn SSL 256-bit",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Bảo vệ thông tin & hoàn tiền 100% nếu có sự cố",
                            fontSize = 11.sp,
                            color = SecondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ORDER SUMMARY COMPACT CARD
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider)
                    .clip(RoundedCornerShape(20.dp))
                    .background(CardSurface)
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
                            Icon(
                                painter = painterResource(id = R.drawable.ic_paw),
                                contentDescription = null,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Đơn từ Avocado Mall (3 món)",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                        }

                        Text(
                            text = "Chi tiết >",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Product Thumbnails Stack
                        Row(horizontalArrangement = Arrangement.spacedBy((-8).dp)) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFFF7F2))
                                    .border(1.dp, BorderDivider, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) { Text("🐱", fontSize = 20.sp) }

                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFFF7F2))
                                    .border(1.dp, BorderDivider, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) { Text("🌱", fontSize = 20.sp) }

                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFFF7F2))
                                    .border(1.dp, BorderDivider, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) { Text("🐟", fontSize = 20.sp) }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = "Gói yêu thương Boss",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Text(
                                text = "Royal Canin, Cát Tofu & Súp Ciao",
                                fontSize = 11.sp,
                                color = MutedText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Delivery tag
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondarySurface)
                            .padding(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🚚", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Giao Hỏa tốc 2H ",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color(0xFFFEE2E2))
                                            .padding(horizontal = 4.dp, vertical = 1.dp)
                                    ) {
                                        Text(
                                            text = "Ưu tiên",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = DangerText
                                        )
                                    }
                                }
                                Text(
                                    text = "Masteri Thảo Điền, P. Thảo Điền, TP. Thủ Đức",
                                    fontSize = 11.sp,
                                    color = MutedText
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // SECTION: Phương thức thanh toán
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_credit_card),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Phương thức thanh toán",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SuccessBg)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "An toàn 100%",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Option 1: COD
                PaymentOptionCard(
                    iconEmoji = "💵",
                    title = "Thanh toán khi nhận hàng (COD)",
                    subtitle = "Thanh toán tiền mặt cho shipper khi nhận gói hàng thức ăn cho bé",
                    isSelected = selectedPaymentMethodIndex == 0,
                    onClick = { selectedPaymentMethodIndex = 0 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Option 2: MoMo / ZaloPay (Selected)
                PaymentOptionCard(
                    iconEmoji = "📱",
                    title = "Ví MoMo / ZaloPay",
                    badgeText = "Khuyên dùng",
                    subtitle = "Liên kết thanh toán 1 chạm, nhận thêm voucher hoàn xu 10.000đ",
                    tags = listOf("Voucher -10k hoàn tiền", "⚡ Xác nhận tức thì"),
                    isSelected = selectedPaymentMethodIndex == 1,
                    onClick = { selectedPaymentMethodIndex = 1 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Option 3: ATM / VietQR
                PaymentOptionCard(
                    iconEmoji = "🏦",
                    title = "Thẻ ATM / Quét mã VietQR",
                    subtitle = "Chuyển khoản tức thì miễn phí qua tất cả các ngân hàng Việt Nam (24/7)",
                    isSelected = selectedPaymentMethodIndex == 2,
                    onClick = { selectedPaymentMethodIndex = 2 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Option 4: Thẻ Quốc Tế
                PaymentOptionCard(
                    iconEmoji = "💳",
                    title = "Thẻ Quốc Tế (Visa, Master, JCB)",
                    subtitle = "Bảo mật chuẩn quốc tế OTP 3D-Secure 2.0 chống gian lận thẻ",
                    isSelected = selectedPaymentMethodIndex == 3,
                    onClick = { selectedPaymentMethodIndex = 3 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Option 5: PetPay Trả Sau
                PaymentOptionCard(
                    iconEmoji = "🐷",
                    title = "Ví PetPay Trả Sau",
                    badgeText = "Lãi 0%",
                    subtitle = "Mua trước trả sau 0% lãi suất • Hạn mức khả dụng: 2.000.000đ (Kỳ hạn 30 ngày)",
                    isSelected = selectedPaymentMethodIndex == 4,
                    onClick = { selectedPaymentMethodIndex = 4 }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // E-INVOICE REQUEST ROW
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🧾", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Yêu cầu hóa đơn điện tử (VAT)",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Text(
                                text = "Cho công ty, hộ kinh doanh hoặc phòng khám thú y",
                                fontSize = 11.sp,
                                color = MutedText
                            )
                        }
                    }

                    Switch(
                        checked = isVatInvoiceRequested,
                        onCheckedChange = { isVatInvoiceRequested = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = AdminMaroon
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TRANSPARENT BREAKDOWN CARD
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider)
                    .clip(RoundedCornerShape(20.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🧾", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Chi tiết thanh toán minh bạch",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    BreakdownRow(label = "Tổng tiền hàng (3 món)", value = "653.000đ")
                    BreakdownRow(label = "Phí vận chuyển hỏa tốc 2H", value = "35.000đ")
                    BreakdownRow(label = "Giảm giá voucher shop (Pet Care)", value = "-30.000đ", isDiscount = true)
                    BreakdownRow(label = "Giảm giá vận chuyển FastShip", value = "-25.000đ", isDiscount = true)
                    BreakdownRow(label = "Giảm trừ điểm thưởng Pet Points (1.500 xu)", value = "-15.000đ", isDiscount = true)
                    BreakdownRow(label = "Dịch vụ gói quà kèm nơ cho Boss", value = "+5.000đ")

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
                                text = "Tổng thanh toán",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Text(
                                text = "(Đã bao gồm 8% thuế GTGT)",
                                fontSize = 10.sp,
                                color = MutedText
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "618.000đ",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = AdminMaroon
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(SuccessBg)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "+120 Pet Points tích lũy",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SuccessText
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PaymentOptionCard(
    iconEmoji: String,
    title: String,
    subtitle: String,
    badgeText: String? = null,
    tags: List<String> = emptyList(),
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(
                1.dp,
                if (isSelected) AdminMaroon else BorderDivider,
                RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SecondarySurface),
                contentAlignment = Alignment.Center
            ) { Text(text = iconEmoji, fontSize = 22.sp) }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    if (badgeText != null) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFFEE2E2))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = badgeText,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = DangerText
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = MutedText,
                    lineHeight = 15.sp
                )

                if (tags.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        tags.forEach { tag ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(SecondarySurface)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = tag,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondarySoftText
                                )
                            }
                        }
                    }
                }
            }

            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = AdminMaroon,
                    unselectedColor = BorderDivider
                )
            )
        }
    }
}

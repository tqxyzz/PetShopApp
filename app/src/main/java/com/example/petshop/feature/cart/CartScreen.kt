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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onNavigateToProfile: () -> Unit = {}
) {
    var isUsePoints by remember { mutableStateOf(true) }
    var item1Qty by remember { mutableStateOf(1) }
    var item2Qty by remember { mutableStateOf(2) }
    var item3Qty by remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
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
                                    text = "(3)",
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
                                    text = "608.000đ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = AdminMaroon
                                )
                            }
                            Text(
                                text = "Tiết kiệm 164.000đ",
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
                            text = "Mua hàng (3) ➔",
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
                    text = "Gio Hang",
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
                                text = "Mua thêm 115.000đ để được Freeship toàn quốc",
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(BorderDivider)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
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
                            text = "Đã đạt 75% giá trị",
                            fontSize = 11.sp,
                            color = MutedText
                        )
                        Text(
                            text = "Còn 1 bước nữa 🐾",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Select All Header
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
                        text = "Chọn tất cả (3 món)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "Xóa đã chọn",
                        tint = MutedText,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Xóa đã chọn",
                        fontSize = 12.sp,
                        color = MutedText
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

                    // Item 1: Royal Canin Kitten
                    CartItemRow(
                        emoji = "🐱",
                        title = "Hạt Royal Canin Kitten cho mèo",
                        variant = "Túi 2kg + Tặng Pate Ciao ▾",
                        price = "385.000đ",
                        oldPrice = "490.000đ",
                        stockText = "Còn 12 gói",
                        qty = item1Qty,
                        onQtyChange = { item1Qty = it },
                        giftText = "🎁 Quà tặng 0đ: 01 Gói Pate Ciao Churu cá ngừ (Trị giá 15.000đ)"
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Item 2: Cature Tofu Litter
                    CartItemRow(
                        emoji = "🌱",
                        title = "Cát đậu nành Tofu Cature...",
                        variant = "Hương Trà Xanh 7L ▾",
                        price = "115.000đ",
                        oldPrice = "145.000đ",
                        subtotalText = "Tổng: 230.000đ",
                        qty = item2Qty,
                        onQtyChange = { item2Qty = it }
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Item 3: Ciao Churu Treat
                    CartItemRow(
                        emoji = "🐟",
                        title = "Súp thưởng Ciao Churu cá ngừ...",
                        variant = "Vị Cá ngừ 4 gói ▾",
                        price = "38.000đ",
                        oldPrice = "50.000đ",
                        tagText = "Mua kèm giá tốt",
                        qty = item3Qty,
                        onQtyChange = { item3Qty = it }
                    )
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
                                    text = "Đã chọn 2 mã (-50.000đ)",
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
                                    text = "Tiết kiệm 50k",
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
                                        text = "Dùng 1.500 Pet Points ",
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
                            checked = isUsePoints,
                            onCheckedChange = { isUsePoints = it },
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

                    BreakdownRow(label = "Tạm tính hàng hóa", value = "653.000đ")
                    BreakdownRow(label = "Giảm giá voucher shop", value = "-30.000đ", isDiscount = true)
                    BreakdownRow(label = "Điểm Pet Points khấu trừ", value = "-15.000đ", isDiscount = true)
                    BreakdownRow(
                        label = "Phí vận chuyển ước tính",
                        value = "0đ (Freeship)",
                        oldValue = "25.000đ"
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
                                text = "Đã tiết kiệm được 164.000đ",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }

                        Text(
                            text = "608.000đ",
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

@Composable
fun CartItemRow(
    emoji: String,
    title: String,
    variant: String,
    price: String,
    oldPrice: String,
    stockText: String? = null,
    subtotalText: String? = null,
    tagText: String? = null,
    giftText: String? = null,
    qty: Int = 1,
    onQtyChange: (Int) -> Unit = {}
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
                Text(text = emoji, fontSize = 36.sp)
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Variant selector dropdown tag
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SecondarySurface)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = variant,
                        fontSize = 10.sp,
                        color = SecondarySoftText,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = price,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryCTA
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = oldPrice,
                        fontSize = 11.sp,
                        color = MutedText,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                if (stockText != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SuccessBg)
                            .padding(horizontal = 6.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = stockText,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                } else if (subtotalText != null) {
                    Text(
                        text = subtotalText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryText
                    )
                } else if (tagText != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SuccessBg)
                            .padding(horizontal = 6.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = tagText,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                }
            }

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
                        .clickable { if (qty > 1) onQtyChange(qty - 1) },
                    contentAlignment = Alignment.Center
                ) { Text("-", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryText) }

                Text(
                    text = "$qty",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onQtyChange(qty + 1) },
                    contentAlignment = Alignment.Center
                ) { Text("+", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryText) }
            }
        }

        if (giftText != null) {
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(SecondarySurface)
                    .padding(8.dp)
            ) {
                Text(
                    text = giftText,
                    fontSize = 11.sp,
                    color = SecondarySoftText,
                    fontWeight = FontWeight.Medium
                )
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

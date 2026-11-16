package com.example.petshop.feature.voucher

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.data.model.VoucherDetail
import com.example.petshop.data.model.sampleVoucherDetails
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.IconPrimaryBg
import com.example.petshop.ui.theme.IconPrimaryTint
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PetShopTheme
import com.example.petshop.ui.theme.PlaceholderText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText

@Composable
fun VoucherCenterScreen(
    onBack: () -> Unit,
    onNavigateToCart: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var promoCodeInput by remember { mutableStateOf("") }
    var selectedFilterCategory by remember { mutableStateOf("Tất cả") }
    var myVouchersList by remember { mutableStateOf(sampleVoucherDetails) }
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            // Sticky Bottom Wallet Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .shadow(12.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(RoundedCornerShape(24.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(SecondarySoft),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_wallet),
                                contentDescription = null,
                                tint = SecondarySoftText,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Ví Voucher của tôi",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Text(
                                text = "4 mã đã lưu đang khả dụng",
                                fontSize = 11.sp,
                                color = MutedText
                            )
                        }
                    }

                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AdminMaroon,
                            contentColor = Color.White
                        ),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                        modifier = Modifier.height(38.dp)
                    ) {
                        Text(
                            text = "Xem ví →",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            // 1. Top Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SecondarySoft)
                            .border(1.dp, BorderDivider, CircleShape)
                            .clickable { onBack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "←",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Trung Tâm Voucher",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryText
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = PrimaryText,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .clickable { onNavigateToCart() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shopping_bag),
                            contentDescription = null,
                            tint = PrimaryText,
                            modifier = Modifier.size(18.dp)
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = 2.dp, y = (-2).dp)
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(PrimaryCTA),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "3",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(AdminMaroon),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // 2. Pet Points Reward Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(PrimaryCTA),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_paw),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "ĐIỂM TÍCH LŨY",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MutedText,
                                    letterSpacing = 0.5.sp
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(SecondarySoft)
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Bơ Vàng VIP",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondarySoftText
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            Row(verticalAlignment = Alignment.Bottom) {
                                Text(
                                    text = "520",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = PrimaryText
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Pet Points",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = SecondaryText,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(CardSurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(18.dp))
                            .clickable { }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Lịch sử >",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 3. Voucher Promo Code Input Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = null,
                        tint = MutedText,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(modifier = Modifier.weight(1f)) {
                        if (promoCodeInput.isEmpty()) {
                            Text(
                                text = "NHẬP MÃ VOUCHER GIẢM GIÁ...",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = PlaceholderText,
                                letterSpacing = 0.5.sp
                            )
                        }

                        BasicTextField(
                            value = promoCodeInput,
                            onValueChange = { promoCodeInput = it },
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryCTA,
                            contentColor = Color.White
                        ),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text(
                            text = "Áp dụng",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Voucher Category Filter Chips Row
            val filterChips = listOf("Tất cả 12", "Freeship", "Giảm giá", "Khác")
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filterChips) { chip ->
                    val isSelected = chip.contains(selectedFilterCategory, ignoreCase = true) || (chip.contains("Tất cả") && selectedFilterCategory == "Tất cả")
                    val displayText = when {
                        chip.contains("Tất cả") -> "🐾 Tất cả 12"
                        chip == "Freeship" -> "🚚 Freeship"
                        chip == "Giảm giá" -> "% Giảm giá"
                        else -> "⚙ Khác"
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) PrimaryCTA else CardSurface)
                            .border(
                                1.dp,
                                if (isSelected) PrimaryCTA else BorderDivider,
                                RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                selectedFilterCategory = when {
                                    chip.contains("Tất cả") -> "Tất cả"
                                    chip == "Freeship" -> "Freeship"
                                    chip == "Giảm giá" -> "Giảm giá"
                                    else -> "Khác"
                                }
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = displayText,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else SecondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 5. Voucher Cards List
            val filteredVouchers = myVouchersList.filter { item ->
                selectedFilterCategory == "Tất cả" || item.category.contains(selectedFilterCategory, ignoreCase = true)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                filteredVouchers.forEach { voucher ->
                    VoucherDetailCard(
                        voucher = voucher,
                        onToggleSave = { targetVoucher ->
                            myVouchersList = myVouchersList.map {
                                if (it.id == targetVoucher.id) it.copy(isSaved = !it.isSaved) else it
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 6. Saving Tip Card (Mẹo nhỏ tiết kiệm)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(IconPrimaryBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bulb),
                            contentDescription = null,
                            tint = IconPrimaryTint,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Mẹo nhỏ tiết kiệm",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = SecondaryText)) {
                                    append("Bạn có thể áp dụng đồng thời ")
                                }
                                withStyle(style = SpanStyle(color = SecondarySoftText, fontWeight = FontWeight.ExtraBold)) {
                                    append("01 Mã Giảm Giá")
                                }
                                withStyle(style = SpanStyle(color = SecondaryText)) {
                                    append(" cùng ")
                                }
                                withStyle(style = SpanStyle(color = SecondarySoftText, fontWeight = FontWeight.ExtraBold)) {
                                    append("01 Mã Freeship")
                                }
                                withStyle(style = SpanStyle(color = SecondaryText)) {
                                    append(" trên mỗi đơn hàng!")
                                }
                            },
                            fontSize = 12.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun VoucherDetailCard(
    voucher: VoucherDetail,
    onToggleSave: (VoucherDetail) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                // Top-Left Icon Box
                val (iconBg, iconTextColor) = when (voucher.iconBgType) {
                    "green" -> Color(0xFFDCFCE7) to Color(0xFF15803D)
                    "pink" -> Color(0xFFFFE2DC) to SecondarySoftText
                    "brown" -> Color(0xFFFFF1EA) to SecondarySoftText
                    "beige" -> Color(0xFFFFE8E2) to SecondarySoftText
                    else -> Color(0xFFDCFCE7) to Color(0xFF15803D)
                }

                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = voucher.iconEmoji, fontSize = 26.sp)
                        Text(
                            text = voucher.badge1.take(8),
                            fontSize = 8.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = iconTextColor
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Right Content Body
                Column(modifier = Modifier.weight(1f)) {
                    // Badges Row
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(iconBg)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = voucher.badge1,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = iconTextColor
                            )
                        }

                        if (!voucher.badge2.isNullOrEmpty()) {
                            Spacer(modifier = Modifier.width(6.dp))
                            val isHot = voucher.badge2.contains("Hot")
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (isHot) Color(0xFFFEF3C7) else Color(0xFFFEE2E2))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = voucher.badge2,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isHot) Color(0xFFB45309) else DangerText
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Title
                    Text(
                        text = voucher.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryText
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    // Subtitle
                    Text(
                        text = voucher.subtitle,
                        fontSize = 11.sp,
                        lineHeight = 15.sp,
                        color = SecondaryText
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // HSD Text
                    Text(
                        text = voucher.hsdText,
                        fontSize = 11.sp,
                        color = MutedText
                    )

                    // Usage Progress Bar if applicable
                    if (voucher.usagePercent != null) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Đã dùng ${voucher.usagePercent}%",
                                fontSize = 10.sp,
                                color = MutedText
                            )
                            if (voucher.usageText != null) {
                                Text(
                                    text = voucher.usageText,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DangerText
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(BorderDivider)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth((voucher.usagePercent / 100f))
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(PrimaryCTA)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BorderDivider)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Card Footer Action Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = voucher.footerLeftText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (voucher.isSaved) SuccessText else MutedText,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                when (voucher.buttonType) {
                    "already_saved" -> {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(SuccessBg)
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "✓ Đã lưu",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    }
                    "exchange" -> {
                        Button(
                            onClick = { onToggleSave(voucher) },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF325239),
                                contentColor = Color.White
                            ),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                            modifier = Modifier.height(34.dp)
                        ) {
                            Text(
                                text = "⇄ Đổi ngay",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    else -> {
                        Button(
                            onClick = { onToggleSave(voucher) },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (voucher.isSaved) SuccessBg else PrimaryCTA,
                                contentColor = if (voucher.isSaved) SuccessText else Color.White
                            ),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                            modifier = Modifier.height(34.dp)
                        ) {
                            Text(
                                text = if (voucher.isSaved) "✓ Đã lưu" else "Lưu mã",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VoucherCenterScreenPreview() {
    PetShopTheme {
        VoucherCenterScreen(onBack = {})
    }
}

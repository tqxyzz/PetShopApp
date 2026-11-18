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
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
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
fun VoucherPetPointsScreen(
    onBack: () -> Unit = {},
    onConfirm: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    var promoCodeInput by remember { mutableStateOf("") }
    var isPointsEnabled by remember { mutableStateOf(true) }
    var selectedFreeshipIndex by remember { mutableStateOf(0) }
    var selectedShopVoucherIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            // Fixed Bottom Action Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(12.dp)
                    .background(CardSurface)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                tint = SuccessText,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Đã chọn 2 voucher + 1.500 điểm",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryText
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Tiết kiệm: ", fontSize = 12.sp, color = SecondaryText)
                            Text(
                                text = "75.000đ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = DangerText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Confirm Primary Button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(PrimaryCTA)
                            .clickable { onConfirm() },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Xác nhận áp dụng ưu đãi",
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
                    text = "Khuyen Mai Va Pet Points",
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

            // Coupon Code Input Section
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Nhập mã khuyến mãi",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(SecondarySurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (promoCodeInput.isEmpty()) {
                            Text(
                                text = "NHẬP MÃ ƯU ĐÃI HOẶC N...",
                                fontSize = 13.sp,
                                color = PlaceholderText
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(PrimaryCTA)
                            .clickable { }
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Áp dụng",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // PET POINTS REDEMPTION CARD
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
                            Icon(
                                painter = painterResource(id = R.drawable.ic_leaf),
                                contentDescription = null,
                                tint = SuccessText,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Pet Points ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SuccessBg)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Thành viên VIP",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SuccessText
                                )
                            }
                        }

                        Switch(
                            checked = isPointsEnabled,
                            onCheckedChange = { isPointsEnabled = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = AdminMaroon
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Số dư: 3.250 điểm (~32.500đ)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryText
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Points Input Field
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(CardSurface)
                                .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "1.500 ",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                                Text(
                                    text = "điểm (-15.000đ)",
                                    fontSize = 12.sp,
                                    color = MutedText
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .height(44.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(SecondarySoft)
                                .clickable { }
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Tối đa",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_leaf),
                            contentDescription = null,
                            tint = SuccessText,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Tích thêm +120 điểm khi bạn hoàn tất đơn hàng này",
                            fontSize = 11.sp,
                            color = SecondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // SECTION: Mã Miễn Phí Vận Chuyển
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🚚", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mã Miễn Phí Vận Chuyển",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }
                    Text(
                        text = "Áp dụng tối đa 1 mã",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Freeship Option 1
                VoucherOptionCard(
                    iconText = "FREE",
                    tagText = "Toàn quốc",
                    title = "Freeship Toàn Quốc",
                    subtitle = "Giảm tối đa 30.000đ cho đơn từ...",
                    expiryText = "⏰ Còn 3 ngày",
                    isSelected = selectedFreeshipIndex == 0,
                    onClick = { selectedFreeshipIndex = 0 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Freeship Option 2
                VoucherOptionCard(
                    iconText = "2H",
                    tagText = "Hỏa tốc",
                    title = "Freeship Hỏa Tốc 2H",
                    subtitle = "Giảm 25.000đ cho đơn từ 400.000đ",
                    expiryText = "⏰ Hết hạn hôm nay",
                    isSelected = selectedFreeshipIndex == 1,
                    onClick = { selectedFreeshipIndex = 1 }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // SECTION: Voucher Avocado Mall & Shop
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_ticket),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Voucher Avocado Mall & Shop",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }
                    Text(
                        text = "Áp dụng tối đa 1 mã",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Shop Voucher 1
                VoucherOptionCard(
                    iconText = "PATE",
                    tagText = "Hot Deal Sen Cưng",
                    title = "Giảm 30.000đ đơn thức ăn & pate",
                    subtitle = "Đơn tối thiểu 500.000đ",
                    expiryText = "📅 HSD: 30/11/2024",
                    isSelected = selectedShopVoucherIndex == 0,
                    onClick = { selectedShopVoucherIndex = 0 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Shop Voucher 2
                VoucherOptionCard(
                    iconText = "10%",
                    tagText = "Đồ cho mèo",
                    title = "Giảm 10% (Tối đa 50.000đ)",
                    subtitle = "Áp dụng đơn từ 350.000đ",
                    expiryText = "⏰ Còn 5 ngày",
                    isSelected = selectedShopVoucherIndex == 1,
                    onClick = { selectedShopVoucherIndex = 1 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Shop Voucher 3 (Disabled / Needs more amount)
                VoucherOptionCard(
                    iconText = "-50K",
                    tagText = "Đơn lớn",
                    title = "Giảm 50.000đ cho đơn từ 799.000đ",
                    subtitle = "Cần mua thêm 146.000đ để áp dụng",
                    actionButtonText = "Mua thêm",
                    isSelected = false,
                    onClick = { }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TIP BOX FOR SEN
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(SuccessBg)
                    .padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bulb),
                        contentDescription = null,
                        tint = SuccessText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Mẹo cho Sen: Kết hợp Mã Freeship + Voucher Shop + Điểm thưởng giúp bạn tiết kiệm tối đa lên đến 35% hóa đơn!",
                        fontSize = 12.sp,
                        color = SuccessText,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun VoucherOptionCard(
    iconText: String,
    tagText: String,
    title: String,
    subtitle: String,
    expiryText: String? = null,
    actionButtonText: String? = null,
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
                if (isSelected) PrimaryCTA else BorderDivider,
                RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Square Box
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SecondarySurface),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = iconText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = SecondarySoftText
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Voucher Details
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(SecondarySurface)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = tagText,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Spacer(modifier = Modifier.height(1.dp))

                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = if (actionButtonText != null) DangerText else MutedText
                )

                if (expiryText != null) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = expiryText,
                        fontSize = 10.sp,
                        color = MutedText
                    )
                }
            }

            // Radio Button or Buy More Button
            if (actionButtonText != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(SecondarySurface)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = actionButtonText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            } else {
                RadioButton(
                    selected = isSelected,
                    onClick = onClick,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = PrimaryCTA,
                        unselectedColor = BorderDivider
                    )
                )
            }
        }
    }
}

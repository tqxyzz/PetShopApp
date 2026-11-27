package com.example.petshop.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.theme.*

@Composable
fun NgonNguGiaoDienScreen(
    onBack: () -> Unit,
    onSaveSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedLanguage by remember { mutableStateOf("Tiếng Việt (Mặc định)") }
    var selectedTheme by remember { mutableStateOf("Sáng ấm áp (Light Warm)") }
    var fontSizeScale by remember { mutableStateOf("Chuẩn (100%)") }
    var meowSound by remember { mutableStateOf(true) }
    var hapticFeedback by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, CircleShape)
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "‹",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            Text(
                text = "Ngôn Ngữ & Giao Diện",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(PrimaryCTA.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_leaf),
                    contentDescription = "Appearance",
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Top Experience Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(SecondarySoft),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Góc Nhỏ Avocado",
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
                                text = "Tùy chỉnh riêng",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Cá nhân hóa ngôn ngữ, màu sắc giao diện ấm áp cùng âm thanh cưng xỉu mỗi khi đặt hạt và phụ kiện cho Boss!",
                        fontSize = 11.sp,
                        color = SecondaryText,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        // 1. Language Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ticket),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Ngôn ngữ hiển thị (Language)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            val languages = listOf(
                Triple("Tiếng Việt (Mặc định)", "Trải nghiệm đầy đủ nhất", "🇻🇳"),
                Triple("English (US)", "English Language support", "🇺🇸"),
                Triple("日本語", "Japanese language support", "🇯🇵"),
                Triple("한국어", "Korean language support", "🇰🇷")
            )

            languages.forEach { (langTitle, langSubtitle, flag) ->
                val isSelected = selectedLanguage == langTitle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isSelected) SecondarySoft else SecondarySurface)
                        .border(
                            1.dp,
                            if (isSelected) PrimaryCTA else BorderDivider,
                            RoundedCornerShape(14.dp)
                        )
                        .clickable { selectedLanguage = langTitle }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = flag, fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = langTitle,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = langSubtitle,
                            fontSize = 11.sp,
                            color = MutedText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) PrimaryCTA else CardSurface)
                            .border(
                                2.dp,
                                if (isSelected) PrimaryCTA else BorderDivider,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Text(text = "✓", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                        }
                    }
                }
            }
        }

        // 2. Appearance Theme Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_leaf),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Giao diện màu sắc (Theme)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val themes = listOf(
                    Triple("Sáng ấm áp (Light Warm)", "Giao diện Avocado chuẩn", R.drawable.ic_star),
                    Triple("Tối êm dịu (Dark Mode)", "Bảo vệ mắt ban đêm", R.drawable.ic_lock),
                    Triple("Theo hệ thống", "Tự động đồng bộ", R.drawable.ic_shield_check)
                )

                themes.forEach { (themeTitle, themeSubtitle, iconRes) ->
                    val isSelected = selectedTheme == themeTitle
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(130.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) SecondarySoft else SecondarySurface)
                            .border(
                                1.dp,
                                if (isSelected) PrimaryCTA else BorderDivider,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { selectedTheme = themeTitle }
                            .padding(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = iconRes),
                                    contentDescription = null,
                                    tint = if (isSelected) PrimaryCTA else MutedText,
                                    modifier = Modifier.size(16.dp)
                                )
                                if (isSelected) {
                                    Box(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .clip(CircleShape)
                                            .background(PrimaryCTA),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "✓", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                                    }
                                }
                            }

                            Column {
                                Text(
                                    text = themeTitle,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText,
                                    maxLines = 2
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = themeSubtitle,
                                    fontSize = 10.sp,
                                    color = MutedText,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3. Font Size Scale Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Kích thước chữ (Font Size)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SecondarySoft)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = fontSizeScale,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val scales = listOf("Nhỏ (90%)", "Chuẩn (100%)", "Lớn (115%)", "Đại (130%)")
                scales.forEach { scale ->
                    val isSelected = fontSizeScale == scale
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) AdminMaroon else SecondarySurface)
                            .border(
                                1.dp,
                                if (isSelected) AdminMaroon else BorderDivider,
                                RoundedCornerShape(12.dp)
                            )
                            .clickable { fontSizeScale = scale },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = scale.split(" ")[0],
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) OnPrimaryText else SecondaryText
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SecondarySurface)
                    .padding(14.dp)
            ) {
                Text(
                    text = "Mẫu văn bản hiển thị: 🐾 Avocado PetShop chuyên cung cấp hạt hữu cơ & phụ kiện chính hãng cho Boss cưng!",
                    fontSize = when (fontSizeScale) {
                        "Nhỏ (90%)" -> 11.sp
                        "Lớn (115%)" -> 14.sp
                        "Đại (130%)" -> 16.sp
                        else -> 12.sp
                    },
                    color = PrimaryText,
                    lineHeight = 18.sp
                )
            }
        }

        // 4. Sound & Haptics Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Âm thanh & Phản hồi xúc giác",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Âm thanh tiếng \"Meo\" khi đặt hàng",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Phát âm thanh cưng xỉu khi hoàn tất đơn cho Boss",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Switch(
                    checked = meowSound,
                    onCheckedChange = { meowSound = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnPrimaryText,
                        checkedTrackColor = PrimaryCTA,
                        uncheckedThumbColor = OnPrimaryText,
                        uncheckedTrackColor = BorderDivider
                    )
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Rung phản hồi (Haptics)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Rung nhẹ khi chạm chọn tab hoặc bấm nút",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Switch(
                    checked = hapticFeedback,
                    onCheckedChange = { hapticFeedback = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnPrimaryText,
                        checkedTrackColor = PrimaryCTA,
                        uncheckedThumbColor = OnPrimaryText,
                        uncheckedTrackColor = BorderDivider
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Button(
                onClick = { onSaveSuccess() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryCTA)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = OnPrimaryText,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Lưu cài đặt giao diện",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimaryText
                    )
                }
            }
        }
    }
}

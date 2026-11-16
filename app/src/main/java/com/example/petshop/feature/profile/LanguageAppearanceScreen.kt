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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.theme.*

@Composable
fun LanguageAppearanceScreen(
    onBack: () -> Unit,
    onSaveSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedLanguage by remember { mutableStateOf("Tiếng Việt") }
    var selectedTheme by remember { mutableStateOf("Sáng ấm áp (Light Warm)") }
    var soundMeow by remember { mutableStateOf(true) }
    var haptics by remember { mutableStateOf(true) }

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
                    painter = painterResource(id = R.drawable.ic_bulb),
                    contentDescription = "Theme",
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Top Banner Card
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SecondarySoft)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "🐾 Trải nghiệm dành riêng cho Sen",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Tùy biến góc nhỏ Avocado theo phong cách của bạn",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Cài đặt trực quan, mượt mà và ấm áp từng thao tác chạm.",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(SecondarySurface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        }

        // Section: Ngôn ngữ hiển thị (4 languages)
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
                Text(
                    text = "🌐 Ngôn ngữ hiển thị",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SecondarySoft)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "4 ngôn ngữ",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }

            val languages = listOf(
                Triple("Tiếng Việt", "Giao diện và trợ lý AI tối ưu cho Sen Việt", "Mặc định"),
                Triple("English (Tiếng Anh)", "English interface & AI assistance", null),
                Triple("日本語 (Tiếng Nhật)", "Nhật ngữ tiêu chuẩn cho bé cưng", null),
                Triple("한국어 (Tiếng Hàn)", "Giao diện tiếng Hàn thân thiện", null)
            )

            languages.forEach { (lang, desc, badge) ->
                val isSelected = selectedLanguage == lang.substringBefore(" (")
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
                        .clickable { selectedLanguage = lang.substringBefore(" (") }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = lang,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            if (badge != null) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(AdminMaroon)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(text = badge, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = desc,
                            fontSize = 11.sp,
                            color = MutedText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) PrimaryCTA else CardSurface)
                            .border(1.dp, if (isSelected) PrimaryCTA else BorderDivider, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Text(text = "✓", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                        }
                    }
                }
            }
        }

        // Section: Chế độ giao diện (Appearance)
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
                Text(
                    text = "☀️ Chế độ giao diện",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
                Text(
                    text = "Ấm cúng",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryCTA
                )
            }

            // Theme 1: Light Warm
            val isLightSelected = selectedTheme.contains("Light Warm")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isLightSelected) SecondarySoft else SecondarySurface)
                    .border(
                        1.dp,
                        if (isLightSelected) PrimaryCTA else BorderDivider,
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { selectedTheme = "Sáng ấm áp (Light Warm)" }
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
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
                            painter = painterResource(id = R.drawable.ic_bulb),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Sáng ấm áp (Light Warm)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isLightSelected) PrimaryCTA else CardSurface)
                            .border(1.dp, if (isLightSelected) PrimaryCTA else BorderDivider, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isLightSelected) {
                            Text(text = "✓", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                        }
                    }
                }

                // Mini Preview Box inside Light Warm
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardSurface)
                        .padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(SecondarySoft),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_paw), contentDescription = null, tint = PrimaryCTA, modifier = Modifier.size(12.dp))
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(8.dp)
                                .background(BorderDivider, RoundedCornerShape(4.dp))
                        )
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(DangerBg),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = null, tint = DangerText, modifier = Modifier.size(12.dp))
                        }
                    }
                }

                Text(
                    text = "Tông màu kem đào ấm cúng đặc trưng của Avocado PetShop, giữ nét vui tươi và êm dịu.",
                    fontSize = 11.sp,
                    color = MutedText
                )
            }

            // Theme 2: Dark Mode
            val isDarkSelected = selectedTheme.contains("Dark Mode")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isDarkSelected) SecondarySoft else SecondarySurface)
                    .border(
                        1.dp,
                        if (isDarkSelected) PrimaryCTA else BorderDivider,
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { selectedTheme = "Tối êm dịu (Dark Mode)" }
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
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
                            painter = painterResource(id = R.drawable.ic_lock),
                            contentDescription = null,
                            tint = MutedText,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Tối êm dịu (Dark Mode)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isDarkSelected) PrimaryCTA else CardSurface)
                            .border(1.dp, if (isDarkSelected) PrimaryCTA else BorderDivider, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isDarkSelected) {
                            Text(text = "✓", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                        }
                    }
                }

                // Mini Preview Box inside Dark Mode
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF29170C))
                        .padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF4E2F1D)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_paw), contentDescription = null, tint = PrimaryCTA, modifier = Modifier.size(12.dp))
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(8.dp)
                                .background(Color(0xFF76533F), RoundedCornerShape(4.dp))
                        )
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF8C332E)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = null, tint = OnPrimaryText, modifier = Modifier.size(12.dp))
                        }
                    }
                }

                Text(
                    text = "Dị mắt khi chăm thú cưng đêm, bảo vệ mắt ban đêm, tiết kiệm pin cho màn hình OLED.",
                    fontSize = 11.sp,
                    color = MutedText
                )
            }

            // Theme 3: System
            val isSystemSelected = selectedTheme.contains("System")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isSystemSelected) SecondarySoft else SecondarySurface)
                    .border(
                        1.dp,
                        if (isSystemSelected) PrimaryCTA else BorderDivider,
                        RoundedCornerShape(16.dp)
                    )
                    .clickable { selectedTheme = "Theo hệ thống thiết bị (System)" }
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_store),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Theo hệ thống thiết bị (System)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = PrimaryText)
                    Text(text = "Tự động đồng bộ theo cài đặt Android của máy", fontSize = 10.sp, color = MutedText)
                }
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(if (isSystemSelected) PrimaryCTA else CardSurface)
                        .border(1.dp, if (isSystemSelected) PrimaryCTA else BorderDivider, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSystemSelected) {
                        Text(text = "✓", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                    }
                }
            }
        }

        // Section: Cỡ chữ hiển thị (Font Size Slider Preview)
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
                Text(
                    text = "🔤 Cỡ chữ hiển thị",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SecondarySoft)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "Tiêu chuẩn",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }

            // Slider points row
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("Nhỏ", "Tiêu chuẩn", "Lớn", "Rất lớn").forEach { size ->
                        Text(
                            text = size,
                            fontSize = 11.sp,
                            fontWeight = if (size == "Tiêu chuẩn") FontWeight.Bold else FontWeight.Medium,
                            color = if (size == "Tiêu chuẩn") PrimaryCTA else MutedText
                        )
                    }
                }

                // Visual slider bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(BorderDivider)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.35f)
                            .fillMaxHeight()
                            .background(PrimaryCTA, RoundedCornerShape(3.dp))
                    )
                }
            }

            // Preview Text Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_bulb), contentDescription = null, tint = PrimaryCTA, modifier = Modifier.size(14.dp))
                        Text(text = "Hộp xem trước văn bản", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryText)
                    }
                    Text(
                        text = "\"Xem trước: Boss Miu rất thích hạt Orijen và pate cá hồi tươi! Giao diện chữ rõ ràng giúp Sen đặt hàng dễ dàng hơn.\"",
                        fontSize = 12.sp,
                        color = SecondaryText,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "✓ Tối ưu cho cả người lớn tuổi và các bạn nhỏ đọc app",
                        fontSize = 10.sp,
                        color = SuccessText,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Section: Âm thanh & Rung phản hồi (Haptics & Sound)
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
                Text(
                    text = "🔊 Âm thanh & Rung phản hồi",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
                Text(
                    text = "Tương tác vui",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondarySoftText
                )
            }

            // Sound Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Âm thanh tiếng \"Meo / Gâu\"",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Phát tiếng kêu đáng yêu khi hoàn tất đặt hàng thành công",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }
                Switch(
                    checked = soundMeow,
                    onCheckedChange = { soundMeow = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnPrimaryText,
                        checkedTrackColor = PrimaryCTA,
                        uncheckedThumbColor = OnPrimaryText,
                        uncheckedTrackColor = BorderDivider
                    )
                )
            }

            // Haptics Toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Rung phản hồi nhẹ (Haptics)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Nhịp rung tactile dịu êm mỗi khi bấm chọn sản phẩm hoặc nút giỏ hàng",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }
                Switch(
                    checked = haptics,
                    onCheckedChange = { haptics = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnPrimaryText,
                        checkedTrackColor = PrimaryCTA,
                        uncheckedThumbColor = OnPrimaryText,
                        uncheckedTrackColor = BorderDivider
                    )
                )
            }
        }

        // Bottom Save Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
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

            Text(
                text = "⏱ Mọi thay đổi sẽ được lưu ngay tức thì trên ứng dụng này",
                fontSize = 11.sp,
                color = MutedText
            )
        }
    }
}

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
import com.example.petshop.ui.components.PetShopTextField
import com.example.petshop.ui.theme.*

@Composable
fun ThemBossScreen(
    onBack: () -> Unit,
    onPetAdded: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedPetType by remember { mutableStateOf("Mèo con") }
    var petName by remember { mutableStateOf("") }
    var petBreed by remember { mutableStateOf("Mèo Anh lông ngắn") }
    var selectedGender by remember { mutableStateOf("Bé Trai") }
    var isSterilized by remember { mutableStateOf("Đã làm") }
    var birthDate by remember { mutableStateOf("08/15/2023") }
    var weight by remember { mutableStateOf("3.8") }
    var activityLevel by remember { mutableStateOf("Bình thường") }
    var allergyNotes by remember { mutableStateOf("") }
    var bossPriority by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
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
                text = "Thêm Hồ Sơ Thú Cưng",
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
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "Profile",
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Avatar Upload Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(SecondarySurface)
                        .border(2.dp, PrimaryCTA, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = "Avatar",
                        tint = PrimaryCTA,
                        modifier = Modifier.size(50.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(PrimaryCTA)
                        .clickable { /* Upload avatar */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "Camera",
                        tint = OnPrimaryText,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Chạm để tải ảnh bé cưng",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryCTA
            )
            Text(
                text = "Hỗ trợ JPG, PNG (tối đa 5MB)",
                fontSize = 11.sp,
                color = MutedText
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Pet Type & Basic Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "🐾 Bé cưng của bạn là?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val petTypes = listOf("Cún cưng", "Mèo con", "Khác")
                petTypes.forEach { type ->
                    val isSelected = selectedPetType == type
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) AdminMaroon else SecondarySurface)
                            .border(
                                1.dp,
                                if (isSelected) AdminMaroon else BorderDivider,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { selectedPetType = type },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_paw),
                                contentDescription = null,
                                tint = if (isSelected) OnPrimaryText else PrimaryCTA,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = type,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) OnPrimaryText else PrimaryText
                            )
                        }
                    }
                }
            }

            PetShopTextField(
                value = petName,
                onValueChange = { petName = it },
                label = "Tên ở nhà của bé",
                placeholder = "VD: Bơ, Miu, Đậu Đậu, Bánh Bao...",
                isRequired = true,
                trailingLabel = "Thân mật & dễ nhớ"
            )

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                PetShopTextField(
                    value = petBreed,
                    onValueChange = { petBreed = it },
                    label = "Giống loài cụ thể",
                    placeholder = "Nhập giống loài"
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val breeds = listOf("Mèo Anh", "Mèo Ta", "Mèo Ba Tư", "Poodle")
                    breeds.forEach { b ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(SecondarySurface)
                                .border(1.dp, BorderDivider, RoundedCornerShape(10.dp))
                                .clickable { petBreed = b }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(text = b, fontSize = 11.sp, color = SecondaryText, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Giới tính", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = SecondaryText)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("Bé Trai", "Bé Gái").forEach { g ->
                            val isSel = selectedGender == g
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSel) PrimaryCTA else SecondarySurface)
                                    .clickable { selectedGender = g },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (g == "Bé Trai") "♂ Bé Trai" else "♀ Bé Gái",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSel) OnPrimaryText else SecondaryText
                                )
                            }
                        }
                    }
                }

                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Triệt sản", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = SecondaryText)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("Đã làm", "Chưa").forEach { s ->
                            val isSel = isSterilized == s
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSel) PrimaryCTA else SecondarySurface)
                                    .clickable { isSterilized = s },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = s,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSel) OnPrimaryText else SecondaryText
                                )
                            }
                        }
                    }
                }
            }

            Column {
                PetShopTextField(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    label = "Sinh nhật hoặc ngày đón bé",
                    placeholder = "MM/DD/YYYY",
                    trailingLabel = "Nhận quà tặng 🎁"
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Avocado sẽ tự động gửi mã ưu đãi phụ kiện và pate hạt thơm ngon vào tuần sinh nhật Boss.",
                    fontSize = 11.sp,
                    color = MutedText,
                    lineHeight = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Physical & Lifestyle
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    text = "Thể trạng & Lối sống",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        PetShopTextField(
                            value = weight,
                            onValueChange = { weight = it },
                            label = "Cân nặng hiện tại",
                            placeholder = "3.8"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SuccessBg)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "✓ Dáng người lý tưởng",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                    Text(
                        text = "Phù hợp kích thước đệm & chén ăn cỡ M",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Mức độ vận động hàng ngày",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val activities = listOf(
                        Triple("Thích nằm lười", "Ít di chuyển", R.drawable.ic_store),
                        Triple("Bình thường", "Dạo chơi vừa đủ", R.drawable.ic_paw),
                        Triple("Rất hiếu động", "Chạy nhảy liên tục", R.drawable.ic_lightning)
                    )
                    activities.forEach { (title, subtitle, iconRes) ->
                        val isSelected = activityLevel == title
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(90.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) SecondarySoft else SecondarySurface)
                                .border(
                                    1.dp,
                                    if (isSelected) PrimaryCTA else BorderDivider,
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable { activityLevel = title }
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = iconRes),
                                    contentDescription = null,
                                    tint = if (isSelected) SecondarySoftText else PrimaryCTA,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = title,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                                Text(
                                    text = subtitle,
                                    fontSize = 9.sp,
                                    color = MutedText,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Favorite Taste & Allergies
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Khẩu vị bé mê nhất",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Text(
                    text = "Chọn nhiều",
                    fontSize = 12.sp,
                    color = MutedText,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(AdminMaroon).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "✓ Pate cá ngừ", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(AdminMaroon).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "✓ Hạt vị thịt gà", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "+ Súp thưởng tôm", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "+ Thịt bò sấy thăng hoa", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "+ Cá hồi áp chảo", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "+ Cỏ mèo bạc hà", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Món bé không thích hoặc dị ứng",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText
                )
                PetShopTextField(
                    value = allergyNotes,
                    onValueChange = { allergyNotes = it },
                    label = "",
                    placeholder = "VD: Bé không thích mùi cá trích, dị ứng bơ đậu phộng..."
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Default Priority Boss Switch Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SecondarySoft),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Boss ưu tiên mặc định",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Tự động gợi ý đồ chơi & thức ăn ngay khi mở app",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Switch(
                    checked = bossPriority,
                    onCheckedChange = { bossPriority = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnPrimaryText,
                        checkedTrackColor = PrimaryCTA,
                        uncheckedThumbColor = OnPrimaryText,
                        uncheckedTrackColor = BorderDivider
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ai_assistant),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "AI của Avocado PetShop sẽ phân tích hồ sơ này để thiết kế thực đơn dinh dưỡng và tìm kiếm phụ kiện vừa vặn nhất cho bé!",
                    fontSize = 12.sp,
                    color = SecondaryText,
                    lineHeight = 17.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Button(
                onClick = { onPetAdded() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AdminMaroon)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = OnPrimaryText,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Hoàn tất tạo hồ sơ thú cưng",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimaryText
                    )
                }
            }
        }
    }
}

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
fun ChinhSuaBossScreen(
    onBack: () -> Unit,
    onSaveSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var petName by remember { mutableStateOf("Miu") }
    var petNickname by remember { mutableStateOf("Miu Miu Béo Ú") }
    var petWeight by remember { mutableStateOf("3.5") }
    var isLazyMovement by remember { mutableStateOf(false) }
    var priorityPet by remember { mutableStateOf(true) }
    var birthdayNotification by remember { mutableStateOf(true) }
    var petNotes by remember { mutableStateOf("Bé thích ăn hạt trộn cùng nửa gói pate vào buổi tối; hơi kén uống nước từ chén thường, thích vòi nước tuần hoàn.") }

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
                text = "Chỉnh Sửa Hồ Sơ Thú Cưng",
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

        // Avatar & Boss ID Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size(100.dp)) {
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
                        contentDescription = "Pet Avatar",
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
                        .clickable { /* Change avatar */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "Change",
                        tint = OnPrimaryText,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(SecondarySoft)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = SecondarySoftText,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Hồ sơ Boss cưng #01",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Cập nhật vóc dáng để gợi ý khẩu phần chuẩn xác nhất",
                fontSize = 11.sp,
                color = MutedText
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Basic Info Card Container
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
                    painter = painterResource(id = R.drawable.ic_store),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Thông tin cơ bản",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            PetShopTextField(
                value = petName,
                onValueChange = { petName = it },
                label = "Tên bé cưng",
                placeholder = "Nhập tên bé cưng",
                isRequired = true
            )

            PetShopTextField(
                value = petNickname,
                onValueChange = { petNickname = it },
                label = "Biệt danh thân mật khi gọi ăn",
                placeholder = "Nhập biệt danh",
                trailingLabel = "🐰"
            )

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Giống loài",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                        .clickable { /* Select breed */ }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Mèo Anh lông ngắn",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "›",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MutedText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Body & Physical Condition Card Container
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
                        painter = painterResource(id = R.drawable.ic_leaf),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Vóc dáng & Thể trạng",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(SuccessBg)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = null,
                            tint = SuccessText,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "AI chuẩn lượng",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                }
            }

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        PetShopTextField(
                            value = petWeight,
                            onValueChange = { petWeight = it },
                            label = "Cân nặng mới nhất của bé",
                            placeholder = "3.5"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.padding(top = 22.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SuccessBg)
                                .padding(horizontal = 10.dp, vertical = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "↗ +0.3 kg (tháng trước)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SecondarySurface)
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bulb),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Cập nhật cân nặng giúp tính toán chính xác lượng thức ăn và hạt dinh dưỡng hàng ngày cho bé.",
                        fontSize = 11.sp,
                        color = SecondaryText,
                        lineHeight = 15.sp
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Giai đoạn phát triển",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(CardSurface),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Mèo con đang lớn",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = "2 – 12 tháng tuổi • Nhu cầu đạm cao",
                            fontSize = 11.sp,
                            color = MutedText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(SecondarySoft)
                            .clickable { /* Change stage */ }
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Đổi",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }
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
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (!isLazyMovement) SecondarySurface else SecondarySoft)
                            .border(
                                1.dp,
                                if (!isLazyMovement) BorderDivider else PrimaryCTA,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { isLazyMovement = false }
                            .padding(12.dp)
                    ) {
                        Column {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_store),
                                contentDescription = null,
                                tint = if (!isLazyMovement) MutedText else PrimaryCTA,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Lười vận động",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Text(
                                text = "Thích ngủ, cuộn tròn",
                                fontSize = 10.sp,
                                color = MutedText
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isLazyMovement) SecondarySurface else PrimaryCTA)
                            .border(
                                1.dp,
                                if (isLazyMovement) BorderDivider else PrimaryCTA,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable { isLazyMovement = true }
                            .padding(12.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_lightning),
                                    contentDescription = null,
                                    tint = if (isLazyMovement) MutedText else OnPrimaryText,
                                    modifier = Modifier.size(18.dp)
                                )
                                if (!isLazyMovement) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_shield_check),
                                        contentDescription = "Selected",
                                        tint = OnPrimaryText,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Năng động",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isLazyMovement) PrimaryText else OnPrimaryText
                            )
                            Text(
                                text = "Thích vồ bóng, chạy nhảy",
                                fontSize = 10.sp,
                                color = if (isLazyMovement) MutedText else OnPrimaryText.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Favorite Taste & Notes
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
                        text = "Khẩu vị yêu thích",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Text(
                    text = "Đã chọn 3 món",
                    fontSize = 12.sp,
                    color = MutedText,
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = "Các món bé mê tít để Avocado ưu tiên xếp vào giỏ hàng mua định kỳ:",
                fontSize = 12.sp,
                color = SecondaryText
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(PrimaryCTA).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "🐾 Pate cá hồi Na Uy  ✕", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(PrimaryCTA).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "🐾 Hạt không ngũ cốc (Grain-free)  ✕", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(PrimaryCTA).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "🐾 Ức gà xé sấy thăng hoa  ✕", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = OnPrimaryText)
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "+ Súp thưởng vị cá ngừ", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 6.dp)) {
                        Text(text = "+ Cỏ mèo bạc hà (Catnip)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Ghi chú thói quen đặc biệt của Boss",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText
                )
                PetShopTextField(
                    value = petNotes,
                    onValueChange = { petNotes = it },
                    label = "",
                    placeholder = "Nhập ghi chú thói quen..."
                )
                Text(
                    text = "Trợ lý thực đơn sẽ tự nhắc combo chén nước & bát đôi",
                    fontSize = 11.sp,
                    color = MutedText
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Priority Settings
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
                    painter = painterResource(id = R.drawable.ic_lightning),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Cài đặt ưu tiên",
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
                        text = "Đặt làm Boss ưu tiên",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Hiển thị đầu trang chủ & cá nhân hóa trợ lý AI",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Switch(
                    checked = priorityPet,
                    onCheckedChange = { priorityPet = it },
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
                        text = "Thông báo ưu đãi sinh nhật",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Nhận voucher hạt & đồ chơi giảm 30% tháng sinh nhật bé Miu",
                        fontSize = 11.sp,
                        color = MutedText,
                        lineHeight = 15.sp
                    )
                }

                Switch(
                    checked = birthdayNotification,
                    onCheckedChange = { birthdayNotification = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnPrimaryText,
                        checkedTrackColor = PrimaryCTA,
                        uncheckedThumbColor = OnPrimaryText,
                        uncheckedTrackColor = BorderDivider
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bottom Action Buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
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
                        text = "Lưu thay đổi hồ sơ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimaryText
                    )
                }
            }

            Button(
                onClick = { /* Delete pet */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DangerBg)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cart_add),
                        contentDescription = null,
                        tint = DangerText,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Xóa hồ sơ bé cưng này",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DangerText
                    )
                }
            }
        }
    }
}

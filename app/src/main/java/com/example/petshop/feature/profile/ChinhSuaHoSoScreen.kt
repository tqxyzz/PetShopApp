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
import com.example.petshop.ui.components.PetShopTextField
import com.example.petshop.ui.theme.*

@Composable
fun ChinhSuaHoSoScreen(
    onBack: () -> Unit,
    onSaveSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    var fullName by remember { mutableStateOf("Nguyễn Thùy Linh") }
    var nickname by remember { mutableStateOf("Linh Sen Cưng") }
    var phoneNumber by remember { mutableStateOf("0987 654 321") }
    var email by remember { mutableStateOf("thuylinh.pet@email.com") }
    var selectedGender by remember { mutableStateOf("Nữ") }
    var birthDate by remember { mutableStateOf("18/06/1998") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(rememberScrollState())
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
                text = "Chỉnh Sửa Hồ Sơ",
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

        // Avatar Section
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
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = "Avatar",
                        tint = PrimaryCTA,
                        modifier = Modifier.size(50.dp)
                    )
                }

                // Camera Badge
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(PrimaryCTA)
                        .clickable { /* Change avatar action */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "Change Avatar",
                        tint = OnPrimaryText,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ℹ️ Hỗ trợ định dạng PNG, JPG tối đa 5MB",
                fontSize = 12.sp,
                color = MutedText
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Personal Info Card Container
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
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Hồ sơ cá nhân",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            // Họ và tên
            Column {
                PetShopTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = "Họ và tên",
                    placeholder = "Nhập họ và tên",
                    isRequired = true
                )
            }

            // Biệt danh bé cưng hay gọi
            Column {
                PetShopTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = "Biệt danh bé cưng hay gọi",
                    placeholder = "Nhập biệt danh",
                    trailingLabel = "Tối đa 20 ký tự"
                )
            }

            // Số điện thoại
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        PetShopTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = "Số điện thoại",
                            placeholder = "Nhập số điện thoại"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(SuccessBg)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shield_check),
                                contentDescription = null,
                                tint = SuccessText,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Đã xác thực",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    }
                }
            }

            // Email with Change Button
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        PetShopTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = "Hộp thư điện tử (Email)",
                            placeholder = "Nhập email"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Thay đổi",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText,
                        modifier = Modifier
                            .clickable { /* Change email action */ }
                            .padding(4.dp)
                    )
                }
            }

            // Giới tính
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Giới tính",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val genders = listOf("Nữ", "Nam", "Khác")
                    genders.forEach { gender ->
                        val isSelected = selectedGender == gender
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (isSelected) PrimaryCTA else SecondarySurface)
                                .border(
                                    1.dp,
                                    if (isSelected) PrimaryCTA else BorderDivider,
                                    RoundedCornerShape(14.dp)
                                )
                                .clickable { selectedGender = gender },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = when (gender) {
                                    "Nữ" -> "♀ Nữ"
                                    "Nam" -> "♂ Nam"
                                    else -> "♡ Khác"
                                },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isSelected) OnPrimaryText else SecondaryText
                            )
                        }
                    }
                }
            }

            // Ngày sinh nhật Sen
            Column {
                PetShopTextField(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    label = "Ngày sinh nhật Sen",
                    placeholder = "DD/MM/YYYY"
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_leaf),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Avocado gửi quà bất ngờ cho bé cưng vào tháng sinh nhật bạn",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Social Links Card Container
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
                    painter = painterResource(id = R.drawable.ic_ticket),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Liên kết mạng xã hội",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            // Google Item
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Tài khoản Google",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "✓ Đã kết nối an toàn",
                        fontSize = 11.sp,
                        color = SuccessText,
                        fontWeight = FontWeight.Medium
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = "Locked",
                    tint = MutedText,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Facebook Item
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Tài khoản Facebook",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Chưa đồng bộ danh bạ",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(SecondarySoft)
                        .clickable { /* Link Facebook */ }
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Liên kết",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
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
                Text(
                    text = "💾 Lưu thay đổi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnPrimaryText
                )
            }

            Button(
                onClick = { onBack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SecondarySurface)
            ) {
                Text(
                    text = "Hủy bỏ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryText
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

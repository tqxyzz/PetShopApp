package com.example.petshop.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.petshop.R
import com.example.petshop.ui.components.PetShopTextField
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.IconPrimaryBg
import com.example.petshop.ui.theme.IconPrimaryTint
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondaryText

@Composable
fun ForgotPasswordDialog(
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    var emailOrPhone by remember { mutableStateOf("") }
    var sentSuccess by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Icon Header
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(IconPrimaryBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null,
                        tint = IconPrimaryTint,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = if (sentSuccess) "Kiểm tra hòm thư!" else "Quên mật khẩu?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (sentSuccess)
                        "Mã xác nhận khôi phục mật khẩu đã được gửi tới $emailOrPhone."
                    else
                        "Nhập email hoặc số điện thoại đã đăng ký để nhận mã khôi phục mật khẩu.",
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = SecondaryText,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))

                if (!sentSuccess) {
                    PetShopTextField(
                        value = emailOrPhone,
                        onValueChange = { emailOrPhone = it },
                        label = "Email / Số điện thoại",
                        placeholder = "Nhập email hoặc SĐT",
                        leadingIconRes = R.drawable.ic_mail,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (emailOrPhone.isNotBlank()) {
                                sentSuccess = true
                                onSubmit(emailOrPhone)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryCTA)
                    ) {
                        Text(
                            text = "Gửi mã khôi phục",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                } else {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryCTA)
                    ) {
                        Text(
                            text = "Đã hiểu",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = onDismiss) {
                    Text(
                        text = "Đóng",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MutedText
                    )
                }
            }
        }
    }
}

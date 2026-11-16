package com.example.petshop.feature.auth

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.components.AvocadoLogoCircle
import com.example.petshop.ui.components.PetShopTextField
import com.example.petshop.ui.components.SocialButton
import com.example.petshop.ui.components.SocialIconButton
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PetShopTheme
import com.example.petshop.ui.theme.PlaceholderText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    onForgotPassword: () -> Unit,
    modifier: Modifier = Modifier
) {
    var accountInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var rememberLogin by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        // Mascot Logo Circle
        AvocadoLogoCircle(circleSize = 110.dp)

        Spacer(modifier = Modifier.height(16.dp))

        // Subhead Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySoft)
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Text(
                text = "♥ Cộng đồng yêu thú cưng",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SecondarySoftText
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Title
        Text(
            text = "Chào mừng bạn trở lại!",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = PrimaryText,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Subtitle
        Text(
            text = "Đăng nhập để chăm sóc bé cưng tốt hơn\nmỗi ngày",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SecondaryText,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // White Card Surface Form
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp)
        ) {
            Column {
                // Email / Phone field
                PetShopTextField(
                    value = accountInput,
                    onValueChange = {
                        accountInput = it
                        errorMessage = null
                    },
                    label = "Số điện thoại hoặc Email",
                    placeholder = "Nhập số điện thoại hoặc email",
                    leadingIconRes = R.drawable.ic_mail,
                    trailingLabel = "Bắt buộc",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                PetShopTextField(
                    value = passwordInput,
                    onValueChange = {
                        passwordInput = it
                        errorMessage = null
                    },
                    label = "Mật khẩu",
                    placeholder = "Nhập mật khẩu của bạn",
                    leadingIconRes = R.drawable.ic_lock,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Options Row: Remember Me & Forgot Password
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { rememberLogin = !rememberLogin }
                    ) {
                        Checkbox(
                            checked = rememberLogin,
                            onCheckedChange = { rememberLogin = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = PrimaryCTA,
                                uncheckedColor = BorderDivider
                            ),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Ghi nhớ đăng nhập",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = PrimaryText
                        )
                    }

                    Text(
                        text = "Quên mật khẩu?",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText,
                        modifier = Modifier.clickable { onForgotPassword() }
                    )
                }

                if (errorMessage != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = errorMessage!!,
                        fontSize = 12.sp,
                        color = Color(0xFFDC2626),
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Primary Submit Button
                Button(
                    onClick = {
                        if (accountInput.isBlank() || passwordInput.isBlank()) {
                            errorMessage = "Vui lòng nhập đầy đủ thông tin đăng nhập"
                        } else {
                            onNavigateToHome()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(26.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryCTA,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = "Đăng nhập →",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Divider: —— Hoặc đăng nhập nhanh bằng ——
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(BorderDivider)
            )
            Text(
                text = "Hoặc đăng nhập nhanh bằng",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = PlaceholderText,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(BorderDivider)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Social Buttons Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialButton(
                iconRes = R.drawable.ic_google,
                text = "Google",
                onClick = { onNavigateToHome() },
                modifier = Modifier.weight(1f)
            )

            SocialButton(
                iconRes = R.drawable.ic_facebook,
                text = "Facebook",
                onClick = { onNavigateToHome() },
                modifier = Modifier.weight(1f)
            )

            SocialIconButton(
                iconRes = R.drawable.ic_fingerprint,
                onClick = { onNavigateToHome() },
                iconTint = AdminMaroon
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Footer: Chưa có tài khoản? Đăng ký ngay
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MutedText, fontWeight = FontWeight.Normal)) {
                    append("Chưa có tài khoản? ")
                }
                withStyle(style = SpanStyle(color = SecondarySoftText, fontWeight = FontWeight.Bold)) {
                    append("Đăng ký ngay")
                }
            },
            fontSize = 14.sp,
            modifier = Modifier
                .clickable { onNavigateToRegister() }
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Guest Mode Pill Button: 👁 Khám phá ngay không cần đăng nhập
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                .clickable { onNavigateToHome() }
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eye),
                    contentDescription = null,
                    tint = MutedText,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Khám phá ngay không cần đăng nhập",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SecondaryText
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PetShopTheme {
        LoginScreen(
            onNavigateToRegister = {},
            onNavigateToHome = {},
            onForgotPassword = {}
        )
    }
}

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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.components.PetShopTextField
import com.example.petshop.ui.components.SocialButton
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var fullNameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var phoneInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var confirmPasswordInput by remember { mutableStateOf("") }
    var agreeTerms by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        // Top Bar: Back Button Left & App Tag Right
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Circular Button
            Box(
                modifier = Modifier
                    .size(42.dp)
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

            // App Tag Right
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Avocado PetShop",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Subhead Badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySoft)
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "🥐 GIA NHẬP HỘI SEN & CƯNG",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondarySoftText
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Title
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Tạo tài khoản mới ",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryText
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_paw),
                contentDescription = null,
                tint = PrimaryCTA,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Subtitle
        Text(
            text = "Tham gia cộng đồng yêu thú cưng Avocado PetShop ngay hôm nay!",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = SecondaryText
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Form Card Surface
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
                // Full name field
                PetShopTextField(
                    value = fullNameInput,
                    onValueChange = {
                        fullNameInput = it
                        errorMessage = null
                    },
                    label = "Họ và tên",
                    placeholder = "Nhập họ và tên của bạn",
                    isRequired = true,
                    leadingIconRes = R.drawable.ic_person
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email field
                PetShopTextField(
                    value = emailInput,
                    onValueChange = {
                        emailInput = it
                        errorMessage = null
                    },
                    label = "Email đăng nhập",
                    placeholder = "Nhập địa chỉ email của bạn",
                    isRequired = true,
                    leadingIconRes = R.drawable.ic_mail,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Phone field
                PetShopTextField(
                    value = phoneInput,
                    onValueChange = {
                        phoneInput = it
                        errorMessage = null
                    },
                    label = "Số điện thoại",
                    placeholder = "Nhập số điện thoại của bạn",
                    isRequired = true,
                    leadingIconRes = R.drawable.ic_phone,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
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
                    placeholder = "Tạo mật khẩu (tối thiểu 8 ký tự)",
                    isRequired = true,
                    leadingIconRes = R.drawable.ic_lock,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Confirm password field
                PetShopTextField(
                    value = confirmPasswordInput,
                    onValueChange = {
                        confirmPasswordInput = it
                        errorMessage = null
                    },
                    label = "Xác nhận mật khẩu",
                    placeholder = "Nhập lại mật khẩu",
                    isRequired = true,
                    leadingIconRes = R.drawable.ic_shield_check,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Checkbox Agreement
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.clickable { agreeTerms = !agreeTerms }
                ) {
                    Checkbox(
                        checked = agreeTerms,
                        onCheckedChange = { agreeTerms = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = PrimaryCTA,
                            uncheckedColor = BorderDivider
                        ),
                        modifier = Modifier
                            .size(24.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = SecondaryText)) {
                                append("Tôi đồng ý với ")
                            }
                            withStyle(style = SpanStyle(color = SecondarySoftText, fontWeight = FontWeight.Bold)) {
                                append("Điều khoản dịch vụ")
                            }
                            withStyle(style = SpanStyle(color = SecondaryText)) {
                                append(" và ")
                            }
                            withStyle(style = SpanStyle(color = SecondarySoftText, fontWeight = FontWeight.Bold)) {
                                append("Chính sách bảo mật")
                            }
                            withStyle(style = SpanStyle(color = SecondaryText)) {
                                append(" của Avocado PetShop")
                            }
                        },
                        fontSize = 13.sp,
                        lineHeight = 18.sp
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

                // Primary Submit Button: 🐾 Đăng ký tài khoản
                Button(
                    onClick = {
                        when {
                            fullNameInput.isBlank() || emailInput.isBlank() || phoneInput.isBlank() || passwordInput.isBlank() -> {
                                errorMessage = "Vui lòng nhập đầy đủ thông tin bắt buộc"
                            }
                            passwordInput != confirmPasswordInput -> {
                                errorMessage = "Mật khẩu xác nhận không trùng khớp"
                            }
                            !agreeTerms -> {
                                errorMessage = "Vui lòng đồng ý với Điều khoản & Chính sách bảo mật"
                            }
                            else -> {
                                isLoading = true
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailInput.trim(), passwordInput)
                                    .addOnSuccessListener { result ->
                                        val firebaseUser = result.user
                                        val uid = firebaseUser?.uid ?: ""

                                        // Lưu thông tin tài khoản vào collection "accounts" (bảng Tai_Khoan trong ERD)
                                        // ma_vai_tro mặc định là "r1" (Khách hàng)
                                        val accountMap = hashMapOf(
                                            "ma_tai_khoan" to uid,
                                            "ma_vai_tro" to "r1",
                                            "email" to emailInput.trim(),
                                            "so_dien_thoai" to phoneInput.trim(),
                                            "mat_khau" to passwordInput,
                                            "ten_tai_khoan" to fullNameInput.trim(),
                                            "xac_thuc" to true
                                        )

                                        FirebaseFirestore.getInstance().collection("accounts")
                                            .document(uid)
                                            .set(accountMap)
                                            .addOnSuccessListener {
                                                isLoading = false
                                                val profileUpdates = UserProfileChangeRequest.Builder()
                                                    .setDisplayName(fullNameInput.trim())
                                                    .build()
                                                firebaseUser?.updateProfile(profileUpdates)
                                                onNavigateToHome()
                                            }
                                            .addOnFailureListener { e ->
                                                isLoading = false
                                                errorMessage = "Lưu tài khoản thất bại: ${e.localizedMessage}"
                                            }
                                    }
                                    .addOnFailureListener { e ->
                                        isLoading = false
                                        errorMessage = e.localizedMessage ?: "Đăng ký thất bại. Vui lòng kiểm tra lại mạng."
                                    }
                            }
                        }
                    },
                    enabled = !isLoading,
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isLoading) "Đang đăng ký..." else "Đăng ký tài khoản",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Divider: —— HOẶC ĐĂNG KÝ BẰNG ——
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
                text = "HOẶC ĐĂNG KÝ BẰNG",
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
            horizontalArrangement = Arrangement.spacedBy(10.dp)
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

            SocialButton(
                iconRes = R.drawable.ic_phone,
                text = "Số ĐT",
                onClick = { onNavigateToHome() },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Footer Link: Bạn đã có tài khoản? Đăng nhập ngay >
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MutedText, fontWeight = FontWeight.Normal)) {
                    append("Bạn đã có tài khoản? ")
                }
                withStyle(style = SpanStyle(color = SecondarySoftText, fontWeight = FontWeight.Bold)) {
                    append("Đăng nhập ngay >")
                }
            },
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onNavigateToLogin() }
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PetShopTheme {
        RegisterScreen(
            onNavigateToLogin = {},
            onNavigateToHome = {},
            onBack = {}
        )
    }
}

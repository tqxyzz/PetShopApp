package com.example.petshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PlaceholderText
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText

@Composable
fun PetShopTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    isRequired: Boolean = false,
    leadingIconRes: Int? = null,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMessage: String? = null,
    trailingLabel: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        // Label Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = SecondaryText, fontWeight = FontWeight.SemiBold)) {
                        append(label)
                    }
                    if (isRequired) {
                        withStyle(style = SpanStyle(color = DangerText, fontWeight = FontWeight.Bold)) {
                            append(" *")
                        }
                    }
                },
                fontSize = 14.sp
            )

            if (trailingLabel != null) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = trailingLabel,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = DangerText
                )
            }
        }

        // Input Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SecondarySurface)
                .border(
                    width = 1.dp,
                    color = if (errorMessage != null) DangerText else BorderDivider,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (leadingIconRes != null) {
                    Icon(
                        painter = painterResource(id = leadingIconRes),
                        contentDescription = null,
                        tint = MutedText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }

                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 14.sp,
                            color = PlaceholderText,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = PrimaryText
                        ),
                        cursorBrush = SolidColor(PrimaryText),
                        keyboardOptions = keyboardOptions,
                        visualTransformation = if (isPassword && !passwordVisible) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (isPassword) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(
                            id = if (passwordVisible) R.drawable.ic_eye else R.drawable.ic_eye_off
                        ),
                        contentDescription = null,
                        tint = MutedText,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { passwordVisible = !passwordVisible }
                    )
                }
            }
        }

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                fontSize = 11.sp,
                color = DangerText,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

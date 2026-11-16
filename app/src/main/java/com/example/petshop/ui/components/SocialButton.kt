package com.example.petshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.PrimaryText

@Composable
fun SocialButton(
    iconRes: Int,
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )

            if (!text.isNullOrEmpty()) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = text,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryText
                )
            }
        }
    }
}

@Composable
fun SocialIconButton(
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.Unspecified
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(CardSurface)
            .border(1.dp, BorderDivider, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(22.dp)
        )
    }
}

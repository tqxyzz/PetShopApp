package com.example.petshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.IconPrimaryTint
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText

@Composable
fun AvocadoLogoCircle(
    modifier: Modifier = Modifier,
    circleSize: Dp = 180.dp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // Outer glowing/soft shadow circle container
        Box(
            modifier = Modifier
                .size(circleSize)
                .shadow(
                    elevation = 16.dp,
                    shape = CircleShape,
                    ambientColor = PrimaryCTA.copy(alpha = 0.2f),
                    spotColor = PrimaryCTA.copy(alpha = 0.3f)
                )
                .clip(CircleShape)
                .background(CardSurface)
                .border(3.dp, Color(0xFFFFF0EA), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                // Avocado Mascot Graphic
                Box(
                    modifier = Modifier.size(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer avocado body shape
                    Box(
                        modifier = Modifier
                            .size(56.dp, 64.dp)
                            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp, bottomStart = 24.dp, bottomEnd = 24.dp))
                            .background(Color(0xFFE58B6D))
                    )
                    // Inner lighter avocado seed area
                    Box(
                        modifier = Modifier
                            .size(46.dp, 52.dp)
                            .clip(RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                            .background(Color(0xFFFFD1BD))
                    )
                    // Center pit with Paw print
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF8C332E)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = Color(0xFFFFD1BD),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Brand text inside mascot
                Text(
                    text = "Avocado",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryText
                )
                Text(
                    text = "PetShop",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryCTA
                )
            }
        }

        // Overlapping bottom-right pink paw badge button
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-8).dp, y = (-8).dp)
                .size(44.dp)
                .shadow(6.dp, CircleShape)
                .clip(CircleShape)
                .background(IconPrimaryTint),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_paw),
                contentDescription = null,
                tint = CardSurface,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

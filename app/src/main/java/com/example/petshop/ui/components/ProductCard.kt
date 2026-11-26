package com.example.petshop.ui.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.petshop.R
import com.example.petshop.data.model.Product
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface

@Composable
fun ProductCard(
    product: Product,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
            .clickable { onProductClick(product) }
    ) {
        Column {
            // Product Image Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color(0xFFFFF7F2)),
                contentAlignment = Alignment.Center
            ) {
                if (product.imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = when (product.petType) {
                                "dog" -> "🐶"
                                else -> "🐱"
                            },
                            fontSize = 44.sp
                        )
                        Text(
                            text = product.brand,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }
                }

                if (!product.badge.isNullOrEmpty()) {
                    val (bgColor, textColor) = when (product.badgeColor) {
                        "green" -> Color(0xFFDCFCE7) to Color(0xFF15803D)
                        "dark" -> Color(0xFF4E2F1D) to Color.White
                        else -> PrimaryCTA to Color.White
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(bgColor)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = product.badge,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    }
                }

                // Top-Right Favorite Heart Button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .shadow(4.dp, CircleShape)
                        .clip(CircleShape)
                        .background(CardSurface)
                        .clickable { isFavorite = !isFavorite },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null,
                        tint = if (isFavorite) DangerText else BorderDivider,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Product Details Content
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = product.brand,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MutedText,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = product.title,
                    fontSize = 13.sp,
                    lineHeight = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${product.rating}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB45309)
                    )
                    Text(
                        text = " | Đã bán ${product.soldCount}",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = product.currentPrice,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryText
                        )

                        if (!product.oldPrice.isNullOrEmpty()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = product.oldPrice,
                                    fontSize = 11.sp,
                                    color = MutedText,
                                    textDecoration = TextDecoration.LineThrough
                                )
                                if (!product.discountPercent.isNullOrEmpty()) {
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = product.discountPercent,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DangerText
                                    )
                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .shadow(4.dp, CircleShape)
                            .clip(CircleShape)
                            .background(AdminMaroon)
                            .clickable { onAddToCart(product) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart_add),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

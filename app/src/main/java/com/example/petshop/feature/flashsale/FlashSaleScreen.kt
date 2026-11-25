package com.example.petshop.feature.flashsale

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.petshop.R
import com.example.petshop.data.model.FlashSaleItem
import com.example.petshop.data.model.TimeSlot
import com.example.petshop.data.model.sampleFlashSaleItems
import com.example.petshop.data.model.sampleTimeSlots
import com.example.petshop.feature.home.HomeViewModel
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PetShopTheme
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessText

@Composable
fun FlashSaleScreen(
    onBack: () -> Unit,
    onNavigateToCart: () -> Unit = {},
    onAddToCart: (FlashSaleItem) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var selectedTimeSlot by remember { mutableStateOf("09:00") }
    var selectedCategoryFilter by remember { mutableStateOf("Tất cả") }
    val scrollState = rememberScrollState()

    val flashSaleItems = if (viewModel.flashSaleItems.isNotEmpty()) viewModel.flashSaleItems else sampleFlashSaleItems

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(scrollState)
    ) {
        // 1. Top Header Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Back Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
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

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Flash Sale Chớp Nhoáng",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryText
                )
            }

            // Right Action Icons: Search, Cart, Profile
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(SecondarySurface)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = PrimaryText,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(SecondarySurface)
                        .clickable { onNavigateToCart() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shopping_bag),
                        contentDescription = null,
                        tint = PrimaryText,
                        modifier = Modifier.size(18.dp)
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 2.dp, y = (-2).dp)
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(PrimaryCTA),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "3",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(AdminMaroon),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        // 2. Red Coral Banner Row with Timer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryCTA)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "⚡ ĐỢT GIẢM SÂU NHOÁNG",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Kết thúc trong: ",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )

                    TimerDigitBox(text = "01")
                    Text(text = " : ", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    TimerDigitBox(text = "24")
                    Text(text = " : ", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                    TimerDigitBox(text = "43")
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 3. Time Slot Tabs
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(sampleTimeSlots) { slot ->
                TimeSlotCard(
                    timeSlot = slot,
                    isSelected = slot.time == selectedTimeSlot,
                    onClick = { selectedTimeSlot = slot.time }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 4. Category Filter Chips
        val filterCategories = listOf("Tất cả", "Thức ăn hạt", "Pate & Đồ ướt", "Bánh thưởng")
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filterCategories) { cat ->
                val isSelected = cat == selectedCategoryFilter
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) AdminMaroon else SecondarySoft)
                        .border(
                            1.dp,
                            if (isSelected) AdminMaroon else BorderDivider,
                            RoundedCornerShape(20.dp)
                        )
                        .clickable { selectedCategoryFilter = cat }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = if (cat == "Tất cả") "🐾 Tất cả" else cat,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else SecondarySoftText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 5. Flash Sale Items List
        val filteredItems = flashSaleItems.filter { item ->
            selectedCategoryFilter == "Tất cả" || item.category.contains(selectedCategoryFilter, ignoreCase = true)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            filteredItems.forEach { item ->
                FlashSaleDetailCard(
                    item = item,
                    onBuyNow = { onAddToCart(item) }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 6. Bottom Policy Banner (Cam kết từ Avocado PetShop)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(SecondarySoft),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = null,
                        tint = SuccessText,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Cam kết từ Avocado PetShop",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shield_check),
                            contentDescription = null,
                            tint = SuccessText,
                            modifier = Modifier.size(14.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "⚡ Cam kết hàng chính hãng 100%. Số lượng flash sale có hạn, mỗi khách hàng tối đa 2 sản phẩm/mã ưu đãi.",
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        color = SecondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun TimerDigitBox(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = 5.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
            color = PrimaryCTA
        )
    }
}

@Composable
fun TimeSlotCard(
    timeSlot: TimeSlot,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) PrimaryCTA else SecondarySurface)
            .border(
                1.dp,
                if (isSelected) PrimaryCTA else BorderDivider,
                RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = timeSlot.time,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (isSelected) Color.White else PrimaryText
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = timeSlot.status,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) Color.White.copy(alpha = 0.9f) else MutedText
            )
        }
    }
}

@Composable
fun FlashSaleDetailCard(
    item: FlashSaleItem,
    onBuyNow: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Product Image Container
            Box(
                modifier = Modifier
                    .size(105.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFFF7F2)),
                contentAlignment = Alignment.Center
            ) {
                if (item.imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text(text = item.petEmoji, fontSize = 44.sp)
                }

                // Discount Tag attached top-left
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(bottomEnd = 12.dp, topStart = 16.dp))
                        .background(PrimaryCTA)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = item.discountTag,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Right Product Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.brand,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MutedText,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = item.title,
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
                    Text(
                        text = item.currentPrice,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryCTA
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = item.oldPrice,
                        fontSize = 11.sp,
                        color = MutedText,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Bottom Row: Sales Status & Buy Now Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Sold status pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondarySoft)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = item.soldStatusText,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }

                    // Buy Now Button
                    Button(
                        onClick = onBuyNow,
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryCTA,
                            contentColor = Color.White
                        ),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Text(
                            text = "Mua ngay",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlashSaleScreenPreview() {
    PetShopTheme {
        FlashSaleScreen(onBack = {})
    }
}

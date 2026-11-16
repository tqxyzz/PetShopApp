package com.example.petshop.feature.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.data.model.CategoryGroup
import com.example.petshop.data.model.SubCategoryItem
import com.example.petshop.data.model.sampleDogCategoryGroups
import com.example.petshop.data.model.sampleSidebarCategories
import com.example.petshop.ui.components.HomeTab
import com.example.petshop.ui.components.PetShopBottomNavBar
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.IconPrimaryBg
import com.example.petshop.ui.theme.IconPrimaryTint
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
fun CategoryScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToCategoryProductList: (String) -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToAssistant: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedSidebarId by remember { mutableStateOf("cat_1") }
    val sidebarCategories = sampleSidebarCategories

    Scaffold(
        bottomBar = {
            PetShopBottomNavBar(
                selectedTab = HomeTab.Category,
                onTabSelected = { tab ->
                    when (tab) {
                        is HomeTab.Home -> onNavigateToHome()
                        is HomeTab.Assistant -> onNavigateToAssistant()
                        is HomeTab.Profile -> onNavigateToProfile()
                        else -> {}
                    }
                }
            )
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 1. Top Bar Header & Double Search Input Bars
            CategoryHeaderBar(onNavigateToCart = onNavigateToCart)

            Spacer(modifier = Modifier.height(10.dp))

            // 2. Promo Banner Card
            CategoryPromoBanner()

            Spacer(modifier = Modifier.height(12.dp))

            // 3. Split-Pane Layout: Left Sidebar + Right Content Area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // Left Sidebar (Navigation Rail)
                Column(
                    modifier = Modifier
                        .width(115.dp)
                        .fillMaxHeight()
                        .background(GlobalBackground)
                        .verticalScroll(rememberScrollState())
                        .padding(start = 12.dp, end = 8.dp)
                ) {
                    sidebarCategories.forEach { cat ->
                        val isSelected = cat.id == selectedSidebarId
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) PrimaryCTA else SecondarySurface)
                                .clickable { selectedSidebarId = cat.id }
                                .padding(vertical = 10.dp, horizontal = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = cat.emoji, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                     text = cat.title,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else SecondaryText,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                // Right Content Area
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(GlobalBackground)
                        .verticalScroll(rememberScrollState())
                        .padding(start = 4.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    // Right Content Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Danh mục dành cho Cún",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = PrimaryText
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Cập nhật xu hướng chăm cưng mỗi ngày",
                                fontSize = 11.sp,
                                color = MutedText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondarySoft)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "1.200+ món",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Render Subcategory Groups
                    sampleDogCategoryGroups.forEach { group ->
                        CategoryGroupSection(
                            group = group,
                            onSeeAll = { onNavigateToCategoryProductList(group.title) }
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                    }

                    // Partner Brands Group
                    PartnerBrandsGroup()

                    Spacer(modifier = Modifier.height(14.dp))

                    // Best Seller Product Cards Group
                    BestSellerDogsGroup(onProductClick = { onNavigateToCategoryProductList("Bán chạy") })
                }
            }
        }
    }
}

@Composable
fun CategoryHeaderBar(
    onNavigateToCart: () -> Unit
) {
    Column(modifier = Modifier.padding(top = 12.dp).padding(horizontal = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(IconPrimaryBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = IconPrimaryTint,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Avocado",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryText
                    )
                    Text(
                        text = "PETSHOP",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryCTA,
                        letterSpacing = 1.sp
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(SecondarySurface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bell),
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

        Spacer(modifier = Modifier.height(10.dp))

        // Search Bar (Tìm theo tên sản phẩm, danh mục...)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .clip(RoundedCornerShape(21.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(21.dp))
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = MutedText,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Tìm theo tên sản phẩm, danh mục...",
                    fontSize = 12.sp,
                    color = PlaceholderText,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    tint = MutedText,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun CategoryPromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(SecondarySoft)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(PrimaryCTA)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "🔥 ƯU ĐÃI HOT",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Tuần Lễ Dinh Dưỡng Boss Cưng",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryText
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Giảm tới 40% toàn ngành thức ăn &...",
                    fontSize = 11.sp,
                    color = SecondaryText
                )
            }

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(IconPrimaryBg),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🐾", fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun CategoryGroupSection(
    group: CategoryGroup,
    onSeeAll: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
            .padding(12.dp)
    ) {
        Column {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🔴", fontSize = 10.sp, modifier = Modifier.padding(end = 4.dp))
                    Text(
                        text = group.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryText
                    )
                }

                Text(
                    text = "Xem hết >",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondarySoftText,
                    modifier = Modifier.clickable { onSeeAll() }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Subcategory Items Grid (3 Columns)
            val chunkedItems = group.items.chunked(3)
            chunkedItems.forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach { subItem ->
                        SubCategoryCard(
                            item = subItem,
                            onClick = onSeeAll,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowItems.size < 3) {
                        repeat(3 - rowItems.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubCategoryCard(
    item: SubCategoryItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = item.emoji, fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.name,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            textAlign = TextAlign.Center,
            maxLines = 2,
            lineHeight = 13.sp
        )
    }
}

@Composable
fun PartnerBrandsGroup() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(SecondarySurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
            .padding(12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Thương hiệu đồng hành",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
                Text(
                    text = "Chính hãng 100%",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondarySoftText
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val brands = listOf("RC" to "Royal Canin", "SH" to "SmartHeart", "PD" to "Pedigree")
                brands.forEach { (logo, name) ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(CardSurface)
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = logo, fontSize = 13.sp, fontWeight = FontWeight.ExtraBold, color = SecondarySoftText)
                            Text(text = name, fontSize = 9.sp, color = MutedText)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BestSellerDogsGroup(
    onProductClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "✪", fontSize = 12.sp, color = PrimaryCTA, modifier = Modifier.padding(end = 4.dp))
                Text(
                    text = "Bán chạy nhất cho Cún tuần này",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryText
                )
            }

            Text(
                text = "Xem Top 20>",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = SecondarySoftText,
                modifier = Modifier.clickable { onProductClick() }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Product 1
            MiniProductCard(
                title = "Hạt Royal Canin Puppy 800g cho cún con",
                brand = "Royal Canin",
                price = "165.000đ",
                oldPrice = "195.000đ",
                discount = "-15%",
                badge = null,
                rating = "4.9 (428)",
                emoji = "🐶",
                onClick = onProductClick,
                modifier = Modifier.weight(1f)
            )

            // Product 2
            MiniProductCard(
                title = "Xương gặm Orgo sạch cao răng & thơm miệng",
                brand = "Orgo Dental",
                price = "35.000đ",
                oldPrice = "45.000đ",
                discount = null,
                badge = "Bán chạy",
                rating = "5.0 (1.2k)",
                emoji = "🦴",
                onClick = onProductClick,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun MiniProductCard(
    title: String,
    brand: String,
    price: String,
    oldPrice: String,
    discount: String?,
    badge: String?,
    rating: String,
    emoji: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFFFF7F2)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 36.sp)

                if (!discount.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(bottomEnd = 8.dp, topStart = 12.dp))
                            .background(DangerText)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = discount, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }

                if (!badge.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(bottomEnd = 8.dp, topStart = 12.dp))
                            .background(PrimaryCTA)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = badge, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = brand, fontSize = 9.sp, color = MutedText, fontWeight = FontWeight.Bold)
            Text(text = title, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryText, maxLines = 2, lineHeight = 14.sp)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "⭐ $rating", fontSize = 9.sp, color = MutedText)

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = price, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, color = PrimaryCTA)
                    Text(text = oldPrice, fontSize = 9.sp, color = MutedText, textDecoration = TextDecoration.LineThrough)
                }

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(PrimaryCTA),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_cart_add), contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    PetShopTheme {
        CategoryScreen()
    }
}

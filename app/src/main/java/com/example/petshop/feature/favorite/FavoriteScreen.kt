package com.example.petshop.feature.favorite

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.petshop.R
import com.example.petshop.data.model.Product
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText

val sampleFavoriteProducts = listOf(
    Product(
        id = "fav_1",
        brand = "ROYAL CANIN",
        title = "Hạt Royal Canin Mother & Babycat 2kg",
        badge = "-15%",
        badgeColor = "red",
        rating = 5.0,
        soldCount = "1.2k",
        currentPrice = "385.000đ",
        oldPrice = "450.000đ",
        discountPercent = "-15%",
        petType = "cat",
        imageUrl = "https://images.unsplash.com/photo-1548767797-d8c844163c4c?w=500",
        isFlashSale = true
    ),
    Product(
        id = "fav_2",
        brand = "CATURE",
        title = "Cát vệ sinh đậu nành Cature Tofu 6L trà...",
        badge = "Bán chạy",
        badgeColor = "green",
        rating = 4.9,
        soldCount = "1.2k",
        currentPrice = "145.000đ",
        oldPrice = null,
        discountPercent = null,
        petType = "cat",
        imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=500"
    ),
    Product(
        id = "fav_3",
        brand = "NEKKO",
        title = "Pate Nekko Cho Mèo Đủ Vị (Combo 12 gói)",
        badge = "⚡-20%",
        badgeColor = "coral",
        rating = 4.8,
        soldCount = "3.4k",
        currentPrice = "180.000đ",
        oldPrice = "225.000đ",
        discountPercent = "-20%",
        petType = "cat",
        imageUrl = "https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?w=500",
        isFlashSale = true
    ),
    Product(
        id = "fav_4",
        brand = "VIRBAC",
        title = "Gel dinh dưỡng NUTRIPLUS Virbac...",
        badge = "Dược thú y",
        badgeColor = "blue",
        rating = 4.9,
        soldCount = "890",
        currentPrice = "165.000đ",
        oldPrice = null,
        discountPercent = null,
        petType = "cat",
        imageUrl = "https://images.unsplash.com/photo-1561948955-570b270e7c36?w=500"
    ),
    Product(
        id = "fav_5",
        brand = "ORGO",
        title = "Xương gặm sạch răng Orgo vị bò 90g",
        badge = "-25%",
        badgeColor = "coral",
        rating = 4.7,
        soldCount = "2.1k",
        currentPrice = "45.000đ",
        oldPrice = "60.000đ",
        discountPercent = "-25%",
        petType = "dog",
        imageUrl = "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=500",
        isFlashSale = true
    ),
    Product(
        id = "fav_6",
        brand = "AVOCADO HOME",
        title = "Trụ cào móng cây nấm nhung mèo con",
        badge = "Còn 3 chiếc",
        badgeColor = "red",
        rating = 4.9,
        soldCount = "520",
        currentPrice = "220.000đ",
        oldPrice = null,
        discountPercent = null,
        petType = "cat",
        imageUrl = "https://images.unsplash.com/photo-1545249390-6bdfa286032f?w=500"
    )
)

@Composable
fun FavoriteScreen(
    onBack: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToFlashSale: () -> Unit = {},
    onProductClick: (Product) -> Unit = {},
    onAddToCart: (Product) -> Unit = {},
    onAddAllToCart: (List<Product>) -> Unit = {},
    viewModel: FavoriteViewModel = viewModel()
) {
    var isSelectAllActive by remember { mutableStateOf(false) }

    val rawFavorites = viewModel.favoriteProducts
    val favoriteList = if (rawFavorites.isNotEmpty()) viewModel.getFilteredProducts() else sampleFavoriteProducts
    val totalCount = if (rawFavorites.isNotEmpty()) rawFavorites.size else 12
    val flashSaleCount = if (rawFavorites.isNotEmpty()) viewModel.getFlashSaleCount() else 3

    val catCount = if (rawFavorites.isNotEmpty()) rawFavorites.count { it.petType == "cat" } else 6
    val dogCount = if (rawFavorites.isNotEmpty()) rawFavorites.count { it.petType == "dog" } else 5

    Scaffold(
        bottomBar = {
            // Fixed Bottom Action Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(12.dp)
                    .background(CardSurface)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Share Button
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .border(1.dp, BorderDivider, CircleShape)
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Chia sẻ",
                            tint = PrimaryText,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Primary Add All To Cart Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(PrimaryCTA)
                            .clickable { onAddAllToCart(favoriteList) },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shopping_bag),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Thêm tất cả vào giỏ (${favoriteList.size} món)",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back Button
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(SecondarySurface)
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Quay lại",
                        tint = PrimaryText,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Title
                Text(
                    text = "Danh Sách Yêu Thích",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                // Actions: Search, Heart Badge, Profile
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .clickable { onNavigateToSearch() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Tìm kiếm",
                            tint = SecondaryText,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Heart Icon with Badge Count
                    Box(contentAlignment = Alignment.TopEnd) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SecondarySoft),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_heart),
                                contentDescription = "Yêu thích",
                                tint = DangerText,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(PrimaryCTA),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$totalCount",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(AdminMaroon)
                            .clickable { onNavigateToProfile() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = "Tài khoản",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Subhead Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = AdminMaroon,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "$totalCount sản phẩm đã lưu",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Select All Pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondarySurface)
                            .clickable { isSelectAllActive = !isSelectAllActive }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🎼 ", fontSize = 11.sp)
                            Text(
                                text = if (isSelectAllActive) "Bỏ chọn" else "Chọn tất cả",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    // Trash Clear All
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .clickable { viewModel.clearAllFavorites() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_trash),
                            contentDescription = "Xóa tất cả",
                            tint = DangerText,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Flash Sale Promo Alert Banner Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFFE8E2))
                    .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_flame),
                                contentDescription = null,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "$flashSaleCount món ",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = DangerText
                                )
                                Text(
                                    text = "trong danh sách đang Flash",
                                    fontSize = 12.sp,
                                    color = PrimaryText
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(PrimaryCTA)
                            .clickable { onNavigateToFlashSale() }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Xem ngay",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(text = "➔", fontSize = 10.sp, color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Pills Row
            val filterTabs = listOf("Tất cả ($totalCount)", "Dành cho Mèo ($catCount)", "Dành cho Chó ($dogCount)")
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filterTabs) { tabText ->
                    val isSelected = (tabText.contains("Tất cả") && viewModel.selectedFilterTab == "Tất cả") ||
                            (tabText.contains("Mèo") && viewModel.selectedFilterTab == "Dành cho Mèo") ||
                            (tabText.contains("Chó") && viewModel.selectedFilterTab == "Dành cho Chó")

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) PrimaryCTA else CardSurface)
                            .border(
                                1.dp,
                                if (isSelected) PrimaryCTA else BorderDivider,
                                RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                viewModel.selectedFilterTab = when {
                                    tabText.contains("Mèo") -> "Dành cho Mèo"
                                    tabText.contains("Chó") -> "Dành cho Chó"
                                    else -> "Tất cả"
                                }
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (isSelected) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_paw),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                            Text(
                                text = tabText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else SecondaryText
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 2-Column Favorite Product Grid
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                favoriteList.chunked(2).forEach { pair ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        pair.forEach { prod ->
                            FavoriteProductCard(
                                product = prod,
                                onProductClick = { onProductClick(prod) },
                                onRemoveFavorite = { viewModel.removeFavorite(prod) },
                                onAddToCart = { onAddToCart(prod) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (pair.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // End of List Footer Banner Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFFFF7F2))
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFE8E2)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = null,
                            tint = DangerText,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Bạn đã khám phá hết danh sách yêu thích",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Mẹo nhỏ: Bấm vào hình trái tim bất kỳ để xóa món đồ khi cưng không còn cần nữa nhé!",
                        fontSize = 11.sp,
                        color = MutedText,
                        textAlign = TextAlign.Center,
                        lineHeight = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun FavoriteProductCard(
    product: Product,
    onProductClick: () -> Unit,
    onRemoveFavorite: () -> Unit,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider)
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
            .clickable { onProductClick() }
            .padding(10.dp)
    ) {
        Column {
            // Product Image Container with Badges
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(14.dp))
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
                    Text(
                        text = if (product.petType == "dog") "🐶" else "🐱",
                        fontSize = 48.sp
                    )
                }

                // Top-Left Discount/Tag Badge
                if (!product.badge.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(6.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                when (product.badgeColor) {
                                    "green" -> SuccessBg
                                    "red" -> Color(0xFFFEE2E2)
                                    else -> Color(0xFFFFE8E2)
                                }
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = product.badge,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = when (product.badgeColor) {
                                "green" -> SuccessText
                                "red" -> DangerText
                                else -> AdminMaroon
                            }
                        )
                    }
                }

                // Top-Right Red Heart Button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .shadow(2.dp, CircleShape)
                        .clickable { onRemoveFavorite() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = "Xóa khỏi yêu thích",
                        tint = DangerText,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tag & Rating Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(SecondarySurface)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (product.petType == "dog") "Dành cho Chó" else "Còn hàng",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryText
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(11.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "${product.rating}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB45309)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Title
            Text(
                text = product.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Price & Add to Cart Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    if (!product.oldPrice.isNullOrEmpty()) {
                        Text(
                            text = product.oldPrice,
                            fontSize = 10.sp,
                            color = MutedText,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                    Text(
                        text = product.currentPrice,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = AdminMaroon
                    )
                }

                // Add to Cart Red Circle Button
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(PrimaryCTA)
                        .clickable { onAddToCart() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cart_add),
                        contentDescription = "Thêm vào giỏ",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

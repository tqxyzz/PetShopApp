package com.example.petshop.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.petshop.utils.importProductsFromAssets
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.data.model.BrandItem
import com.example.petshop.data.model.CategoryItem
import com.example.petshop.data.model.Product
import com.example.petshop.data.model.VoucherItem
import com.example.petshop.data.model.sampleBrands
import com.example.petshop.data.model.sampleCategories
import com.example.petshop.data.model.sampleFlashSaleProducts
import com.example.petshop.data.model.sampleProducts
import com.example.petshop.data.model.sampleVouchers
import com.example.petshop.ui.components.HomeTab
import com.example.petshop.ui.components.PetShopBottomNavBar
import com.example.petshop.ui.components.ProductCard
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
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText

@Composable
fun HomeScreen(
    onNavigateToCategory: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToProductDetail: (Product) -> Unit = {},
    onNavigateToFlashSale: () -> Unit = {},
    onNavigateToVoucher: () -> Unit = {},
    onNavigateToBrand: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToAssistant: () -> Unit = {},
    onNavigateToOrder: () -> Unit = {},
    onNavigateToWishlist: () -> Unit = {},
    onNavigateToSearchHistory: () -> Unit = {},
    onNavigateToCameraScan: () -> Unit = {},
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var selectedTab by remember { mutableStateOf<HomeTab>(HomeTab.Home) }
    var selectedPetTab by remember { mutableStateOf("cat") } // "cat" or "dog"

    val allProducts = if (viewModel.productList.isNotEmpty()) viewModel.productList else sampleProducts
    val flashSaleProducts = if (viewModel.flashSaleList.isNotEmpty()) viewModel.flashSaleList else sampleFlashSaleProducts

    Scaffold(
        bottomBar = {
            PetShopBottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { tab ->
                    selectedTab = tab
                    when (tab) {
                        is HomeTab.Category -> onNavigateToCategory()
                        is HomeTab.Assistant -> onNavigateToAssistant()
                        is HomeTab.Profile -> onNavigateToProfile()
                        else -> {}
                    }
                }
            )
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            // 1. Top Header Bar
            HomeTopHeaderBar(
                onNavigateToCart = onNavigateToCart,
                onNavigateToWishlist = onNavigateToWishlist,
                onNavigateToOrder = onNavigateToOrder
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 2. Search Bar & Hot Tags
            HomeSearchBarSection(
                onSearchClick = onNavigateToSearchHistory,
                onCameraClick = onNavigateToCameraScan
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Admin Import Button Banner
//            val context = LocalContext.current
//            var importStatus by remember { mutableStateOf<String?>(null) }
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(SecondarySurface)
//                    .border(1.dp, PrimaryCTA, RoundedCornerShape(16.dp))
//                    .clickable {
//                        importProductsFromAssets(context)
//                        importStatus = "Đã import dữ liệu thành công! Hãy reload lại trang."
//                    }
//                    .padding(12.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text(
//                        text = "🚀 Bấm vào đây để Import sản phẩm vào Firestore",
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = PrimaryCTA
//                    )
//                    if (importStatus != null) {
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(
//                            text = importStatus!!,
//                            fontSize = 12.sp,
//                            color = SuccessText
//                        )
//                    }
//                }
//            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Hero Banner Carousel
            HomeHeroBanner()

            Spacer(modifier = Modifier.height(20.dp))

            // 4. Categories Section
            HomeCategoriesSection()

            Spacer(modifier = Modifier.height(20.dp))

            // 5. Flash Sale Section
            HomeFlashSaleSection(
                products = flashSaleProducts,
                onProductClick = onNavigateToProductDetail,
                onSeeAllClick = onNavigateToFlashSale
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 6. Exclusive Vouchers Section
            HomeVouchersSection(onSeeAllClick = onNavigateToVoucher)

            Spacer(modifier = Modifier.height(20.dp))

            // 7. Official Brands Section
            HomeBrandsSection(onBrandClick = onNavigateToBrand)

            Spacer(modifier = Modifier.height(20.dp))

            // 8. Pet Type Filter Tabs (Hoàng Thượng / Boss Cún)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Tab 1: Hoàng Thượng 🐱
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (selectedPetTab == "cat") CardSurface else SecondarySurface)
                        .border(
                            width = 1.5.dp,
                            color = if (selectedPetTab == "cat") BorderDivider else Color.Transparent,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .clickable { selectedPetTab = "cat" },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Hoàng Thượng 🐱",
                        fontSize = 14.sp,
                        fontWeight = if (selectedPetTab == "cat") FontWeight.ExtraBold else FontWeight.SemiBold,
                        color = if (selectedPetTab == "cat") SecondarySoftText else MutedText
                    )
                }

                // Tab 2: Boss Cún 🐶
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(if (selectedPetTab == "dog") CardSurface else SecondarySurface)
                        .border(
                            width = 1.5.dp,
                            color = if (selectedPetTab == "dog") BorderDivider else Color.Transparent,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .clickable { selectedPetTab = "dog" },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Boss Cún 🐶",
                        fontSize = 14.sp,
                        fontWeight = if (selectedPetTab == "dog") FontWeight.ExtraBold else FontWeight.SemiBold,
                        color = if (selectedPetTab == "dog") SecondarySoftText else MutedText
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 9. 2-Column Product Grid
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                val filteredProducts = allProducts.filter { it.petType == selectedPetTab || selectedPetTab == "cat" }
                val chunkedProducts = filteredProducts.chunked(2)

                chunkedProducts.forEach { rowProducts ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowProducts.forEach { product ->
                            ProductCard(
                                product = product,
                                onProductClick = onNavigateToProductDetail,
                                onAddToCart = {},
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (rowProducts.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 10. End of List Paw Indicator
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(IconPrimaryBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = IconPrimaryTint,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Bạn đã xem hết danh sách gợi ý hôm nay!",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Hãy vuốt lên hoặc tìm kiếm để khám phá thêm 3,000+ món đồ cho bé.",
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = MutedText,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun HomeTopHeaderBar(
    onNavigateToCart: () -> Unit = {},
    onNavigateToWishlist: () -> Unit = {},
    onNavigateToOrder: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Brand Logo Top-Left
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

        // Action Buttons Top-Right: Heart (Wishlist), Ticket (Orders), Cart, Profile
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Wishlist Heart Icon
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(SecondarySurface)
                    .clickable { onNavigateToWishlist() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "Wishlist",
                    tint = AdminMaroon,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Order List Icon
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(SecondarySurface)
                    .clickable { onNavigateToOrder() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ticket),
                    contentDescription = "Orders",
                    tint = PrimaryText,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Cart with Badge
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
                    contentDescription = "Cart",
                    tint = PrimaryText,
                    modifier = Modifier.size(18.dp)
                )

                // Red Badge Count
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

            // User Profile
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
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun HomeSearchBarSection(
    onSearchClick: () -> Unit = {},
    onCameraClick: () -> Unit = {}
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        // Search Input Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .clickable { onSearchClick() }
                .padding(horizontal = 16.dp),
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
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Tìm thức ăn hạt, pate, phụ kiện...",
                    fontSize = 13.sp,
                    color = PlaceholderText,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = null,
                    tint = MutedText,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onCameraClick() }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Hot Search Suggestions Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "↗ Gợi ý hot:",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = SecondarySoftText,
                modifier = Modifier.padding(end = 6.dp)
            )

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listOf("Pate Ciao", "Hạt Royal Canin", "Cát đậu nành")) { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(CardSurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = tag,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeHeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(170.dp)
            .shadow(10.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFF7B75),
                        Color(0xFFFF9E98),
                        Color(0xFFFFD4C8)
                    )
                )
            )
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .align(Alignment.CenterStart)
        ) {
            // Freeship Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.9f))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "🚚 FREESHIP TOÀN QUỐC",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryCTA
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Main Banner Title
            Text(
                text = "ĐẠI TIỆC BOSS CƯNG",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "GIẢM ĐẾN 50%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.White)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "DEAL SỐC",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DangerText
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // CTA Button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .clickable { }
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Khám phá ngay →",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }
        }

        // Mascot Graphic Right
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(110.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "🐱🐶", fontSize = 38.sp)
                Text(
                    text = "Avocado Deals",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        // Indicator Dots
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-4).dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color.White)
            )
            repeat(3) {
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.5f))
                )
            }
        }
    }
}

@Composable
fun HomeCategoriesSection() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "🔺",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Danh mục cho bé",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            Text(
                text = "Tất cả (8)",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MutedText
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 2 Rows x 4 Items Grid
        val firstRow = sampleCategories.take(4)
        val secondRow = sampleCategories.drop(4)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            firstRow.forEach { cat ->
                CategoryItemView(category = cat)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            secondRow.forEach { cat ->
                CategoryItemView(category = cat)
            }
        }
    }
}

@Composable
fun CategoryItemView(category: CategoryItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { }
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = category.titleEmojiOrIcon(), fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = category.title,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText,
            textAlign = TextAlign.Center
        )
    }
}

private fun CategoryItem.titleEmojiOrIcon(): String = this.iconEmoji

@Composable
fun HomeFlashSaleSection(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    onSeeAllClick: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Header Row: ⚡ FLASH SALE + Timer + Xem tất cả >
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onSeeAllClick() }
            ) {
                Text(
                    text = "⚡FLASH SALE",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryCTA
                )
                Spacer(modifier = Modifier.width(8.dp))

                // Timer boxes
                TimerBox(text = "02")
                Text(text = ":", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 2.dp))
                TimerBox(text = "45")
                Text(text = ":", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 2.dp))
                TimerBox(text = "16")
            }

            Text(
                text = "Xem tất cả >",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = SecondarySoftText,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Horizontal Flash Sale List
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                FlashSaleCard(product = product, onClick = { onProductClick(product) })
            }
        }
    }
}

@Composable
fun TimerBox(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(PrimaryText)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun FlashSaleCard(
    product: Product,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .shadow(6.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        Column {
            // Image area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
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
                    Text(text = "🥫", fontSize = 42.sp)
                }

                // Discount Badge top-left
                if (!product.discountPercent.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(bottomEnd = 12.dp, topStart = 16.dp))
                            .background(DangerText)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = product.discountPercent,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = product.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = product.currentPrice,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryCTA
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    if (!product.oldPrice.isNullOrEmpty()) {
                        Text(
                            text = product.oldPrice,
                            fontSize = 10.sp,
                            color = MutedText,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Progress Bar: ĐÃ BÁN xx%
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFFE8E2)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(PrimaryCTA)
                    )
                    Text(
                        text = "ĐÃ BÁN ${product.soldCount}",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeVouchersSection(
    onSeeAllClick: () -> Unit = {}
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🎁", fontSize = 16.sp, modifier = Modifier.padding(end = 4.dp))
                Text(
                    text = "Voucher độc quyền",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            Text(
                text = "Kho Voucher",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = SecondarySoftText,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Voucher List Row
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(sampleVouchers) { voucher ->
                VoucherCard(voucher = voucher, onClick = onSeeAllClick)
            }
        }
    }
}

@Composable
fun VoucherCard(
    voucher: VoucherItem,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = voucher.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (voucher.isSaved) SuccessText else SecondarySoftText
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = voucher.subtitle,
                    fontSize = 11.sp,
                    color = MutedText,
                    maxLines = 1
                )
                Text(
                    text = voucher.hsd,
                    fontSize = 10.sp,
                    color = PlaceholderText
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Save Action Button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (voucher.isSaved) SuccessBg else PrimaryCTA)
                    .clickable { }
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = if (voucher.isSaved) "✓ Đã lưu" else "Lưu",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (voucher.isSaved) SuccessText else Color.White
                )
            }
        }
    }
}

@Composable
fun HomeBrandsSection(
    onBrandClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onBrandClick() }
            .padding(horizontal = 20.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "🛡", fontSize = 16.sp, modifier = Modifier.padding(end = 4.dp))
                Text(
                    text = "Thương hiệu chính hãng",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(SecondarySurface)
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "100% THẬT",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MutedText
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Brand Circle Cards Row
        LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            items(sampleBrands) { brand ->
                BrandCard(brand = brand, onClick = onBrandClick)
            }
        }
    }
}

@Composable
fun BrandCard(
    brand: BrandItem,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(96.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(SecondarySurface),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = brand.logoText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = SecondarySoftText
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = brand.name,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            Text(
                text = brand.country,
                fontSize = 10.sp,
                color = MutedText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PetShopTheme {
        HomeScreen()
    }
}

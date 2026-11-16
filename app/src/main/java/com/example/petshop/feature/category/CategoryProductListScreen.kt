package com.example.petshop.feature.category

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.data.model.Product
import com.example.petshop.ui.components.ProductCard
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.IconPrimaryBg
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PetShopTheme
import com.example.petshop.ui.theme.PlaceholderText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText

val sampleCategoryProducts = listOf(
    Product(
        id = "cp_1",
        brand = "SMARTHEART",
        title = "Hạt SmartHeart Gold Puppy 1kg vị thịt bò &...",
        badge = "Chính hãng",
        badgeColor = "green",
        rating = 4.9,
        soldCount = "1.4k",
        currentPrice = "115.000đ",
        oldPrice = "145.000đ",
        discountPercent = "-20%",
        petType = "dog"
    ),
    Product(
        id = "cp_2",
        brand = "ROYAL CANIN",
        title = "Hạt Royal Canin Mini Adult 2kg cho chó nhỏ",
        badge = "Top bán chạy",
        badgeColor = "coral",
        rating = 5.0,
        soldCount = "2.8k",
        currentPrice = "385.000đ",
        oldPrice = "450.000đ",
        discountPercent = "-15%",
        petType = "dog"
    ),
    Product(
        id = "cp_3",
        brand = "ORGO",
        title = "Xương gặm canxi Orgo vị phô mai gói...",
        badge = "Khuyên dùng",
        badgeColor = "coral",
        rating = 4.8,
        soldCount = "3.1k",
        currentPrice = "28.000đ",
        oldPrice = "40.000đ",
        discountPercent = "-30%",
        petType = "dog"
    ),
    Product(
        id = "cp_4",
        brand = "AVOCADO",
        title = "Vòng cổ đệm êm kèm chuông Avocado...",
        badge = "Phụ kiện",
        badgeColor = "dark",
        rating = 4.9,
        soldCount = "820",
        currentPrice = "45.000đ",
        oldPrice = "60.000đ",
        discountPercent = null,
        petType = "dog"
    ),
    Product(
        id = "cp_5",
        brand = "SOS",
        title = "Sữa tắm SOS dịu nhẹ mềm lông cho chó...",
        badge = "Thảo mộc",
        badgeColor = "green",
        rating = 4.9,
        soldCount = "950",
        currentPrice = "89.000đ",
        oldPrice = "120.000đ",
        discountPercent = "-25%",
        petType = "dog"
    ),
    Product(
        id = "cp_6",
        brand = "AVOCADO HOME",
        title = "Đệm ngủ oval hình bơ nhung êm cho cún...",
        badge = "Êm ái",
        badgeColor = "coral",
        rating = 5.0,
        soldCount = "640",
        currentPrice = "195.000đ",
        oldPrice = "250.000đ",
        discountPercent = "-22%",
        petType = "dog"
    )
)

@Composable
fun CategoryProductListScreen(
    categoryTitle: String = "Đồ Dùng & Thức Ăn Cho Chó",
    onBack: () -> Unit,
    onNavigateToCart: () -> Unit = {},
    onProductClick: (Product) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedSort by remember { mutableStateOf("Phổ biến") }
    var selectedSubFilter by remember { mutableStateOf("Tất cả") }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(scrollState)
            .padding(bottom = 24.dp)
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

                Column {
                    Text(
                        text = "Danh Mục Sản Phẩm",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryText
                    )
                    Text(
                        text = "🐶 $categoryTitle",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
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
            }
        }

        // Subhead Count Badge
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🐶 $categoryTitle",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryText
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(SecondarySoft)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "850+ món",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondarySoftText
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Search Input Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(44.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(22.dp))
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
                    text = "Tìm đồ cho cún cưng, hạt, pate, đồ chơi...",
                    fontSize = 12.sp,
                    color = PlaceholderText,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Promo Banner Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySurface)
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
                            text = "🔥 Ưu đãi tuần cưng",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Chăm sóc Cún yêu toàn diện",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryText
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Dinh dưỡng chuẩn Pháp & Thái Lan giảm tới 35%",
                        fontSize = 11.sp,
                        color = SecondaryText
                    )
                }

                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(IconPrimaryBg),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🐶", fontSize = 28.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subcategory Filter Pills Row 1
        val subFilters = listOf("Tất cả (850)", "Thức ăn hạt", "Pate & Đồ hộp", "Xương...")
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(subFilters) { filter ->
                val isSelected = filter.contains(selectedSubFilter, ignoreCase = true) || (filter.contains("Tất cả") && selectedSubFilter == "Tất cả")
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) PrimaryCTA else CardSurface)
                        .border(
                            1.dp,
                            if (isSelected) PrimaryCTA else BorderDivider,
                            RoundedCornerShape(20.dp)
                        )
                        .clickable { selectedSubFilter = if (filter.contains("Tất cả")) "Tất cả" else filter }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = if (filter.contains("Tất cả")) "🐾 $filter" else filter,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else SecondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sort Options & Active Filters Row 2
        val sortOptions = listOf("Phổ biến", "Bán chạy", "Mới nhất", "Giá ⇅", "≡ Lọc")
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sortOptions) { sort ->
                val isSelected = sort == selectedSort
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) AdminMaroon else SecondarySurface)
                        .clickable { selectedSort = sort }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = sort,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else SecondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Active Filter Badges Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Đang lọc: ",
                fontSize = 11.sp,
                color = MutedText
            )
            Spacer(modifier = Modifier.width(4.dp))
            ActiveFilterBadge(text = "Mọi độ tuổi x")
            Spacer(modifier = Modifier.width(6.dp))
            ActiveFilterBadge(text = "Đang có sẵn x")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2-Column Product Grid
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            val chunkedProducts = sampleCategoryProducts.chunked(2)
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
                            onProductClick = onProductClick,
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

        Spacer(modifier = Modifier.height(10.dp))

        // Load More Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                .clickable { }
                .padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Xem thêm 800+ sản phẩm cho cún",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "˅",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Hiển thị 6 trên 850 sản phẩm",
                    fontSize = 10.sp,
                    color = MutedText
                )
            }
        }
    }
}

@Composable
fun ActiveFilterBadge(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SecondarySoft)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = SecondarySoftText
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryProductListScreenPreview() {
    PetShopTheme {
        CategoryProductListScreen(onBack = {})
    }
}

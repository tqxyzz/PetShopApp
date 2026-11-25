package com.example.petshop.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petshop.R
import com.example.petshop.data.model.Product
import com.example.petshop.ui.components.ProductCard
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PlaceholderText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText

data class TrendingKeyword(
    val rank: Int,
    val keyword: String,
    val searchCount: String,
    val isHot: Boolean = false
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchHistoryScreen(
    onBack: () -> Unit = {},
    onNavigateToCameraScan: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onSearchSubmit: (String) -> Unit = {},
    onProductClick: (Product) -> Unit = {},
    onAddToCart: (Product) -> Unit = {},
    viewModel: SearchViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        viewModel.onQueryChanged(searchQuery)
    }

    val liveResults = viewModel.searchResults
    val isLoading = viewModel.isLoading

    val recentSearches = remember {
        mutableStateListOf(
            "pate ciao churu",
            "cát đậu nành tofu",
            "sữa tắm sos chó",
            "vòng cổ quả bơ",
            "chuồng vận chuyển",
            "hạt cho cún poodle"
        )
    }

    val trendingKeywords = remember {
        listOf(
            TrendingKeyword(1, "Cát vệ sinh cho mèo khử mùi", "~2.4k lượt tìm", isHot = true),
            TrendingKeyword(2, "Pate tươi The Pet Vietnam", "~1.8k lượt tìm", isHot = true),
            TrendingKeyword(3, "Thức ăn hạt Royal Canin Kitten", "~1.5k lượt tìm", isHot = true),
            TrendingKeyword(4, "Bánh thưởng cún gặm sạch răng", "890 lượt tìm"),
            TrendingKeyword(5, "Đệm ngủ êm ái hình quả bơ", "740 lượt tìm"),
            TrendingKeyword(6, "Cần câu mèo lông vũ chuông", "610 lượt tìm"),
            TrendingKeyword(7, "Áo ấm cho cún mùa đông", "520 lượt tìm"),
            TrendingKeyword(8, "Gel dinh dưỡng mượt lông", "480 lượt tìm")
        )
    }

    Scaffold(
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
                    text = "Lịch sử & Xu hướng tìm kiếm",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                // Actions: Camera & Profile
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .clickable { onNavigateToCameraScan() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "Chụp ảnh",
                            tint = SecondaryText,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(AdminMaroon)
                            .clickable { onNavigateToProfile() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = "Tài khoản",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Search Bar & Search Button Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search Input Field
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
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

                        Box(modifier = Modifier.weight(1f)) {
                            if (searchQuery.isEmpty()) {
                                Text(
                                    text = "Tìm kiếm thức ăn hạt, pate, phụ...",
                                    fontSize = 13.sp,
                                    color = PlaceholderText,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            BasicTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = PrimaryText
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        if (searchQuery.isNotEmpty()) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = "Xóa",
                                tint = MutedText,
                                modifier = Modifier
                                    .size(18.dp)
                                    .clickable { searchQuery = "" }
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "Tìm bằng hình ảnh",
                                tint = MutedText,
                                modifier = Modifier
                                    .size(18.dp)
                                    .clickable { onNavigateToCameraScan() }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Primary Search Button
                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(PrimaryCTA)
                        .clickable {
                            if (searchQuery.isNotBlank()) {
                                onSearchSubmit(searchQuery)
                            }
                        }
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tìm",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // LIVE SEARCH RESULTS (When user types a, b, c or any characters)
            if (searchQuery.isNotBlank()) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = null,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Sản phẩm gợi ý (${liveResults.size})",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                        }

                        if (liveResults.isNotEmpty()) {
                            Text(
                                text = "Xem tất cả >",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText,
                                modifier = Modifier.clickable { onSearchSubmit(searchQuery) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = PrimaryCTA)
                        }
                    } else if (liveResults.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
                                .clip(RoundedCornerShape(16.dp))
                                .background(CardSurface)
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "🔍", fontSize = 36.sp)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Không có sản phẩm phù hợp cho \"$searchQuery\"",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Thử nhập \"hạt cho chó\", \"pate\", \"royal\"...",
                                    fontSize = 11.sp,
                                    color = MutedText
                                )
                            }
                        }
                    } else {
                        // 2-Column Grid of Live Filtered Products from Firebase
                        liveResults.chunked(2).forEach { pair ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                pair.forEach { prod ->
                                    ProductCard(
                                        product = prod,
                                        onProductClick = { onProductClick(prod) },
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
                }

                Spacer(modifier = Modifier.height(20.dp))
            } else {
                // NORMAL SEARCH HISTORY & TRENDING UI (When input is empty)

                // SECTION 1: Lịch sử tìm kiếm gần đây
                if (recentSearches.isNotEmpty()) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_history),
                                    contentDescription = null,
                                    tint = SecondarySoftText,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Lịch sử tìm kiếm gần đây",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { recentSearches.clear() }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_trash),
                                    contentDescription = "Xóa tất cả",
                                    tint = DangerText,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Xóa tất cả",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = DangerText
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Tags FlowRow
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            recentSearches.forEach { query ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(CardSurface)
                                        .border(1.dp, BorderDivider.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                                        .clickable {
                                            searchQuery = query
                                            onSearchSubmit(query)
                                        }
                                        .padding(horizontal = 14.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = query,
                                        fontSize = 13.sp,
                                        color = SecondaryText,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_close),
                                        contentDescription = "Xóa",
                                        tint = PlaceholderText,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .clickable { recentSearches.remove(query) }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }

                // SECTION 2: Từ khóa xu hướng
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_flame),
                                contentDescription = null,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Từ khóa xu hướng",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFFFE2DC))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "• Hot tuần này",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        trendingKeywords.forEach { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(CardSurface)
                                    .clickable {
                                        searchQuery = item.keyword
                                        onSearchSubmit(item.keyword)
                                    }
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val (badgeBg, badgeTextColor) = when (item.rank) {
                                    1 -> PrimaryCTA to Color.White
                                    2, 3 -> SecondarySoft to SecondarySoftText
                                    else -> SecondarySurface to SecondaryText
                                }

                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(badgeBg),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = item.rank.toString(),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = badgeTextColor
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = item.keyword,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = item.searchCount,
                                        fontSize = 11.sp,
                                        color = MutedText
                                    )
                                }

                                if (item.isHot) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_flame),
                                        contentDescription = null,
                                        tint = PrimaryCTA,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // SECTION 3: Khám phá danh mục được quan tâm
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_category),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Khám phá danh mục được quan tâm",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            CategoryExploreCard(
                                emoji = "🐶",
                                title = "Dành cho Cún",
                                subtitle = "Hạt, xương & p...",
                                bgColor = Color(0xFFFFF1EA),
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    searchQuery = "hạt cho chó"
                                    onSearchSubmit("hạt cho chó")
                                }
                            )
                            CategoryExploreCard(
                                emoji = "🐱",
                                title = "Dành cho Mèo",
                                subtitle = "Pate, cát & đồ...",
                                bgColor = Color(0xFFDCFCE7),
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    searchQuery = "hạt cho mèo"
                                    onSearchSubmit("hạt cho mèo")
                                }
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            CategoryExploreCard(
                                emoji = "⚡",
                                title = "Ưu đãi Flash",
                                subtitle = "Giảm sốc tới 50%...",
                                bgColor = Color(0xFFFFE2DC),
                                modifier = Modifier.weight(1f)
                            )
                            CategoryExploreCard(
                                emoji = "🎁",
                                title = "Voucher Hot",
                                subtitle = "Mã giảm 50k &...",
                                bgColor = Color(0xFFFEF3C7),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // BOTTOM BANNER CARD
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .clickable { onNavigateToCameraScan() }
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFE2DC)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🥑", fontSize = 22.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Bạn đang tìm sản phẩm đặc trị?",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Bé cưng bị kén ăn hoặc viêm da? Dùng camera để chúng mình gợi ý ngay nhé!",
                                fontSize = 12.sp,
                                color = SecondaryText,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun CategoryExploreCard(
    emoji: String,
    title: String,
    subtitle: String,
    bgColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(bgColor),
            contentAlignment = Alignment.Center
        ) {
            Text(text = emoji, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = MutedText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

package com.example.petshop.feature.search

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
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

data class SimilarProductItem(
    val id: String,
    val title: String,
    val discountPercent: String,
    val matchTag: String? = null,
    val recommendationTag: String? = null,
    val rating: String,
    val currentPrice: String,
    val oldPrice: String,
    val petEmoji: String = "🐱"
)

@Composable
fun ImageSearchResultScreen(
    onBack: () -> Unit = {},
    onClose: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToCameraScan: () -> Unit = {},
    onAddToCart: (String) -> Unit = {},
    onBuyNow: (String) -> Unit = {}
) {
    var selectedFilterIndex by remember { mutableStateOf(1) }

    val similarProducts = remember {
        listOf(
            SimilarProductItem(
                id = "sp1",
                title = "Hạt Royal Canin Mother & Babycat...",
                discountPercent = "-18%",
                matchTag = "Độ tương đồng 94%",
                rating = "4.9 (1.8k)",
                currentPrice = "395.000đ",
                oldPrice = "480.000đ"
            ),
            SimilarProductItem(
                id = "sp2",
                title = "Hạt Royal Canin Indoor 27 mèo nuôi...",
                discountPercent = "-19%",
                matchTag = "Độ tương đồng 89%",
                rating = "4.8 (950)",
                currentPrice = "395.000đ",
                oldPrice = "488.000đ"
            ),
            SimilarProductItem(
                id = "sp3",
                title = "Súp thưởng Ciao Churu thanh 4x14g...",
                discountPercent = "-25%",
                recommendationTag = "Ăn kèm đề xuất",
                rating = "5.0 (4.3k)",
                currentPrice = "38.000đ",
                oldPrice = "50.000đ"
            ),
            SimilarProductItem(
                id = "sp4",
                title = "Hạt SmartHeart Gold Kitten 1kg",
                discountPercent = "-15%",
                recommendationTag = "Giá tiết kiệm",
                rating = "4.7 (620)",
                currentPrice = "125.000đ",
                oldPrice = "148.000đ",
                petEmoji = "🐶"
            )
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
                    text = "Kết Quả Tìm Kiếm Bằng Ảnh",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                // Actions: Close & Profile
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .clickable { onClose() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Đóng",
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

            // Top Status Bar (AI Indicator + Rescan button + Cart icon)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(SuccessText)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Trí tuệ nhân tạo Lens AI",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryText
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Rescan button
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(SecondarySoft)
                            .clickable { onNavigateToCameraScan() }
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                            tint = SecondarySoftText,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Chụp lại",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // Cart Icon with Badge
                    Box(contentAlignment = Alignment.TopEnd) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SecondarySoft),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shopping_bag),
                                contentDescription = "Giỏ hàng",
                                tint = SecondarySoftText,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        // Badge count
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(PrimaryCTA),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "3",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // DETECTED AI CROP QUERY CARD
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            // Image Crop Preview Box
                            Box(
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFFFF7F2))
                                    .border(2.dp, PrimaryCTA, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "🐱", fontSize = 28.sp)
                                    Text(
                                        text = "RC Kitten",
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondarySoftText
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            // Product Identified Title & Subtitle
                            Column(modifier = Modifier.weight(1f)) {
                                // Match badge top right
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(SuccessBg)
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_check),
                                            contentDescription = null,
                                            tint = SuccessText,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "Khớp 98% chính xác",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = SuccessText
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = "Hạt dinh dưỡng mèo Royal Canin Kitten",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText,
                                    lineHeight = 18.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Túi 2kg • Mèo con từ 4 đến 12 tháng",
                                    fontSize = 12.sp,
                                    color = MutedText
                                )
                            }

                            Spacer(modifier = Modifier.width(6.dp))

                            // Crop Region Action Button
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(SecondarySoft)
                                    .padding(horizontal = 8.dp, vertical = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_crop_region),
                                        contentDescription = null,
                                        tint = SecondarySoftText,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "Đổi vùng",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondarySoftText
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // FILTER & SORT PILLS ROW
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Filter Pill 1: Bộ lọc
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(CardSurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                            .clickable { }
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = null,
                            tint = SecondaryText,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Bộ lọc",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText
                        )
                    }

                    // Filter Pill 2 (Selected): Chính xác nhất
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(PrimaryCTA)
                            .clickable { selectedFilterIndex = 1 }
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Chính xác nhất",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Filter Pill 3: Cùng thương hiệu
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(CardSurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                            .clickable { selectedFilterIndex = 2 }
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cùng thương hiệu",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // RESULTS COUNT HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sparkle),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Tìm thấy 24 sản phẩm tương đồng nhất",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Text(
                    text = "Sắp xếp: Tự động",
                    fontSize = 12.sp,
                    color = MutedText
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // BEST MATCH FEATURED CARD (#1 Result)
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                // Top Match Badges
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomEnd = 12.dp))
                            .background(AdminMaroon)
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Khớp cao nhất 99%",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 12.dp))
                            .background(SecondarySoft)
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_flame),
                                contentDescription = null,
                                tint = SecondarySoftText,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Bán chạy #1",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }
                }

                // Best Match Product Container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(6.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.Top) {
                            // Image Box with -21% badge
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color(0xFFFFF7F2)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "🐱", fontSize = 48.sp)

                                // Discount Badge
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopStart)
                                        .clip(RoundedCornerShape(bottomEnd = 10.dp, topStart = 16.dp))
                                        .background(PrimaryCTA)
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = "-21%",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            // Details Column
                            Column(modifier = Modifier.weight(1f)) {
                                // Tags row
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(Color(0xFFFEE2E2))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "MALL 100%",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = DangerText
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_shield_check),
                                            contentDescription = null,
                                            tint = SuccessText,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Text(
                                            text = "Chính Hãng",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = SuccessText
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Thức ăn hạt Royal Canin Kitten cho mèo con 2kg",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText,
                                    lineHeight = 18.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                // Rating & Sales
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_star),
                                        contentDescription = null,
                                        tint = Color(0xFFFFB800),
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = "4.9",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFB45309)
                                    )
                                    Text(
                                        text = " • Đã bán 2.1k",
                                        fontSize = 11.sp,
                                        color = MutedText
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                // Price Row
                                Row(verticalAlignment = Alignment.Bottom) {
                                    Text(
                                        text = "385.000đ",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = PrimaryText
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "490.000đ",
                                        fontSize = 12.sp,
                                        color = MutedText,
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Action Buttons Row inside Card
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Button 1: Thêm vào giỏ
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .clip(RoundedCornerShape(22.dp))
                                    .background(SecondarySoft)
                                    .clickable { onAddToCart("best_match") },
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_cart_add),
                                        contentDescription = null,
                                        tint = SecondarySoftText,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Thêm vào giỏ",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SecondarySoftText
                                    )
                                }
                            }

                            // Button 2: Mua ngay
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                                    .clip(RoundedCornerShape(22.dp))
                                    .background(PrimaryCTA)
                                    .clickable { onBuyNow("best_match") },
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_lightning),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Mua ngay",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // FLOATING ACTION PILL BAR (Không đúng ý bạn? Chụp ảnh khác)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .shadow(8.dp, RoundedCornerShape(30.dp), ambientColor = BorderDivider)
                    .clip(RoundedCornerShape(30.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(30.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(SuccessText)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Không đúng ý bạn?",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(AdminMaroon)
                            .clickable { onNavigateToCameraScan() }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Chụp ảnh khác",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // SECTION 2: Gợi ý tương tự cho bạn
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(16.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(PrimaryCTA)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Gợi ý tương tự cho bạn",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Text(
                        text = "Xem tất cả >",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText,
                        modifier = Modifier.clickable { }
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // 2-Column Grid of Similar Products
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SimilarProductCard(
                            item = similarProducts[0],
                            modifier = Modifier.weight(1f),
                            onAddToCart = { onAddToCart(it) }
                        )
                        SimilarProductCard(
                            item = similarProducts[1],
                            modifier = Modifier.weight(1f),
                            onAddToCart = { onAddToCart(it) }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SimilarProductCard(
                            item = similarProducts[2],
                            modifier = Modifier.weight(1f),
                            onAddToCart = { onAddToCart(it) }
                        )
                        SimilarProductCard(
                            item = similarProducts[3],
                            modifier = Modifier.weight(1f),
                            onAddToCart = { onAddToCart(it) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // BOTTOM GUARANTEE BANNER CARD
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(SuccessBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shield_check),
                            contentDescription = null,
                            tint = SuccessText,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Cam kết từ Avocado PetShop",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Đền 200% nếu phát hiện hàng giả • Đổi trả miễn phí trong 7 ngày.",
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

@Composable
fun SimilarProductCard(
    item: SimilarProductItem,
    modifier: Modifier = Modifier,
    onAddToCart: (String) -> Unit = {}
) {
    var isFavorite by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider)
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
    ) {
        Column {
            // Image Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color(0xFFFFF7F2)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.petEmoji, fontSize = 44.sp)

                // Discount Badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(PrimaryCTA)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = item.discountPercent,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                // Heart Favorite Icon
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(30.dp)
                        .shadow(2.dp, CircleShape)
                        .clip(CircleShape)
                        .background(CardSurface)
                        .clickable { isFavorite = !isFavorite },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null,
                        tint = if (isFavorite) DangerText else BorderDivider,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            // Details Content
            Column(modifier = Modifier.padding(10.dp)) {
                // Match or Recommendation Tag
                if (!item.matchTag.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SuccessBg)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = item.matchTag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                } else if (!item.recommendationTag.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFFEF3C7))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = item.recommendationTag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFB45309)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }

                // Title
                Text(
                    text = item.title,
                    fontSize = 13.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = item.rating,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB45309)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Price & Cart Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = item.currentPrice,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryText
                        )
                        Text(
                            text = item.oldPrice,
                            fontSize = 10.sp,
                            color = MutedText,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                    // Cart Circle Button
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .shadow(2.dp, CircleShape)
                            .clip(CircleShape)
                            .background(SecondarySoft)
                            .clickable { onAddToCart(item.id) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shopping_bag),
                            contentDescription = null,
                            tint = SecondarySoftText,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

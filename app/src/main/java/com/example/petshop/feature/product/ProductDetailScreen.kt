package com.example.petshop.feature.product

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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

enum class DetailSheetAction { NONE, ADD_TO_CART, BUY_NOW }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    product: Product? = null,
    onBack: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onAddToCart: (Product) -> Unit = {},
    onBuyNow: () -> Unit = {}
) {
    var selectedWeightIndex by remember { mutableStateOf(1) } // 1: Túi 2kg
    var isFavorite by remember { mutableStateOf(false) }

    // Bottom Sheet Action State
    var activeSheetAction by remember { mutableStateOf(DetailSheetAction.NONE) }
    var sheetQuantity by remember { mutableStateOf(1) }
    var sheetSelectedVariantIndex by remember { mutableStateOf(1) }

    val variantOptions = listOf("Túi 400g", "Túi 2kg Tiêu chuẩn", "Bao Lớn 10kg")

    val titleText = product?.title?.ifEmpty { null } ?: "Thức ăn hạt Royal Canin Kitten cho mèo con từ 4 đến 12 tháng tuổi – Túi 2kg Dinh dưỡng toàn diện"
    val brandText = product?.brand?.ifEmpty { null } ?: "Royal Canin (Pháp)"
    val currentPriceText = product?.currentPrice?.ifEmpty { null } ?: "385.000đ"
    val oldPriceText = product?.oldPrice?.ifEmpty { null } ?: "490.000đ"
    val discountText = product?.discountPercent?.ifEmpty { null } ?: "-21%"
    val ratingVal = if (product != null && product.rating > 0.0) "${product.rating}" else "4.9"
    val soldCountText = product?.soldCount?.ifEmpty { null } ?: "5.4k"
    val badgeText = product?.badge ?: "Bán chạy #1"
    val imageUrl = product?.imageUrl ?: ""

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
                    // Consultation button
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { }
                            .padding(end = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_headset),
                            contentDescription = "Tư vấn",
                            tint = SecondaryText,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Tư vấn",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText
                        )
                    }

                    // Add to Cart button (Triggers BottomSheet)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable {
                                activeSheetAction = DetailSheetAction.ADD_TO_CART
                            }
                            .padding(end = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart_add),
                            contentDescription = "Thêm giỏ",
                            tint = SecondaryText,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Thêm giỏ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText
                        )
                    }

                    // Primary Buy Now Button (Triggers BottomSheet)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(PrimaryCTA)
                            .clickable {
                                activeSheetAction = DetailSheetAction.BUY_NOW
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_lightning),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Mua ngay • $currentPriceText",
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
                    .padding(horizontal = 16.dp, vertical = 10.dp),
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

                Text(
                    text = brandText,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Share
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Chia sẻ",
                            tint = SecondaryText,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Favorite
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .clickable { isFavorite = !isFavorite },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = "Yêu thích",
                            tint = if (isFavorite) DangerText else SecondaryText,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Cart Icon with Badge
                    Box(contentAlignment = Alignment.TopEnd) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SecondarySoft)
                                .clickable { onNavigateToCart() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shopping_bag),
                                contentDescription = "Giỏ hàng",
                                tint = SecondarySoftText,
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
                                text = "3",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // Hero Product Graphic Banner Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(0xFFFFF7F2)),
                contentAlignment = Alignment.Center
            ) {
                if (imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    // Product Graphic Fallback
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = if (product?.petType == "dog") "🐶🥣" else "🐱🥣",
                            fontSize = 84.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = brandText.uppercase(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = SecondarySoftText
                        )
                    }
                }

                // Top Left Badges
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(PrimaryCTA)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "✔ Mall Chính Hãng 100%",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(SuccessBg)
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = "🌱 Bao bì thân thiện",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                }

                // Bottom Overlay Bar (Video clip & indicator)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.9f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "▶", fontSize = 10.sp, color = PrimaryCTA)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Xem clip Boss ăn thử",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.5f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "1/6",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            // Flash Deal Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryCTA)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_flame),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "AVOCADO FLASH DEAL",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }

                    // Timer Box
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Kết thúc sau ",
                            fontSize = 11.sp,
                            color = Color.White
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color.White)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "04 : 28 : 15",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryCTA
                            )
                        }
                    }
                }
            }

            // Product Title & Price Details Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardSurface)
                    .padding(16.dp)
            ) {
                // Price Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = currentPriceText,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PrimaryCTA
                    )
                    if (oldPriceText.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = oldPriceText,
                            fontSize = 14.sp,
                            color = MutedText,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                    if (discountText.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFFEE2E2))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = discountText,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DangerText
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Hot Badge Tag
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SecondarySoft)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = badgeText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = titleText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Rating & Stats
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$ratingVal ⭐⭐⭐⭐⭐ (2.1k đánh giá)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFB45309)
                    )
                    Text(text = " • Đã bán $soldCountText • ", fontSize = 12.sp, color = MutedText)
                    Text(
                        text = "❤ 1.8k Sen thích",
                        fontSize = 12.sp,
                        color = DangerText,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Weight Variant Selector Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardSurface)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Chọn phân loại khối lượng",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }
                    Text(
                        text = "Bảng khẩu phần >",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 3 Options
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    VariantCard(
                        title = "Túi 400g",
                        price = "115.000đ",
                        isSelected = selectedWeightIndex == 0,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            selectedWeightIndex = 0
                            sheetSelectedVariantIndex = 0
                        }
                    )
                    VariantCard(
                        title = "✓ Tiêu chuẩn",
                        price = currentPriceText,
                        badge = "Tiết kiệm 20%",
                        isSelected = selectedWeightIndex == 1,
                        modifier = Modifier.weight(1.2f),
                        onClick = {
                            selectedWeightIndex = 1
                            sheetSelectedVariantIndex = 1
                        }
                    )
                    VariantCard(
                        title = "Bao Lớn",
                        price = "1.650.000đ",
                        isSelected = selectedWeightIndex == 2,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            selectedWeightIndex = 2
                            sheetSelectedVariantIndex = 2
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Vouchers & Offers Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardSurface)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_ticket),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Ưu đãi & Voucher của Sen",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }
                    Text(
                        text = "3 mã khả dụng",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryCTA
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFFF1EA))
                            .border(1.dp, BorderDivider, RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "🎟 Giảm 30k đơn 300k",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = AdminMaroon
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFFF1EA))
                            .border(1.dp, BorderDivider, RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "🚚 Freeship Extra",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = AdminMaroon
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // HALF-SCREEN BOTTOM SHEET FOR VARIANT & QUANTITY SELECTION
        if (activeSheetAction != DetailSheetAction.NONE) {
            ModalBottomSheet(
                onDismissRequest = { activeSheetAction = DetailSheetAction.NONE },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = CardSurface,
                scrimColor = Color.Black.copy(alpha = 0.5f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    // Header Product Info Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFFFF7F2))
                                .border(1.dp, BorderDivider, RoundedCornerShape(16.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageUrl.isNotEmpty()) {
                                AsyncImage(
                                    model = imageUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Text(text = if (product?.petType == "dog") "🐶" else "🐱", fontSize = 36.sp)
                            }
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = currentPriceText,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = PrimaryCTA
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Kho hàng: Còn 128 sản phẩm",
                                fontSize = 12.sp,
                                color = MutedText
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Đã chọn: ${variantOptions[sheetSelectedVariantIndex]}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(SecondarySurface)
                                .clickable { activeSheetAction = DetailSheetAction.NONE },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "✕", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderDivider))
                    Spacer(modifier = Modifier.height(16.dp))

                    // Variant Options Section
                    Text(
                        text = "Chọn phân loại / Quy cách:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        variantOptions.forEachIndexed { idx, title ->
                            val isSelected = idx == sheetSelectedVariantIndex
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) SecondarySoft else CardSurface)
                                    .border(
                                        1.dp,
                                        if (isSelected) AdminMaroon else BorderDivider,
                                        RoundedCornerShape(12.dp)
                                    )
                                    .clickable {
                                        sheetSelectedVariantIndex = idx
                                        selectedWeightIndex = idx
                                    }
                                    .padding(vertical = 10.dp, horizontal = 6.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = title,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) AdminMaroon else SecondaryText,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // Quantity Stepper Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Số lượng mua:",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(SecondarySurface)
                                .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .clickable { if (sheetQuantity > 1) sheetQuantity-- },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "−", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryText)
                            }

                            Text(
                                text = "$sheetQuantity",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText,
                                modifier = Modifier.padding(horizontal = 14.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .clickable { sheetQuantity++ },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "+", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PrimaryText)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Bottom Sheet Action Confirm Button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(PrimaryCTA)
                            .clickable {
                                val isBuyNow = activeSheetAction == DetailSheetAction.BUY_NOW
                                activeSheetAction = DetailSheetAction.NONE
                                if (isBuyNow) {
                                    onBuyNow()
                                } else {
                                    if (product != null) {
                                        onAddToCart(product)
                                    }
                                    onNavigateToCart()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (activeSheetAction == DetailSheetAction.BUY_NOW) "Xác nhận mua ngay ⚡" else "Xác nhận thêm vào giỏ 🛒",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun VariantCard(
    title: String,
    price: String,
    badge: String? = null,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(
                1.5.dp,
                if (isSelected) AdminMaroon else BorderDivider,
                RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (badge != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(SuccessBg)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = badge,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = SuccessText
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) AdminMaroon else PrimaryText
            )
            Text(
                text = price,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = DangerText
            )
        }
    }
}

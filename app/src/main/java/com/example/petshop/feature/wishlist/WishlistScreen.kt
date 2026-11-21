package com.example.petshop.feature.wishlist

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.example.petshop.data.model.WishlistItem
import com.example.petshop.data.model.sampleWishlistItems
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun WishlistScreen(
    onBack: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    var selectedCategoryFilter by remember { mutableStateOf("🐾 Tất cả") }
    var isSelectAllChecked by remember { mutableStateOf(false) }
    var wishlistItems by remember { mutableStateOf<List<WishlistItem>>(sampleWishlistItems) }

    DisposableEffect(Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val listener = FirebaseFirestore.getInstance()
                .collection("wishlists")
                .whereEqualTo("userId", currentUser.uid)
                .addSnapshotListener { snapshot, error ->
                    if (error == null && snapshot != null && !snapshot.isEmpty) {
                        val fetchedItems = snapshot.documents.mapNotNull { doc ->
                            try {
                                doc.toObject(WishlistItem::class.java)?.copy(id = doc.id)
                            } catch (e: Exception) {
                                null
                            }
                        }
                        if (fetchedItems.isNotEmpty()) {
                            wishlistItems = fetchedItems
                        }
                    }
                }
            onDispose { listener.remove() }
        } else {
            onDispose { }
        }
    }

    fun removeWishlistItem(item: WishlistItem) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null && item.id.isNotBlank()) {
            try {
                FirebaseFirestore.getInstance()
                    .collection("wishlists")
                    .document(item.id)
                    .delete()
            } catch (e: Exception) {
                // Ignore exception safely
            }
        }
        wishlistItems = wishlistItems.filter { it.id != item.id }
    }

    fun clearAllWishlist() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            try {
                val db = FirebaseFirestore.getInstance()
                db.collection("wishlists")
                    .whereEqualTo("userId", currentUser.uid)
                    .get()
                    .addOnSuccessListener { snapshot ->
                        val batch = db.batch()
                        snapshot.documents.forEach { doc ->
                            batch.delete(doc.reference)
                        }
                        batch.commit()
                    }
            } catch (e: Exception) {
                // Ignore exception safely
            }
        }
        wishlistItems = emptyList()
    }

    val filteredItems = remember(wishlistItems, selectedCategoryFilter) {
        if (selectedCategoryFilter.contains("Tất cả")) {
            wishlistItems
        } else if (selectedCategoryFilter.contains("Thức ăn")) {
            wishlistItems.filter { it.title.contains("Hạt", true) || it.title.contains("Súp", true) || it.title.contains("Pate", true) }
        } else {
            wishlistItems.filter { it.title.contains("Cát", true) || it.title.contains("Tofu", true) }
        }
    }

    val categoryFilters = listOf(
        "🐾 Tất cả (${wishlistItems.size})",
        "Thức ăn & Pate",
        "Cát vệ sinh"
    )

    Scaffold(
        bottomBar = {
            WishlistBottomActionBar(
                itemCount = wishlistItems.size,
                onClearAll = { clearAllWishlist() },
                onAddToCartAll = onNavigateToCart
            )
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Top Bar
            WishlistTopBar(
                onBack = onBack,
                onSearchClick = onNavigateToSearch,
                onProfileClick = onNavigateToProfile
            )

            // Header Row: Count, Select All, Filter Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${wishlistItems.size} món bé yêu",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(SecondarySurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(14.dp))
                            .clickable { isSelectAllChecked = !isSelectAllChecked }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (isSelectAllChecked) "☑ Chọn tất cả" else "☐ Chọn tất cả",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondarySurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(12.dp))
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Filter",
                            tint = SecondaryText,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Price Drop Notification Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(SecondarySoft)
                    .border(1.dp, BorderDivider, RoundedCornerShape(18.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(PrimaryCTA),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lightning),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Giá tốt vừa cập nhật!",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = AdminMaroon
                        )
                        Text(
                            text = "3 món trong danh sách vừa giảm giá trong 24h qua.",
                            fontSize = 11.sp,
                            color = SecondaryText,
                            lineHeight = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(CardSurface)
                            .clickable { }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "Xem ngay",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = AdminMaroon
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Category Filter Chips & View Toggle Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(categoryFilters) { filter ->
                        val isSelected = filter == selectedCategoryFilter || (selectedCategoryFilter.startsWith("🐾 Tất cả") && filter.startsWith("🐾 Tất cả"))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) PrimaryCTA else CardSurface)
                                .border(1.dp, if (isSelected) PrimaryCTA else BorderDivider, RoundedCornerShape(16.dp))
                                .clickable { selectedCategoryFilter = filter }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = filter,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else SecondaryText
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Layout Toggle Button Group
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(12.dp))
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(CardSurface),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "田", fontSize = 14.sp, color = AdminMaroon)
                    }
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "☰", fontSize = 14.sp, color = MutedText)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 2-Column Product Grid
            if (filteredItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "💔", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Danh sách yêu thích trống",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = "Hãy thả tim sản phẩm bạn thích để lưu lại đây nhé",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredItems) { item ->
                        WishlistProductCard(
                            item = item,
                            onRemoveWishlist = { removeWishlistItem(item) },
                            onAddToCart = { }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WishlistTopBar(
    onBack: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, CircleShape)
                .clickable { onBack() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back",
                tint = PrimaryText,
                modifier = Modifier.size(18.dp)
            )
        }

        Text(
            text = "Danh Sách Yêu Thích",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, CircleShape)
                    .clickable { onSearchClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = SecondaryText,
                    modifier = Modifier.size(18.dp)
                )
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, CircleShape)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "Favorites",
                    tint = AdminMaroon,
                    modifier = Modifier.size(18.dp)
                )
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(AdminMaroon)
                    .clickable { onProfileClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "Profile",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun WishlistProductCard(
    item: WishlistItem,
    onRemoveWishlist: () -> Unit,
    onAddToCart: (WishlistItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
    ) {
        Column {
            // Product Graphic Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color(0xFFFFF7F2)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when {
                        item.title.contains("Royal Canin") -> "🐱"
                        item.title.contains("Tofu") -> "🌿"
                        item.title.contains("Ciao") -> "🐟"
                        item.title.contains("Bát") -> "🥣"
                        item.title.contains("Cần") -> "🎣"
                        else -> "🧴"
                    },
                    fontSize = 44.sp
                )

                // Top-Left Badge if any
                item.badge?.let { badge ->
                    val (bg, txt) = when (item.badgeType) {
                        "green" -> SuccessBg to SuccessText
                        "pink" -> SecondarySoft to AdminMaroon
                        else -> SecondarySoft to PrimaryCTA
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(bg)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = badge,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = txt
                        )
                    }
                }

                // Bottom-Left Discount Badge if any
                item.discount?.let { discount ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(PrimaryCTA)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = discount,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                // Top-Right Heart Filled Icon (Click to remove from wishlist)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(CardSurface)
                        .shadow(2.dp, CircleShape)
                        .clickable { onRemoveWishlist() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = "Remove favorite",
                        tint = DangerText,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            // Product Details
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = item.title,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Rating & Review Count
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${item.rating} ",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB45309)
                    )
                    Text(
                        text = "(${item.reviewCount})",
                        fontSize = 10.sp,
                        color = MutedText
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Price and Add button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        if (item.oldPrice != null) {
                            Text(
                                text = item.oldPrice,
                                fontSize = 10.sp,
                                color = MutedText,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }

                        Text(
                            text = item.price,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(PrimaryCTA)
                            .clickable { onAddToCart(item) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WishlistBottomActionBar(
    itemCount: Int,
    onClearAll: () -> Unit,
    onAddToCartAll: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(0.9f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SecondarySoft)
                    .clickable { onClearAll() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🗑 Xóa hết",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = AdminMaroon
                )
            }

            Box(
                modifier = Modifier
                    .weight(2.1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(PrimaryCTA)
                    .clickable { onAddToCartAll() },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shopping_bag),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Thêm tất cả vào giỏ ($itemCount món)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

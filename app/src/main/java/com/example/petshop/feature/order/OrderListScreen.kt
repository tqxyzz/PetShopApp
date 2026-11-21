package com.example.petshop.feature.order

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.data.model.Order
import com.example.petshop.data.model.OrderItem
import com.example.petshop.data.model.OrderStatusType
import com.example.petshop.data.model.sampleOrders
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerBg
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.IconPrimaryBg
import com.example.petshop.ui.theme.IconPrimaryTint
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PlaceholderText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoft
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText
import com.example.petshop.ui.theme.WarningBg
import com.example.petshop.ui.theme.WarningText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun OrderListScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToAssistant: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilterTab by remember { mutableStateOf("Tất cả (4)") }
    var orderList by remember { mutableStateOf<List<Order>>(sampleOrders) }

    DisposableEffect(Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val listener = FirebaseFirestore.getInstance()
                .collection("orders")
                .whereEqualTo("userId", currentUser.uid)
                .addSnapshotListener { snapshot, error ->
                    if (error == null && snapshot != null && !snapshot.isEmpty) {
                        val fetchedOrders = snapshot.documents.mapNotNull { doc ->
                            try {
                                doc.toObject(Order::class.java)?.copy(orderId = doc.id)
                            } catch (e: Exception) {
                                null
                            }
                        }
                        if (fetchedOrders.isNotEmpty()) {
                            orderList = fetchedOrders
                        }
                    }
                }
            onDispose { listener.remove() }
        } else {
            onDispose { }
        }
    }

    val filteredOrders = remember(orderList, selectedFilterTab, searchQuery) {
        orderList.filter { order ->
            val matchesSearch = searchQuery.isBlank() ||
                    order.orderId.contains(searchQuery, ignoreCase = true) ||
                    order.items.any { it.title.contains(searchQuery, ignoreCase = true) }

            val matchesFilter = when (selectedFilterTab) {
                "Chờ xác nhận •" -> order.statusType == OrderStatusType.PENDING_CONFIRM
                "Đang chuẩn bị" -> order.statusType == OrderStatusType.PENDING_CONFIRM
                "Đang giao •" -> order.statusType == OrderStatusType.EXPRESS_SHIPPING
                "Đã giao" -> order.statusType == OrderStatusType.COMPLETED || order.statusType == OrderStatusType.DELIVERED
                "Đã hủy" -> false
                else -> true
            }

            matchesSearch && matchesFilter
        }
    }

    val filterOptions = listOf(
        "Tất cả (${orderList.size})",
        "Chờ xác nhận •",
        "Đang chuẩn bị",
        "Đang giao •",
        "Đã giao",
        "Đã hủy"
    )

    Scaffold(
        bottomBar = {
            OrderBottomNav(
                selectedTab = "order",
                onTabClick = { tab ->
                    when (tab) {
                        "home" -> onNavigateToHome()
                        "category" -> onNavigateToCategory()
                        "assistant" -> onNavigateToAssistant()
                        "profile" -> onNavigateToProfile()
                    }
                }
            )
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Top Header Bar
            OrderHeader(
                onSearchClick = {},
                onProfileClick = onNavigateToProfile
            )

            // Search Bar & Filter Button
            OrderSearchBar(
                searchQuery = searchQuery,
                onQueryChange = { searchQuery = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Chips Horizontal List
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(filterOptions) { filter ->
                    val isSelected = filter == selectedFilterTab || (selectedFilterTab.startsWith("Tất cả") && filter.startsWith("Tất cả"))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) AdminMaroon else SecondarySurface)
                            .border(
                                1.dp,
                                if (isSelected) AdminMaroon else BorderDivider,
                                RoundedCornerShape(20.dp)
                            )
                            .clickable { selectedFilterTab = filter }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = filter,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else SecondaryText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Orders List
            if (filteredOrders.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "📦", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Chưa có đơn hàng nào",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = "Các đơn hàng bạn đã mua sẽ xuất hiện tại đây",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(filteredOrders) { order ->
                        OrderCardItem(order = order)
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderHeader(
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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
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
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Danh Sách Đơn Hàng",
                fontSize = 20.sp,
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
private fun OrderSearchBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(46.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = PlaceholderText,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (searchQuery.isEmpty()) "Tìm kiếm theo mã đơn hoặc tên sản phẩm..." else searchQuery,
                    fontSize = 13.sp,
                    color = if (searchQuery.isEmpty()) PlaceholderText else PrimaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "Filter",
                tint = SecondaryText,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun OrderCardItem(order: Order) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
            .padding(14.dp)
    ) {
        Column {
            // Header Row: Order ID, Badge, Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = order.orderId,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    // Badge pill
                    val (badgeBg, badgeText) = when (order.statusType) {
                        OrderStatusType.EXPRESS_SHIPPING -> SecondarySoft to SecondarySoftText
                        OrderStatusType.COMPLETED, OrderStatusType.DELIVERED -> SuccessBg to SuccessText
                        OrderStatusType.PENDING_CONFIRM -> WarningBg to WarningText
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(badgeBg)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = order.badge,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = badgeText
                        )
                    }
                }

                Text(
                    text = order.statusText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (order.statusType == OrderStatusType.EXPRESS_SHIPPING) DangerText else MutedText
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Driver Card Container if available
            order.driverInfo?.let { driver ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(14.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(IconPrimaryBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_truck),
                                    contentDescription = null,
                                    tint = PrimaryCTA,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = driver.name,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        append("Đang cách nhà bạn ")
                                        withStyle(SpanStyle(color = AdminMaroon, fontWeight = FontWeight.Bold)) {
                                            append("1.2 km")
                                        }
                                        append(" (dự kiến 7 phút)")
                                    },
                                    fontSize = 11.sp,
                                    color = SecondaryText
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(CardSurface)
                                .border(1.dp, BorderDivider, CircleShape)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_phone),
                                contentDescription = "Call",
                                tint = AdminMaroon,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Order Items
            order.items.forEachIndexed { index, item ->
                OrderItemRow(item = item)
                if (index < order.items.size - 1) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Total payment line
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tổng thanh toán (${order.totalItemCount} món):",
                    fontSize = 13.sp,
                    color = SecondaryText
                )
                Text(
                    text = order.totalPayment,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryText
                )
            }

            // Delivery Notice if present
            order.deliveryNotice?.let { notice ->
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(SecondarySurface)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = notice,
                        fontSize = 11.sp,
                        color = SecondaryText
                    )
                }
            }

            // Cancel Notice if present
            order.cancelNotice?.let { notice ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = notice,
                    fontSize = 11.sp,
                    color = MutedText
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (order.statusType) {
                    OrderStatusType.EXPRESS_SHIPPING -> {
                        Box(
                            modifier = Modifier
                                .weight(1.3f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(AdminMaroon)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "📍 Theo dõi vận chuyển",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(0.9f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(SecondarySoft)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "💬 Hỗ trợ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }

                    OrderStatusType.COMPLETED -> {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(CardSurface)
                                .border(1.dp, BorderDivider, RoundedCornerShape(14.dp))
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Đổi trả / Hoàn tiền",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(SecondarySoft)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "★ Đánh giá",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(AdminMaroon)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🔄 Mua lại",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    OrderStatusType.PENDING_CONFIRM -> {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(DangerBg)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "❌ Hủy đơn hàng",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = DangerText
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(SecondarySoft)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Chi tiết đơn",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }

                    OrderStatusType.DELIVERED -> {
                        Box(
                            modifier = Modifier
                                .weight(1.3f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color(0xFF436B48))
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "✔ Đã nhận được hàng",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(0.9f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(AdminMaroon)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🔄 Mua lại",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderItemRow(item: OrderItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Product Graphic Box
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when {
                    item.title.contains("Royal Canin") -> "🐱"
                    item.title.contains("Tofu") -> "🌿"
                    item.title.contains("Ciao") -> "🐟"
                    item.title.contains("Bát") -> "🥣"
                    item.title.contains("Cần") -> "🎣"
                    else -> "🔔"
                },
                fontSize = 28.sp
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item.subtitle,
                fontSize = 11.sp,
                color = MutedText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "x${item.quantity}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryText
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = item.price,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText
        )
    }
}

@Composable
private fun OrderBottomNav(
    selectedTab: String,
    onTabClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .padding(vertical = 10.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val navItems = listOf(
                Triple("home", "Khám phá", R.drawable.ic_home),
                Triple("category", "Cửa hàng", R.drawable.ic_store),
                Triple("order", "Đơn hàng", R.drawable.ic_ticket),
                Triple("pet", "Bé cưng", R.drawable.ic_paw),
                Triple("profile", "Tài khoản", R.drawable.ic_person)
            )

            navItems.forEach { (route, label, iconRes) ->
                val isSelected = route == selectedTab

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onTabClick(route) }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = label,
                        tint = if (isSelected) PrimaryCTA else MutedText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) PrimaryCTA else MutedText
                    )
                }
            }
        }
    }
}

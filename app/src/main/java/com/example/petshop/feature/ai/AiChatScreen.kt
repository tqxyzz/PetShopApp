package com.example.petshop.feature.ai

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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.data.model.ChatMessage
import com.example.petshop.data.model.RecommendedProduct
import com.example.petshop.data.model.sampleChatMessages
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
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
import com.example.petshop.utils.UserAccountUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AiChatScreen(
    onBack: () -> Unit = {},
    onNavigateToProductDetail: (String) -> Unit = {},
    onAddToCart: (String) -> Unit = {}
) {
    var inputText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf(*sampleChatMessages.toTypedArray()) }
    val coroutineScope = rememberCoroutineScope()
    var isBotLoading by remember { mutableStateOf(false) }
    var currentAccountId by remember { mutableStateOf("acc_002") }
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        GeminiAiService.fetchStoreProducts()

        UserAccountUtils.fetchCurrentAccountId { accId ->
            currentAccountId = accId
            try {
                FirebaseFirestore.getInstance().collection("chats")
                    .whereEqualTo("ma_tai_khoan", accId)
                    .get()
                    .addOnSuccessListener { snapshot ->
                        if (snapshot != null && !snapshot.isEmpty) {
                            val historyMsgs = snapshot.documents.mapNotNull { doc ->
                                val senderType = doc.getString("loai_nguoi_gui") ?: "Khách hàng"
                                val content = doc.getString("noi_dung") ?: return@mapNotNull null
                                val timeStr = doc.getString("ngay_tro_chuyen") ?: "10:14"
                                val ts = doc.getLong("timestamp") ?: 0L

                                Pair(
                                    ts,
                                    ChatMessage(
                                        id = doc.id,
                                        sender = if (senderType == "Khách hàng") "user" else "bot",
                                        text = content,
                                        timestamp = timeStr,
                                        isFromGemini = senderType != "Khách hàng"
                                    )
                                )
                            }.sortedBy { it.first }.map { it.second }

                            if (historyMsgs.isNotEmpty()) {
                                messages.clear()
                                messages.addAll(historyMsgs)
                            }
                        }
                    }
            } catch (e: Exception) {
                // Ignore safely
            }
        }
    }

    // Auto-scroll LazyColumn to newest message
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem((messages.size - 1).coerceAtLeast(0))
        }
    }

    fun handleSend(queryText: String) {
        val cleanQuery = queryText.trim()
        if (cleanQuery.isBlank() || isBotLoading) return

        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val currentDateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        messages.add(
            ChatMessage(
                id = "msg_${System.currentTimeMillis()}",
                sender = "user",
                text = cleanQuery,
                timestamp = currentTime
            )
        )

        try {
            val userChatMap = hashMapOf(
                "ma_phien_tro_chuyen" to "session_${System.currentTimeMillis()}",
                "ma_tai_khoan" to currentAccountId,
                "loai_nguoi_gui" to "Khách hàng",
                "noi_dung" to cleanQuery,
                "ngay_tro_chuyen" to currentDateStr,
                "trang_thai" to "Đang hoạt động",
                "timestamp" to System.currentTimeMillis()
            )
            FirebaseFirestore.getInstance().collection("chats").add(userChatMap)
        } catch (e: Exception) {
            // Ignore safely
        }

        inputText = ""
        isBotLoading = true

        val loadingId = "loading_${System.currentTimeMillis()}"
        messages.add(
            ChatMessage(
                id = loadingId,
                sender = "bot",
                text = "Avocado PetBot đang suy nghĩ...",
                timestamp = currentTime
            )
        )

        coroutineScope.launch {
            val responseMsg = GeminiAiService.sendMessage(cleanQuery)
            messages.removeAll { it.id == loadingId }
            messages.add(responseMsg)
            isBotLoading = false

            // Chỉ lưu lên Firestore khi câu trả lời đến từ Gemini AI thành công
            if (responseMsg.isFromGemini) {
                try {
                    val botChatMap = hashMapOf(
                        "ma_phien_tro_chuyen" to "session_${System.currentTimeMillis()}",
                        "ma_tai_khoan" to currentAccountId,
                        "loai_nguoi_gui" to "Trợ lý AI",
                        "noi_dung" to responseMsg.text,
                        "ngay_tro_chuyen" to currentDateStr,
                        "trang_thai" to "Đang hoạt động",
                        "timestamp" to System.currentTimeMillis()
                    )
                    FirebaseFirestore.getInstance().collection("chats").add(botChatMap)
                } catch (e: Exception) {
                    // Ignore safely
                }
            }
        }
    }

    val suggestedQuestions = listOf(
        "🔄 So sánh 2 loại hạt này",
        "🍴 Gợi ý thêm súp thưởng kích thích vị giác",
        "💡 Cách tập bé ăn hạt mới"
    )

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .navigationBarsPadding()
                    .imePadding()
            ) {
                ChatBottomInputBar(
                    inputText = inputText,
                    onInputChange = { inputText = it },
                    onSend = { handleSend(inputText) }
                )
            }
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Top Bar
            AiChatTopBar(
                onBack = onBack,
                onProfileClick = { }
            )

            // Assistant Subheader Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(18.dp))
                    .padding(12.dp)
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
                                .size(40.dp)
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

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Avocado PetBot AI",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryText
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(SuccessBg)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = if (isBotLoading) "⏳ Đang trả lời..." else "🟢 Đang hoạt động",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SuccessText
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Trợ lý ảo mua sắm & dinh dưỡng",
                                fontSize = 11.sp,
                                color = MutedText
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(CardSurface)
                                .border(1.dp, BorderDivider, CircleShape)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "📌", fontSize = 14.sp)
                        }

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(CardSurface)
                                .border(1.dp, BorderDivider, CircleShape)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_filter),
                                contentDescription = null,
                                tint = SecondaryText,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }

            // Time Stamp Divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Hôm nay, 10:14",
                        fontSize = 11.sp,
                        color = MutedText,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Chat Messages Log & Suggested Chips
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(messages) { msg ->
                    ChatBubbleItem(
                        message = msg,
                        onProductDetail = onNavigateToProductDetail,
                        onAddToCart = onAddToCart
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(6.dp))

                    // Suggested Questions
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "💡", fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Gợi ý câu hỏi tiếp theo cho bạn:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SecondaryText
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            suggestedQuestions.forEach { question ->
                                val cleanQ = question.replace("🔄 ", "").replace("🍴 ", "").replace("💡 ", "")
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(CardSurface)
                                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                                        .clickable { handleSend(cleanQ) }
                                        .padding(horizontal = 14.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = question,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = SecondaryText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AiChatTopBar(
    onBack: () -> Unit,
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

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Trò Chuyện Với AI PetBot",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(SecondarySoft)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "🐾 AI",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondarySoftText
                )
            }
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
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "⋮", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
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
private fun ChatBubbleItem(
    message: ChatMessage,
    onProductDetail: (String) -> Unit,
    onAddToCart: (String) -> Unit
) {
    val isUser = message.sender == "user"

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        if (!isUser) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(SuccessBg)
                    .border(1.dp, BorderDivider, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🥑", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(
            horizontalAlignment = if (isUser) Alignment.End else Alignment.Start,
            modifier = Modifier.weight(1f, fill = false)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
            ) {
                if (!isUser) {
                    Text(
                        text = "Avocado PetBot",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryText
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
                Text(
                    text = message.timestamp,
                    fontSize = 10.sp,
                    color = MutedText
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .shadow(3.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = if (isUser) 20.dp else 4.dp,
                            bottomEnd = if (isUser) 4.dp else 20.dp
                        )
                    )
                    .background(if (isUser) PrimaryCTA else CardSurface)
                    .border(
                        1.dp,
                        if (isUser) PrimaryCTA else BorderDivider,
                        RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = if (isUser) 20.dp else 4.dp,
                            bottomEnd = if (isUser) 4.dp else 20.dp
                        )
                    )
                    .padding(14.dp)
            ) {
                Column {
                    Text(
                        text = message.text,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = if (isUser) Color.White else PrimaryText
                    )

                    // Embedded Recommended Products if present
                    message.recommendedProducts?.let { products ->
                        Spacer(modifier = Modifier.height(12.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            products.forEach { product ->
                                ChatProductCard(
                                    product = product,
                                    onDetailClick = { onProductDetail(product.id) },
                                    onAddCartClick = { onAddToCart(product.id) }
                                )
                            }
                        }
                    }

                    // Footnote if present
                    message.footerNote?.let { note ->
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = note,
                            fontSize = 11.sp,
                            color = MutedText
                        )
                    }
                }
            }
        }

        if (isUser) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(34.dp)
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
}

@Composable
private fun ChatProductCard(
    product: RecommendedProduct,
    onDetailClick: () -> Unit,
    onAddCartClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SecondarySurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
            .padding(10.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Product Thumbnail
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🐱", fontSize = 32.sp)

                    product.badge?.let { badge ->
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .clip(RoundedCornerShape(topStart = 12.dp, bottomEnd = 8.dp))
                                .background(PrimaryCTA)
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = badge,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = product.brandTag,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MutedText
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = product.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = product.subtitle,
                        fontSize = 11.sp,
                        color = MutedText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = product.price,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryCTA
                        )

                        product.oldPrice?.let { oldPrice ->
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = oldPrice,
                                fontSize = 11.sp,
                                color = MutedText,
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(SecondarySoft)
                        .clickable { onDetailClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Xem chi tiết",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(36.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(PrimaryCTA)
                        .clickable { onAddCartClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shopping_bag),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Thêm vào giỏ",
                            fontSize = 11.sp,
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
private fun ChatBottomInputBar(
    inputText: String,
    onInputChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SecondarySurface)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🖼", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.width(6.dp))

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SecondarySurface)
                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mic),
                    contentDescription = "Mic",
                    tint = SecondaryText,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(22.dp))
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (inputText.isEmpty()) {
                    Text(
                        text = "Nhập câu hỏi cho PetBot...",
                        fontSize = 13.sp,
                        color = PlaceholderText
                    )
                }

                BasicTextField(
                    value = inputText,
                    onValueChange = onInputChange,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = PrimaryText
                    ),
                    cursorBrush = SolidColor(PrimaryText),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        autoCorrectEnabled = false,
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = { onSend() }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(PrimaryCTA)
                    .clickable { onSend() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "Send",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.petshop.R
import com.example.petshop.data.model.AiRecentChat
import com.example.petshop.data.model.AiTool
import com.example.petshop.data.model.sampleAiTools
import com.example.petshop.data.model.sampleRecentChats
import com.example.petshop.ui.components.HomeTab
import com.example.petshop.ui.components.PetShopBottomNavBar
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
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

@Composable
fun AiAssistantHubScreen(
    onBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToCategory: () -> Unit = {},
    onNavigateToChat: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToNotification: () -> Unit = {}
) {
    var promptQuery by remember { mutableStateOf("") }

    val quickQuestions = listOf(
        "✏ Hạt nào giúp mượt lông & giảm rụng?",
        "🐾 Cát không bụi dịu nhẹ...",
        "🍖 Pate tươi hỗ trợ tăng cân"
    )

    Scaffold(
        bottomBar = {
            PetShopBottomNavBar(
                selectedTab = HomeTab.Assistant,
                onTabSelected = { tab ->
                    when (tab) {
                        is HomeTab.Home -> onNavigateToHome()
                        is HomeTab.Category -> onNavigateToCategory()
                        is HomeTab.Assistant -> { /* Already on Assistant */ }
                        is HomeTab.Profile -> onNavigateToProfile()
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
                .verticalScroll(rememberScrollState())
        ) {
            // Header Bar
            AiHubHeader(
                onBack = onBack,
                onNotificationClick = onNavigateToNotification,
                onProfileClick = onNavigateToProfile
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Hero Card Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(6.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(RoundedCornerShape(24.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                    .padding(16.dp)
            ) {
                Column {
                    // Top Pill row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(CardSurface)
                                .border(1.dp, BorderDivider, RoundedCornerShape(14.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
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
                                    text = "Avocado Pet AI v2.5 Online 24/7",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SuccessText
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(SecondarySoft)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "🐾 Boss Miu (3.5kg)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Mascot avatar & Headline
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(IconPrimaryBg)
                                .border(2.dp, PrimaryCTA, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🐱", fontSize = 42.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Xin chào Sen! Hôm nay Boss Miu muốn ăn gì nào?",
                                fontSize = 17.sp,
                                lineHeight = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Sẵn sàng gợi ý dinh dưỡng, đồ chơi & so sánh ưu đãi tốt nhất cho bé cưng.",
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = SecondaryText
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // AI Prompt Input Search Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .shadow(4.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(RoundedCornerShape(20.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_ai_assistant),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(22.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (promptQuery.isEmpty()) {
                                Text(
                                    text = "Hỏi AI bất kỳ điều gì về khẩu vị, đồ...",
                                    fontSize = 13.sp,
                                    color = PlaceholderText
                                )
                            }
                            BasicTextField(
                                value = promptQuery,
                                onValueChange = { promptQuery = it },
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
                                    onSend = { onNavigateToChat() }
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.ic_mic),
                            contentDescription = "Mic",
                            tint = MutedText,
                            modifier = Modifier
                                .size(22.dp)
                                .clickable { }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(PrimaryCTA)
                                .clickable { onNavigateToChat() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_send),
                                contentDescription = "Send",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🛡 Tư vấn độc quyền dinh dưỡng & thói quen lành mạnh",
                            fontSize = 10.sp,
                            color = MutedText
                        )
                        Text(
                            text = "Trợ lý nhạy bén",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Section 1: Sen thường hỏi AI
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🔴", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Sen thường hỏi AI",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Text(
                    text = "Vuốt ngang để xem thêm",
                    fontSize = 11.sp,
                    color = MutedText
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(quickQuestions) { question ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(CardSurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                            .clickable { onNavigateToChat() }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
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

            Spacer(modifier = Modifier.height(18.dp))

            // Section 2: Trợ lý thông minh độc quyền
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "✪", fontSize = 14.sp, color = PrimaryCTA)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Trợ lý thông minh độc quyền",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Text(
                    text = "4 công cụ",
                    fontSize = 11.sp,
                    color = MutedText
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                sampleAiTools.forEach { tool ->
                    AiToolCard(
                        tool = tool,
                        onClick = onNavigateToChat
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Section 3: Hội thoại gần đây
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⏰", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Hội thoại gần đây",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Text(
                    text = "Xem tất cả",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryCTA,
                    modifier = Modifier.clickable { }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                sampleRecentChats.forEach { recent ->
                    AiRecentChatCard(
                        recent = recent,
                        onClick = onNavigateToChat
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Portion Tip Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(SecondarySurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(18.dp))
                    .padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(CardSurface)
                            .border(1.dp, BorderDivider, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Khẩu phần hôm nay của Boss Miu",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Đã đạt 85% năng lượng tiêu chuẩn, nhớ cho bé uống thêm nước nhé!",
                            fontSize = 11.sp,
                            color = SecondaryText,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Big Start Chat CTA Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(PrimaryCTA)
                        .clickable { onNavigateToChat() },
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sparkles),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Bắt đầu trò chuyện với AI ngay",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Phản hồi siêu tốc trong 0.5 giây • Hoàn toàn miễn phí",
                    fontSize = 11.sp,
                    color = MutedText
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun AiHubHeader(
    onBack: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Circular Back Arrow Button (matching CartScreen & WishlistScreen)
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
                    contentDescription = "Quay lại",
                    tint = PrimaryText,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(PrimaryCTA),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ai_assistant),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "Trợ Lý AI Hub",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(SuccessText)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Trợ lý bơ cưng sẵn sàng",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                }
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
                    .clickable { onNotificationClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = "Notification",
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
private fun AiToolCard(
    tool: AiTool,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(18.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(18.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(18.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val (iconBg, iconTint, iconRes) = when (tool.iconType) {
                "utensils" -> Triple(IconPrimaryBg, PrimaryCTA, R.drawable.ic_utensils)
                "chat" -> Triple(SuccessBg, SuccessText, R.drawable.ic_ai_assistant)
                "compare" -> Triple(WarningBg, WarningText, R.drawable.ic_compare)
                else -> Triple(SecondarySoft, PrimaryCTA, R.drawable.ic_shopping_bag)
            }

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = tool.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )

                    tool.badge?.let { badge ->
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SecondarySoft)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = badge,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = tool.subtitle,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = MutedText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "›",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MutedText
            )
        }
    }
}

@Composable
private fun AiRecentChatCard(
    recent: AiRecentChat,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SecondarySurface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ai_assistant),
                    contentDescription = null,
                    tint = SecondaryText,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recent.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${recent.subtitle} • ${recent.timeAgo}",
                    fontSize = 11.sp,
                    color = MutedText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "↗",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryText
            )
        }
    }
}

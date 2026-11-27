package com.example.petshop.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.components.HomeTab
import com.example.petshop.ui.components.PetShopBottomNavBar
import com.example.petshop.ui.theme.*

@Composable
fun CaNhanScreen(
    onNavigateToEditProfile: () -> Unit,
    onNavigateToAddressBook: () -> Unit,
    onNavigateToChangePassword: () -> Unit,
    onNavigateToPetDetail: () -> Unit,
    onNavigateToPetList: () -> Unit,
    onNavigateToMembership: () -> Unit,
    onNavigateToPetPoints: () -> Unit,
    onNavigateToHelpCenter: () -> Unit,
    onNavigateToNotificationSettings: () -> Unit,
    onNavigateToLanguageAppearance: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToCategory: () -> Unit,
    onNavigateToAssistant: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf<HomeTab>(HomeTab.Profile) }

    Scaffold(
        bottomBar = {
            PetShopBottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { tab ->
                    selectedTab = tab
                    when (tab) {
                        is HomeTab.Home -> onNavigateToHome()
                        is HomeTab.Category -> onNavigateToCategory()
                        is HomeTab.Assistant -> onNavigateToAssistant()
                        else -> {}
                    }
                }
            )
        },
        containerColor = GlobalBackground
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp)
        ) {
            // 1. Top Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(SecondarySoft),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "Cá Nhân",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = "Avocado PetShop",
                            fontSize = 11.sp,
                            color = MutedText
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(CardSurface)
                            .border(1.dp, BorderDivider, CircleShape)
                            .clickable { /* Notifications */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bell),
                            contentDescription = "Notifications",
                            tint = PrimaryText,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(CardSurface)
                            .border(1.dp, BorderDivider, CircleShape)
                            .clickable { onNavigateToEditProfile() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = "Profile",
                            tint = PrimaryCTA,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // 2. User Info Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(RoundedCornerShape(24.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                    .clickable { onNavigateToMembership() }
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(72.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(SecondarySurface)
                                .border(2.dp, PrimaryCTA, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_person),
                                contentDescription = null,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                        // VIP Badge
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .clip(RoundedCornerShape(8.dp))
                                .background(PrimaryCTA)
                                .padding(horizontal = 6.dp, vertical = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "VIP",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnPrimaryText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Nguyễn Thùy Linh",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        // Tag Sen Chu Đáo
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SecondarySoft)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_paw),
                                    contentDescription = null,
                                    tint = SecondarySoftText,
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Sen Chu Đáo",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondarySoftText
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "📞 0987 *** 321",
                            fontSize = 12.sp,
                            color = SecondaryText
                        )
                        Text(
                            text = "✉️ thuylinh.pet@email.com",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                    }

                    // Edit Icon Button
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface)
                            .clickable { onNavigateToEditProfile() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "Edit",
                            tint = PrimaryCTA,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 3. Stats Bar (Pet Points, Vouchers, Wishlist)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Pet Points
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .clickable { onNavigateToPetPoints() }
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_piggy),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "1.250",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Text(
                        text = "Pet Points",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SecondarySoft)
                            .padding(horizontal = 10.dp, vertical = 3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Đổi quà",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }
                }

                // Vouchers
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "8 mã",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Text(
                        text = "Voucher của tôi",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Tiết kiệm 20%",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = SuccessText
                    )
                }

                // Wishlist
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                        .clip(RoundedCornerShape(20.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "16 món",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Text(
                        text = "Yêu thích",
                        fontSize = 11.sp,
                        color = MutedText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Đang giảm giá 3",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = DangerText
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 4. My Orders Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(RoundedCornerShape(24.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shopping_bag),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Đơn hàng của tôi",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Text(
                        text = "Xem tất cả >",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryCTA,
                        modifier = Modifier.clickable { /* All orders */ }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    val orderStatuses = listOf(
                        Triple("Chờ duyệt", R.drawable.ic_ticket, "1"),
                        Triple("Chuẩn bị", R.drawable.ic_store, "2"),
                        Triple("Đang giao", R.drawable.ic_truck, "1"),
                        Triple("Đánh giá", R.drawable.ic_star, "4"),
                        Triple("Trả hàng", R.drawable.ic_cart_add, null)
                    )

                    orderStatuses.forEach { (title, iconRes, badge) ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.clickable { /* Order status */ }
                        ) {
                            Box(modifier = Modifier.size(42.dp)) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .background(SecondarySurface),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = iconRes),
                                        contentDescription = title,
                                        tint = PrimaryCTA,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                if (badge != null) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .size(18.dp)
                                            .clip(CircleShape)
                                            .background(DangerText),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = badge,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = OnPrimaryText
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = title,
                                fontSize = 11.sp,
                                color = SecondaryText
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 5. My Pets Section ("Boss cưng của tôi")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(RoundedCornerShape(24.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Boss cưng của tôi",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SecondarySoft)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "2 bé",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }

                    Text(
                        text = "Quản lý >",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryCTA,
                        modifier = Modifier.clickable { onNavigateToPetList() }
                    )
                }

                // Pet 1: Miu
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                        .clickable { onNavigateToPetDetail() }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(CardSurface),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Miu",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Text(
                                text = "Mèo Anh lông ngắn",
                                fontSize = 12.sp,
                                color = MutedText
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "5 tháng tuổi • 3.5 kg",
                            fontSize = 12.sp,
                            color = SecondaryText
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SuccessBg)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "🎂 Sắp sinh nhật",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    }
                    Text(
                        text = "›",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MutedText
                    )
                }

                // Pet 2: Bơ
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(CardSurface),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Bơ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Text(
                                text = "Chó Poodle",
                                fontSize = 12.sp,
                                color = MutedText
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "1 tuổi • 4.2 kg",
                            fontSize = 12.sp,
                            color = SecondaryText
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(WarningBg)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "🍖 Khẩu phần hạt cừu",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = WarningText
                            )
                        }
                    }
                    Text(
                        text = "›",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MutedText
                    )
                }

                // Add New Pet Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                        .clickable { onNavigateToPetList() },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "+ Thêm hồ sơ Boss cưng mới",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryCTA
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 6. Settings & Utilities Section ("Cài đặt & Tiện ích")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(RoundedCornerShape(24.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Cài đặt & Tiện ích",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                val settingsItems = listOf(
                    Triple("Sổ địa chỉ nhận hàng", "2 địa chỉ đã lưu (Nhà riêng & Cơ quan)", R.drawable.ic_truck),
                    Triple("Đổi mật khẩu & Bảo mật", "Bảo mật vân tay & Mã PIN thanh toán", R.drawable.ic_lock),
                    Triple("Phương thức thanh toán", "Ví MoMo, VietQR, Thẻ Visa (*4829)", R.drawable.ic_wallet),
                    Triple("Thông báo & Ngôn ngữ", "Tiếng Việt • Bật chuông báo khuyến mãi", R.drawable.ic_bell),
                    Triple("Trung tâm trợ giúp", "Hotline 1900 8888 & Chat tư vấn viên", R.drawable.ic_headset)
                )

                settingsItems.forEachIndexed { index, (title, subtitle, iconRes) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .clickable {
                                if (index == 0) {
                                    onNavigateToAddressBook()
                                } else if (index == 1) {
                                    onNavigateToChangePassword()
                                } else if (index == 3) {
                                    onNavigateToLanguageAppearance()
                                } else if (index == 4) {
                                    onNavigateToHelpCenter()
                                }
                            }
                            .padding(vertical = 10.dp, horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(SecondarySurface),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = title,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = subtitle,
                                fontSize = 11.sp,
                                color = MutedText
                            )
                        }

                        Text(
                            text = "›",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MutedText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 7. Logout Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp)
                    .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                    .clip(RoundedCornerShape(16.dp))
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                    .clickable { onLogout() },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_truck),
                        contentDescription = "Logout",
                        tint = DangerText,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Đăng xuất tài khoản",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DangerText
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Footer version
            Text(
                text = "Phiên bản Avocado PetShop v2.4.0 (2025) • Dành riêng cho thú cưng",
                fontSize = 11.sp,
                color = MutedText,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

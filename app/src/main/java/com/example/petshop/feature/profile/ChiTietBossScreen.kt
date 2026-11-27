package com.example.petshop.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.petshop.ui.theme.*

@Composable
fun ChiTietBossScreen(
    onBack: () -> Unit,
    onNavigateToEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardSurface)
                    .border(1.dp, BorderDivider, CircleShape)
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "‹",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            Text(
                text = "Chi Tiết Hồ Sơ Thú Cưng",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryText
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(PrimaryCTA.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "Profile",
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // VIP Pet ID Card Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(6.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(2.dp, PrimaryCTA.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
                .padding(18.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "AVOCADO PETSHOP • Pet ID VIP",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Text(
                        text = "Mã số cưng: AVO-PET-0892",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(76.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(SecondarySurface)
                                .border(2.dp, PrimaryCTA, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_paw),
                                contentDescription = null,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(38.dp)
                            )
                        }
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
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Bé Miu",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Text(
                                text = "♀",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryCTA
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Biệt danh: Miu Miu Béo Ú",
                            fontSize = 12.sp,
                            color = SecondaryText
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SecondarySoft)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "🐰 Mèo Anh lông ngắn",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondarySurface)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Cân nặng", fontSize = 11.sp, color = MutedText)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "3.5 kg", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryText)
                        Text(text = "Đã triệt sản", fontSize = 10.sp, color = SuccessText)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Độ tuổi", fontSize = 11.sp, color = MutedText)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "5 tháng", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryText)
                        Text(text = "Đang lớn nhanh", fontSize = 10.sp, color = PrimaryCTA)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Sinh nhật", fontSize = 11.sp, color = MutedText)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "15/09", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = PrimaryText)
                        Text(text = "Xử Nữ ♍", fontSize = 10.sp, color = SecondaryText)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Thú Cưng Kim Cương",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shield_check),
                            contentDescription = null,
                            tint = SuccessText,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Boss Ưu Tiên",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Action Buttons Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { onNavigateToEdit() },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SecondarySoft)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = null,
                        tint = SecondarySoftText,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Chỉnh sửa hồ sơ",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }

            Button(
                onClick = { /* Share Pet ID */ },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryCTA)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ticket),
                        contentDescription = null,
                        tint = OnPrimaryText,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Khoe thẻ Pet ID",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Priority Boss Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SuccessBg)
                .border(1.dp, SuccessText.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = SuccessText,
                    modifier = Modifier.size(20.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Boss ưu tiên hiện tại",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Text(
                        text = "Tự động áp dụng ưu đãi cho bé Miu tại Trang chủ",
                        fontSize = 12.sp,
                        color = SuccessText
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_shield_check),
                    contentDescription = null,
                    tint = SuccessText,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nutrition Plan Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height(16.dp)
                            .background(PrimaryCTA, RoundedCornerShape(2.dp))
                    )
                    Text(
                        text = "Chế độ dinh dưỡng mỗi ngày",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SecondarySoft)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "Khuyến nghị riêng",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }

            // Nutrition item 1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondarySurface)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(CardSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Khẩu phần hạt khô",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = "65g – 75g",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryCTA
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Chia đều 3 bữa mỗi ngày (Sáng, Trưa, Tối) giúp duy trì hệ tiêu hóa ổn định cho mèo con đang lớn.",
                        fontSize = 11.sp,
                        color = SecondaryText,
                        lineHeight = 15.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(BorderDivider)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.66f)
                                .fillMaxHeight()
                                .background(PrimaryCTA, RoundedCornerShape(3.dp))
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Bữa 2/3 hoàn tất",
                            fontSize = 10.sp,
                            color = MutedText
                        )
                    }
                }
            }

            // Nutrition item 2
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondarySurface)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(CardSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_leaf),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lượng nước mỗi ngày",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Text(
                            text = "180ml – 220ml",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryCTA
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Bé Miu thích uống nước từ đài phun nước chảy tuần hoàn mát lạnh.",
                        fontSize = 11.sp,
                        color = SecondaryText
                    )
                }
            }

            // Nutrition item 3
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(SecondarySurface)
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(CardSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lightning),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Mức độ tiêu hao năng lượng",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SuccessBg)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Năng động vừa",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Thích trò chơi săn mồi tương tác bằng cần câu lông vũ 20 phút trước khi ngủ.",
                        fontSize = 11.sp,
                        color = SecondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pet Preferences Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(16.dp)
                        .background(PrimaryCTA, RoundedCornerShape(2.dp))
                )
                Text(
                    text = "Sở thích của bé Miu",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "🍽 Khẩu vị mê mẩn", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Text(text = "♥ Pate cá hồi Monge", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Text(text = "♥ Hạt Orijen Kitten gà", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Text(text = "♥ Súp thưởng Ciao nắp vặn", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "🧸 Đồ chơi khoái khẩu", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Text(text = "⚽ Cần câu lông vũ tự nhiên", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Text(text = "⚽ Chuột bông cỏ catnip", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(SecondarySurface).border(1.dp, BorderDivider, RoundedCornerShape(12.dp)).padding(horizontal = 10.dp, vertical = 6.dp)) {
                        Text(text = "⚽ Trụ cào móng dây thừng", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }
                }
            }
        }
    }
}

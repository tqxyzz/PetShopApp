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
fun SoDiaChiScreen(
    onBack: () -> Unit,
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
                text = "Sổ Địa Chỉ",
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

        Spacer(modifier = Modifier.height(8.dp))

        // GPS Smart Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(SuccessBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_truck),
                        contentDescription = null,
                        tint = SuccessText,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Định vị thông minh GPS",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SuccessBg)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Hỏa tốc 2H",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Cập nhật tọa độ chính xác để shipper giao hạt, pate và cát vệ sinh tận cửa không lo lạc đường!",
                        fontSize = 12.sp,
                        color = SecondaryText
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondarySurface)
                            .border(1.dp, BorderDivider, RoundedCornerShape(12.dp))
                            .clickable { /* Turn on GPS */ }
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_lightning),
                                contentDescription = null,
                                tint = PrimaryCTA,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Bật GPS ngay",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryCTA
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add New Address Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(52.dp)
                .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySurface)
                .border(1.dp, PrimaryCTA.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                .clickable { /* Add new address */ },
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(PrimaryCTA),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnPrimaryText
                    )
                }
                Text(
                    text = "Thêm địa chỉ nhận hàng mới",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryCTA
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Section Title: Địa chỉ đã lưu
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Địa chỉ đã lưu",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(SecondarySoft)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "2 địa chỉ",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = null,
                    tint = PrimaryCTA,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "Giao yêu thương",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryCTA
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Address Card 1 (Default Address)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, PrimaryCTA.copy(alpha = 0.4f), RoundedCornerShape(24.dp))
                .padding(18.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(DangerBg)
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_shield_check),
                                    contentDescription = null,
                                    tint = DangerText,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Mặc định",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DangerText
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(SecondarySurface)
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_store),
                                    contentDescription = null,
                                    tint = SecondaryText,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Nhà riêng",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryText
                                )
                            }
                        }
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_check),
                        contentDescription = "Verified",
                        tint = SuccessText,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Nguyễn Thùy Linh",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Text(text = "•", fontSize = 14.sp, color = MutedText)
                    Text(
                        text = "(+84) 987 654 321",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = SecondaryText
                    )
                }

                Text(
                    text = "Căn hộ A12.08, Tòa Landmark 3, Vinhomes Central Park, 208 Nguyễn Hữu Cảnh, Phường 22, Quận Bình Thạnh, TP. Hồ Chí Minh",
                    fontSize = 13.sp,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SecondarySurface)
                        .padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_paw),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "\"Giao tận cửa, gọi trước khi đến 15 phút, nhà có nuôi mèo\"",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_truck),
                            contentDescription = null,
                            tint = SuccessText,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Tuyến giao ưu tiên",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondarySurface)
                                .clickable { /* Edit */ }
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = null,
                                    tint = SecondaryText,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = "Chỉnh sửa",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryText
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(DangerBg)
                                .clickable { /* Delete */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cart_add),
                                contentDescription = "Delete",
                                tint = DangerText,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Address Card 2 (Office Address)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(24.dp))
                .background(CardSurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
                .padding(18.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SecondarySoft)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_store),
                                contentDescription = null,
                                tint = SecondarySoftText,
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Văn phòng / Công ty",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondarySoftText
                            )
                        }
                    }

                    Text(
                        text = "Địa chỉ phụ",
                        fontSize = 12.sp,
                        color = MutedText,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Nguyễn Thùy Linh",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Text(text = "•", fontSize = 14.sp, color = MutedText)
                    Text(
                        text = "(+84) 912 345 678",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = SecondaryText
                    )
                }

                Text(
                    text = "Tầng 6, Tòa nhà Bitexco Financial Tower, Số 2 Hải Triều, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh",
                    fontSize = 13.sp,
                    color = SecondaryText,
                    lineHeight = 18.sp
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(SecondarySurface)
                        .padding(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_headset),
                            contentDescription = null,
                            tint = MutedText,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "\"Giao trong giờ hành chính từ thứ 2 đến thứ 6\"",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = SecondaryText
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.clickable { /* Set default */ }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .border(2.dp, MutedText, CircleShape)
                        )
                        Text(
                            text = "Đặt làm mặc định",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SecondaryText
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondarySurface)
                                .clickable { /* Edit */ }
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = null,
                                    tint = SecondaryText,
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = "Chỉnh sửa",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondaryText
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(DangerBg)
                                .clickable { /* Delete */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cart_add),
                                contentDescription = "Delete",
                                tint = DangerText,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Security Notice Banner at bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .shadow(2.dp, RoundedCornerShape(20.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
                .clip(RoundedCornerShape(20.dp))
                .background(SecondarySurface)
                .border(1.dp, BorderDivider, RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(CardSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Bảo mật thông tin thú cưng",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Số điện thoại của bạn luôn được ẩn danh 2 chiều với nhân viên giao vận Avocado Pet.",
                        fontSize = 11.sp,
                        color = MutedText,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

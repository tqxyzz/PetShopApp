package com.example.petshop.feature.checkout

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
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
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShippingAddressScreen(
    onBack: () -> Unit = {},
    onContinueToPayment: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {}
) {
    var noteInput by remember { mutableStateOf("") }
    var selectedShippingOption by remember { mutableStateOf(0) }
    var isBioWrapChecked by remember { mutableStateOf(true) }
    var isCoCheckChecked by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            // Fixed Bottom Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(12.dp)
                    .background(CardSurface)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Phí vận chuyển: ", fontSize = 12.sp, color = SecondaryText)
                            Text(
                                text = "10.000đ",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = AdminMaroon
                            )
                        }
                        Text(
                            text = "💸 Tiết kiệm 25.000đ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }

                    // Continue Button
                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(PrimaryCTA)
                            .clickable { onContinueToPayment() }
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tiếp tục đến thanh toán ➔",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
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

                Text(
                    text = "Dia Chi Va Van Chuyen",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "?", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = SecondaryText)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(AdminMaroon)
                            .clickable { onNavigateToProfile() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = "Tài khoản",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // Pet Waiting Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(SecondarySurface)
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_paw),
                        contentDescription = null,
                        tint = SecondarySoftText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Đơn hàng chuẩn bị cho Bé Bơ & Miu Miu đang đợi bạn hoàn tất giao nhận!",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SecondarySoftText,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // SECTION: Địa chỉ nhận hàng
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Địa chỉ nhận hàng",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }
                    Text(
                        text = "Thay đổi >",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Address Box Card
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
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Nguyễn Minh Châu • 0908 123 456",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )

                            Row {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color(0xFFFEE2E2))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Mặc định",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DangerText
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(SuccessBg)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Nhà riêng",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SuccessText
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Căn hộ 12.04, Tháp B, Chung cư Masteri Thảo Điền, 159 Xa Lộ Hà Nội, Phường Thảo Điền, Thành phố Thủ Đức, TP. Hồ Chí Minh",
                            fontSize = 12.sp,
                            color = SecondaryText,
                            lineHeight = 17.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_shield_check),
                                    contentDescription = null,
                                    tint = SuccessText,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Địa chỉ thân thiện với thú cưng",
                                    fontSize = 11.sp,
                                    color = MutedText
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(SecondarySurface)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "+ Thêm mới",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SecondarySoftText
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // SECTION: Ghi chú cho shipper & Boss cưng
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🐰", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Ghi chú cho shipper & Boss cưng",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(68.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    if (noteInput.isEmpty()) {
                        Text(
                            text = "Ví dụ: Nhà có cún nhỏ hay sủa vui lòng gọi trước, không bấm chuông gắt, để gói hàng trước cửa...",
                            fontSize = 12.sp,
                            color = PlaceholderText,
                            lineHeight = 16.sp
                        )
                    }
                    BasicTextField(
                        value = noteInput,
                        onValueChange = { noteInput = it },
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = PrimaryText
                        ),
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Quick Chips
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    NoteChip(text = "🐾 Không bấm chuông", onClick = { noteInput += " Không bấm chuông." })
                    NoteChip(text = "⏰ Giờ hành chính", onClick = { noteInput += " Giao giờ hành chính." })
                    NoteChip(text = "🏢 Để ở quầy lễ tân", onClick = { noteInput += " Để ở quầy lễ tân." })
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // SECTION: Phương thức vận chuyển
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🚚", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Phương thức vận chuyển",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SuccessBg)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "Đã áp mã Freeship",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Shipping Option 1: Hỏa tốc 2h (Selected)
                ShippingOptionCard(
                    title = "Hỏa tốc 2 Giờ (Avocado Express) 🚀",
                    price = "10.000đ",
                    oldPrice = "35.000đ",
                    deliveryTime = "⏰ Giao trước 16:30 chiều nay",
                    details = "🍃 Thức ăn tươi mát, đóng thùng carton chống sốc bảo vệ hạt cưng.",
                    highlightTag = "⚡ Ưu tiên bảo quản đông lạnh & pate tươi",
                    isSelected = selectedShippingOption == 0,
                    onClick = { selectedShippingOption = 0 }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Shipping Option 2: Giao hàng nhanh
                ShippingOptionCard(
                    title = "Giao hàng Nhanh (Tiêu chuẩn) ⚡",
                    price = "Miễn phí 0đ",
                    oldPrice = "25.000đ",
                    deliveryTime = "📅 Dự kiến: Ngày mai (10:00 – 18:00)",
                    details = "Phù hợp hạt khô, snack thưởng và đồ chơi không cần gấp.",
                    isSelected = selectedShippingOption == 1,
                    onClick = { selectedShippingOption = 1 }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Shipping Option 3: Giao tiết kiệm
                ShippingOptionCard(
                    title = "Giao tiết kiệm & Hàng kềnh 📦",
                    price = "Miễn phí 0đ",
                    oldPrice = "18.000đ",
                    deliveryTime = "📅 Dự kiến: 2 – 3 ngày làm việc",
                    details = "Tối ưu cho cát vệ sinh bao lớn 10kg, chuồng quây, nệm bơ và cào móng.",
                    isSelected = selectedShippingOption == 2,
                    onClick = { selectedShippingOption = 2 }
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // SECTION: Dịch vụ đóng gói & Cam kết
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "🎁", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Dịch vụ đóng gói & Cam kết",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardSurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(16.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        // Service 1
                        Row(verticalAlignment = Alignment.Top) {
                            Checkbox(
                                checked = isBioWrapChecked,
                                onCheckedChange = { isBioWrapChecked = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = AdminMaroon,
                                    uncheckedColor = BorderDivider
                                )
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Đóng gói bọc xốp sinh học & Thiệp mừng Boss cưng",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = "+5.000đ",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DangerText
                                    )
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Hạt xốp bột ngô tự phân hủy, an toàn nếu thú cưng nghịch cắn; kèm thông điệp chúc ăn ngon miệng in tên bé.",
                                    fontSize = 11.sp,
                                    color = MutedText,
                                    lineHeight = 15.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Service 2
                        Row(verticalAlignment = Alignment.Top) {
                            Checkbox(
                                checked = isCoCheckChecked,
                                onCheckedChange = { isCoCheckChecked = it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = AdminMaroon,
                                    uncheckedColor = BorderDivider
                                )
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Đồng kiểm hàng an tâm khi nhận",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryText
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(SuccessBg)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "Miễn phí",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = SuccessText
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Mở hộp kiểm tra tem niêm phong, hạn dùng thức ăn và tình trạng hạt cùng shipper trước khi nhận.",
                                    fontSize = 11.sp,
                                    color = MutedText,
                                    lineHeight = 15.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Care Assurance Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(SecondarySurface)
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shield_check),
                            contentDescription = null,
                            tint = SuccessText,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Cam kết an toàn Avocado Care",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "100% thức ăn nhập khẩu chính ngạch, đền gấp 3 nếu phát hiện cận date dưới 6 tháng.",
                                fontSize = 11.sp,
                                color = SecondaryText,
                                lineHeight = 15.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun NoteChip(text: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SecondarySurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = SecondarySoftText
        )
    }
}

@Composable
fun ShippingOptionCard(
    title: String,
    price: String,
    oldPrice: String,
    deliveryTime: String,
    details: String,
    highlightTag: String? = null,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface)
            .border(
                1.dp,
                if (isSelected) AdminMaroon else BorderDivider,
                RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = AdminMaroon,
                    unselectedColor = BorderDivider
                )
            )

            Spacer(modifier = Modifier.width(6.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText,
                        modifier = Modifier.weight(1f)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = price,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = DangerText
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = oldPrice,
                            fontSize = 10.sp,
                            color = MutedText,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = deliveryTime,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = SecondaryText
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = details,
                    fontSize = 11.sp,
                    color = MutedText,
                    lineHeight = 15.sp
                )

                if (highlightTag != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SecondarySurface)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = highlightTag,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }
                }
            }
        }
    }
}

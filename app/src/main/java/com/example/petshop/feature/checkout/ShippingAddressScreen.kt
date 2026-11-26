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
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petshop.R
import com.example.petshop.feature.cart.CartViewModel
import com.example.petshop.ui.theme.AdminMaroon
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.DangerText
import com.example.petshop.ui.theme.GlobalBackground
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PlaceholderText
import com.example.petshop.ui.theme.PrimaryCTA
import com.example.petshop.ui.theme.PrimaryText
import com.example.petshop.ui.theme.SecondarySoftText
import com.example.petshop.ui.theme.SecondarySurface
import com.example.petshop.ui.theme.SecondaryText
import com.example.petshop.ui.theme.SuccessBg
import com.example.petshop.ui.theme.SuccessText

data class SavedAddress(
    val id: String,
    val type: String,
    val name: String,
    val phone: String,
    val address: String,
    val isDefault: Boolean = false
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShippingAddressScreen(
    onBack: () -> Unit = {},
    onContinueToPayment: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    viewModel: CheckoutViewModel = viewModel()
) {
    val presetAddresses = listOf(
        SavedAddress(
            id = "addr_1",
            type = "Nhà riêng",
            name = viewModel.recipientName,
            phone = viewModel.recipientPhone,
            address = viewModel.fullAddress,
            isDefault = true
        ),
        SavedAddress(
            id = "addr_2",
            type = "Công ty",
            name = viewModel.recipientName,
            phone = viewModel.recipientPhone,
            address = "Tòa nhà Deutsches Haus, 33 Lê Duẩn, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh"
        )
    )

    var selectedAddressId by remember { mutableStateOf("addr_1") }
    var isCustomEdit by remember { mutableStateOf(false) }

    var editName by remember(viewModel.recipientName) { mutableStateOf(viewModel.recipientName) }
    var editPhone by remember(viewModel.recipientPhone) { mutableStateOf(viewModel.recipientPhone) }
    var editAddress by remember(viewModel.fullAddress) { mutableStateOf(viewModel.fullAddress) }

    var isBioWrapChecked by remember { mutableStateOf(true) }
    var isCoCheckChecked by remember { mutableStateOf(true) }

    val shippingFee = viewModel.calculateShippingFee()
    val shippingFeeText = if (shippingFee == 0) "Miễn phí 0đ" else CartViewModel.formatPrice(shippingFee)

    Scaffold(
        bottomBar = {
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
                            Text(text = "Phí giao hàng: ", fontSize = 12.sp, color = SecondaryText)
                            Text(
                                text = shippingFeeText,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = AdminMaroon
                            )
                        }
                        Text(
                            text = "💸 Giảm 25.000đ Freeship",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessText
                        )
                    }

                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(PrimaryCTA)
                            .clickable {
                                if (isCustomEdit) {
                                    viewModel.updateAddress(editName, editPhone, editAddress)
                                } else {
                                    val selected = presetAddresses.firstOrNull { it.id == selectedAddressId }
                                    if (selected != null) {
                                        viewModel.updateAddress(selected.name, selected.phone, selected.address, selected.type)
                                    }
                                }
                                onContinueToPayment()
                            }
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tiếp tục thanh toán ➔",
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
                    text = "Địa Chỉ & Vận Chuyển",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

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

            Spacer(modifier = Modifier.height(8.dp))

            // SECTION 1: Chọn Địa Chỉ
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
                        text = if (isCustomEdit) "← Chọn có sẵn" else "+ Nhập mới",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondarySoftText,
                        modifier = Modifier.clickable { isCustomEdit = !isCustomEdit }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (isCustomEdit) {
                    // Custom Input Form
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
                            .clip(RoundedCornerShape(16.dp))
                            .background(CardSurface)
                            .border(1.dp, AdminMaroon, RoundedCornerShape(16.dp))
                            .padding(14.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = "Nhập thông tin người nhận:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                            OutlinedTextField(
                                value = editName,
                                onValueChange = { editName = it },
                                label = { Text("Tên người nhận", fontSize = 12.sp) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = editPhone,
                                onValueChange = { editPhone = it },
                                label = { Text("Số điện thoại", fontSize = 12.sp) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = editAddress,
                                onValueChange = { editAddress = it },
                                label = { Text("Địa chỉ giao hàng", fontSize = 12.sp) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                } else {
                    // List Preset Addresses Option Cards
                    presetAddresses.forEach { item ->
                        val isSelected = item.id == selectedAddressId
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .shadow(2.dp, RoundedCornerShape(16.dp), ambientColor = BorderDivider)
                                .clip(RoundedCornerShape(16.dp))
                                .background(CardSurface)
                                .border(
                                    1.dp,
                                    if (isSelected) AdminMaroon else BorderDivider,
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable {
                                    selectedAddressId = item.id
                                    viewModel.updateAddress(item.name, item.phone, item.address, item.type)
                                }
                                .padding(12.dp)
                        ) {
                            Row(verticalAlignment = Alignment.Top) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = {
                                        selectedAddressId = item.id
                                        viewModel.updateAddress(item.name, item.phone, item.address, item.type)
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = AdminMaroon,
                                        unselectedColor = BorderDivider
                                    )
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "${item.name} • ${item.phone}",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = PrimaryText
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(SuccessBg)
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = item.type,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = SuccessText
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = item.address,
                                        fontSize = 11.sp,
                                        color = SecondaryText,
                                        lineHeight = 15.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // SECTION 2: Phương Thức Vận Chuyển
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "🚚 Phương thức giao hàng",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Option 1: Hỏa tốc 2H
                CompactShippingCard(
                    title = "Hỏa tốc 2 Giờ (Avocado Express) 🚀",
                    priceText = "10.000đ",
                    timeText = "⏰ Nhận trước 16:30 chiều nay",
                    isSelected = viewModel.selectedShippingOptionIndex == 0,
                    onClick = { viewModel.selectedShippingOptionIndex = 0 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Option 2: Nhanh tiêu chuẩn
                CompactShippingCard(
                    title = "Giao hàng Nhanh (Tiêu chuẩn) ⚡",
                    priceText = "Miễn phí",
                    timeText = "📅 Dự kiến ngày mai",
                    isSelected = viewModel.selectedShippingOptionIndex == 1,
                    onClick = { viewModel.selectedShippingOptionIndex = 1 }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Option 3: Tiết kiệm
                CompactShippingCard(
                    title = "Giao tiết kiệm & Hàng kềnh 📦",
                    priceText = "Miễn phí",
                    timeText = "📅 Dự kiến 2 - 3 ngày",
                    isSelected = viewModel.selectedShippingOptionIndex == 2,
                    onClick = { viewModel.selectedShippingOptionIndex = 2 }
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // SECTION 3: Ghi Chú & Dịch Vụ
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "📝 Ghi chú cho shipper & Quà tặng",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryText
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(SecondarySurface)
                        .border(1.dp, BorderDivider, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (viewModel.shipperNote.isEmpty()) {
                        Text(
                            text = "Ghi chú giao hàng (ví dụ: gọi trước khi giao, để ở lễ tân...)",
                            fontSize = 12.sp,
                            color = PlaceholderText
                        )
                    }
                    BasicTextField(
                        value = viewModel.shipperNote,
                        onValueChange = { viewModel.shipperNote = it },
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = PrimaryText
                        ),
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    CompactChip(text = "🐾 Không bấm chuông", onClick = { viewModel.shipperNote += " Không bấm chuông." })
                    CompactChip(text = "⏰ Giờ hành chính", onClick = { viewModel.shipperNote += " Giao giờ hành chính." })
                    CompactChip(text = "🏢 Để ở lễ tân", onClick = { viewModel.shipperNote += " Để ở lễ tân." })
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Services compact checkboxes
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isBioWrapChecked,
                        onCheckedChange = { isBioWrapChecked = it },
                        colors = CheckboxDefaults.colors(checkedColor = AdminMaroon)
                    )
                    Text(
                        text = "🎁 Bọc xốp sinh học & thiệp mừng Boss (+5k)",
                        fontSize = 12.sp,
                        color = PrimaryText,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isCoCheckChecked,
                        onCheckedChange = { isCoCheckChecked = it },
                        colors = CheckboxDefaults.colors(checkedColor = AdminMaroon)
                    )
                    Text(
                        text = "🔍 Đồng kiểm hàng khi nhận (Miễn phí)",
                        fontSize = 12.sp,
                        color = PrimaryText,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun CompactShippingCard(
    title: String,
    priceText: String,
    timeText: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(14.dp), ambientColor = BorderDivider)
            .clip(RoundedCornerShape(14.dp))
            .background(CardSurface)
            .border(
                1.dp,
                if (isSelected) AdminMaroon else BorderDivider,
                RoundedCornerShape(14.dp)
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
                        color = PrimaryText
                    )
                    Text(
                        text = priceText,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DangerText
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = timeText,
                    fontSize = 11.sp,
                    color = MutedText
                )
            }
        }
    }
}

@Composable
fun CompactChip(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(SecondarySurface)
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = SecondarySoftText
        )
    }
}

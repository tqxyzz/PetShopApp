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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
fun NotificationSettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var masterToggle by remember { mutableStateOf(true) }
    var orderStatus by remember { mutableStateOf(true) }
    var deliverySuccess by remember { mutableStateOf(true) }
    var flashSale by remember { mutableStateOf(true) }
    var bossOffers by remember { mutableStateOf(true) }
    var pointsWallet by remember { mutableStateOf(true) }
    var aiNutrition by remember { mutableStateOf(true) }
    var petBot by remember { mutableStateOf(true) }
    var pushNotif by remember { mutableStateOf(true) }
    var smsNotif by remember { mutableStateOf(true) }
    var emailNotif by remember { mutableStateOf(false) }
    var doNotDisturb by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GlobalBackground)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
                text = "Cài Đặt Thông Báo",
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
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = "Notification",
                    tint = PrimaryCTA,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Master Toggle Card ("Bật tất cả thông báo")
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(SecondarySoft),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bell),
                        contentDescription = null,
                        tint = PrimaryCTA,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Bật tất cả thông báo",
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
                                text = "Đang bật",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SuccessText
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Nhận cập nhật về đơn hàng của Boss, ưu đãi sinh nhật và tin nhắn tư vấn AI kịp thời nhất.",
                        fontSize = 11.sp,
                        color = MutedText,
                        lineHeight = 15.sp
                    )
                }

                Switch(
                    checked = masterToggle,
                    onCheckedChange = { masterToggle = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnPrimaryText,
                        checkedTrackColor = PrimaryCTA,
                        uncheckedThumbColor = OnPrimaryText,
                        uncheckedTrackColor = BorderDivider
                    )
                )
            }
        }

        // Section: Đơn hàng & Giao vận
        NotificationSection(
            title = "📦 Đơn hàng & Giao vận",
            items = listOf(
                NotificationToggleItem("Cập nhật trạng thái đơn hàng", "Đã xác nhận, Đang chuẩn bị pate hạt, Shipper đang giao hỏa tốc đến tận tay Sen.", orderStatus) { orderStatus = it },
                NotificationToggleItem("Thông báo giao hàng thành công", "Kèm nhắc nhở đánh giá chất lượng món ăn & nhận ngay xu tích lũy Pet Points.", deliverySuccess) { deliverySuccess = it }
            )
        )

        // Section: Ưu đãi & Điểm thưởng Boss Cưng
        NotificationSection(
            title = "🎁 Ưu đãi & Điểm thưởng Boss Cưng",
            items = listOf(
                NotificationToggleItem("Flash Sale & Giảm giá chớp nhoáng", "Nhắc nhở kịp thời giờ vàng săn hạt thơm ngon & pate giá sốc mỗi tuần.", flashSale) { flashSale = it },
                NotificationToggleItem("Ưu đãi độc quyền theo hồ sơ Boss", "Voucher sinh nhật bé Miu, giảm giá thức ăn chuẩn khẩu vị riêng của Boss cưng.", bossOffers) { bossOffers = it },
                NotificationToggleItem("Biến động ví Pet Points", "Nhắc điểm danh nhận xu cưng mỗi ngày và cảnh báo khi Pet Points sắp hết hạn.", pointsWallet) { pointsWallet = it }
            )
        )

        // Section: Trợ lý AI & Chăm sóc sinh hoạt
        NotificationSection(
            title = "✨ Trợ lý AI & Chăm sóc sinh hoạt",
            items = listOf(
                NotificationToggleItem("Lời khuyên dinh dưỡng định kỳ từ AI", "Nhắc bổ sung nước mùa nóng, điều chỉnh khẩu phần hạt khi Boss tăng cân.", aiNutrition) { aiNutrition = it },
                NotificationToggleItem("Tin nhắn từ Avocado PetBot", "Phản hồi tự động giải đáp thắc mắc về kích thước phụ kiện và vị pate ưa thích.", petBot) { petBot = it }
            )
        )

        // Section: Kênh nhận thông báo
        NotificationSection(
            title = "📡 Kênh nhận thông báo",
            items = listOf(
                NotificationToggleItem("Thông báo đẩy ứng dụng", "Push Notifications trên màn hình khóa", pushNotif) { pushNotif = it },
                NotificationToggleItem("Tin nhắn SMS", "Chỉ nhận mã OTP bảo mật & Shipper gọi", smsNotif) { smsNotif = it },
                NotificationToggleItem("Email bản tin & Hóa đơn", "Bản tin tháng & hóa đơn điện tử VAT", emailNotif) { emailNotif = it }
            )
        )

        // Do Not Disturb Card ("Không làm phiền ban đêm")
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(SecondarySurface),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_lock),
                            contentDescription = null,
                            tint = PrimaryCTA,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Không làm phiền ban đêm",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryText
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "22:00 – 07:00 sáng hôm sau",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondarySoftText
                        )
                    }

                    Switch(
                        checked = doNotDisturb,
                        onCheckedChange = { doNotDisturb = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = OnPrimaryText,
                            checkedTrackColor = PrimaryCTA,
                            uncheckedThumbColor = OnPrimaryText,
                            uncheckedTrackColor = BorderDivider
                        )
                    )
                }

                Text(
                    text = "Tự động tắt tiếng chuông và rung cho các thông báo ưu đãi để không làm giật mình giấc ngủ của Sen và Boss cưng. Các đơn hàng khẩn cấp vẫn duy trì bình thường.",
                    fontSize = 11.sp,
                    color = MutedText,
                    lineHeight = 16.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tùy chỉnh khung giờ vàng nghỉ ngơi",
                        fontSize = 11.sp,
                        color = SecondaryText,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "Chỉnh sửa >",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryCTA,
                        modifier = Modifier.clickable { /* Edit time */ }
                    )
                }
            }
        }

        // Footer System Notice
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shield_check),
                contentDescription = null,
                tint = MutedText,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "Bạn cũng có thể thay đổi hoặc thu hồi quyền thông báo cấp hệ thống trong Cài đặt hệ điều hành Android của thiết bị bất kỳ lúc nào.",
                fontSize = 11.sp,
                color = MutedText,
                lineHeight = 15.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun NotificationSection(
    title: String,
    items: List<NotificationToggleData>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .shadow(4.dp, RoundedCornerShape(24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(24.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(24.dp))
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryText
        )

        items.forEach { item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryText
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.subtitle,
                        fontSize = 11.sp,
                        color = MutedText,
                        lineHeight = 15.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Switch(
                    checked = item.checked,
                    onCheckedChange = item.onCheckedChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = OnPrimaryText,
                        checkedTrackColor = PrimaryCTA,
                        uncheckedThumbColor = OnPrimaryText,
                        uncheckedTrackColor = BorderDivider
                    )
                )
            }
        }
    }
}

data class NotificationToggleData(
    val title: String,
    val subtitle: String,
    val checked: Boolean,
    val onCheckedChange: (Boolean) -> Unit
)

fun NotificationToggleItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) = NotificationToggleData(title, subtitle, checked, onCheckedChange)

package com.example.avocadopetshop.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avocadopetshop.ui.data.PetProduct
import com.example.avocadopetshop.ui.data.formatVnd
import com.example.avocadopetshop.ui.theme.AvocadoColors
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun AvocadoPage(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    headerAction: String? = "♙",
    headerActionIcon: Int? = null,
    onHeaderAction: (() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().background(AvocadoColors.GlobalBackground)
    ) {
        AvocadoTopBar(
            title = title,
            onBack = onBack,
            actionLabel = headerAction,
            actionIcon = headerActionIcon,
            onAction = onHeaderAction
        )
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = content
        )
        if (bottomBar != null) bottomBar()
    }
}

@Composable
fun AvocadoTopBar(
    title: String,
    onBack: () -> Unit,
    actionLabel: String? = "♙",
    actionIcon: Int? = null,
    onAction: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(AvocadoColors.GlobalBackground).padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(onClick = onBack, shape = CircleShape, color = AvocadoColors.SecondarySurface) {
            Box(Modifier.size(40.dp), contentAlignment = Alignment.Center) {
                Text("‹", color = AvocadoColors.PrimaryText, fontSize = 30.sp, lineHeight = 30.sp)
            }
        }
        Text(
            text = title,
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp),
            color = AvocadoColors.PrimaryText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (actionIcon != null) {
            Surface(onClick = { onAction?.invoke() }, shape = CircleShape, color = Color.Transparent) {
                Image(
                    painter = painterResource(id = actionIcon),
                    contentDescription = "Header Icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(38.dp).clip(CircleShape)
                )
            }
        } else if (actionLabel != null) {
            Surface(onClick = { onAction?.invoke() }, shape = CircleShape, color = AvocadoColors.Maroon) {
                Box(Modifier.size(38.dp), contentAlignment = Alignment.Center) {
                    Text(actionLabel, color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun AppCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = AvocadoColors.CardSurface),
        border = BorderStroke(0.7.dp, AvocadoColors.Border),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(9.dp), content = content)
    }
}

@Composable
fun SectionHeading(title: String, trailing: String? = null, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, modifier = Modifier.weight(1f), color = AvocadoColors.PrimaryText, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        if (trailing != null) Text(trailing, color = AvocadoColors.Maroon, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().defaultMinSize(minHeight = 48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = AvocadoColors.Primary, contentColor = Color.White, disabledContainerColor = AvocadoColors.PrimarySoft, disabledContentColor = AvocadoColors.MutedText)
    ) {
        Text(text, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Composable
fun MaroonButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().defaultMinSize(minHeight = 48.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = AvocadoColors.Maroon, contentColor = Color.White)
    ) { Text(text, fontWeight = FontWeight.Bold, fontSize = 14.sp) }
}

@Composable
fun SecondaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().defaultMinSize(minHeight = 44.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = AvocadoColors.PrimarySoft, contentColor = AvocadoColors.Maroon)
    ) { Text(text, fontWeight = FontWeight.SemiBold, fontSize = 13.sp) }
}

@Composable
fun StatusBadge(text: String, type: BadgeType = BadgeType.NEUTRAL) {
    val (bg, fg) = when (type) {
        BadgeType.SUCCESS -> AvocadoColors.SuccessBackground to AvocadoColors.Success
        BadgeType.WARNING -> AvocadoColors.WarningBackground to AvocadoColors.Warning
        BadgeType.DANGER -> AvocadoColors.DangerBackground to AvocadoColors.Danger
        BadgeType.PRIMARY -> AvocadoColors.IconBackground to AvocadoColors.Maroon
        BadgeType.NEUTRAL -> AvocadoColors.SecondarySurface to AvocadoColors.MutedText
    }
    Surface(color = bg, shape = RoundedCornerShape(50)) {
        Text(text, modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp), color = fg, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
    }
}

enum class BadgeType { NEUTRAL, SUCCESS, WARNING, DANGER, PRIMARY }

@Composable
fun SearchField(value: String, onValueChange: (String) -> Unit, placeholder: String, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text(placeholder, color = AvocadoColors.Placeholder, fontSize = 13.sp) },
        leadingIcon = { Text("⌕", color = AvocadoColors.MutedText, fontSize = 24.sp) },
        shape = RoundedCornerShape(24.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = AvocadoColors.SecondarySurface,
            unfocusedContainerColor = AvocadoColors.SecondarySurface,
            focusedBorderColor = AvocadoColors.Primary,
            unfocusedBorderColor = AvocadoColors.Border,
            focusedTextColor = AvocadoColors.PrimaryText,
            unfocusedTextColor = AvocadoColors.PrimaryText,
            cursorColor = AvocadoColors.Primary
        )
    )
}

@Composable
fun ProductThumb(emoji: String, modifier: Modifier = Modifier, size: Int = 76) {
    Box(
        modifier = modifier.size(size.dp).clip(RoundedCornerShape(15.dp)).background(AvocadoColors.SecondarySurface),
        contentAlignment = Alignment.Center
    ) { Text(emoji, fontSize = (size * 0.42).sp) }
}

@Composable
fun ProductRow(
    product: PetProduct,
    modifier: Modifier = Modifier,
    quantityLabel: String? = null,
    actionLabel: String? = null,
    onAction: () -> Unit = {}
) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(11.dp)) {
        ProductThumb(product.emoji, size = 76)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            if (product.badge != null) StatusBadge(product.badge, BadgeType.PRIMARY)
            Text(product.name, color = AvocadoColors.PrimaryText, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text(product.subtitle, color = AvocadoColors.MutedText, fontSize = 11.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                Text(formatVnd(product.price), color = AvocadoColors.Maroon, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                if (product.oldPrice != null) Text(formatVnd(product.oldPrice), color = AvocadoColors.Placeholder, fontSize = 10.sp)
            }
            if (quantityLabel != null) Text(quantityLabel, color = AvocadoColors.MutedText, fontSize = 10.sp)
        }
        if (actionLabel != null) {
            Surface(onClick = onAction, color = AvocadoColors.PrimarySoft, shape = RoundedCornerShape(10.dp)) {
                Text(actionLabel, modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp), color = AvocadoColors.Maroon, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun PriceLine(label: String, amount: String, emphasized: Boolean = false, amountColor: Color = AvocadoColors.PrimaryText) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = if (emphasized) AvocadoColors.PrimaryText else AvocadoColors.SecondaryText, fontSize = if (emphasized) 15.sp else 13.sp, fontWeight = if (emphasized) FontWeight.Bold else FontWeight.Normal)
        Text(amount, color = amountColor, fontSize = if (emphasized) 19.sp else 13.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ChoiceChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Surface(onClick = onClick, shape = RoundedCornerShape(50), color = if (selected) AvocadoColors.Maroon else AvocadoColors.SecondarySurface, border = if (selected) null else BorderStroke(0.6.dp, AvocadoColors.Border)) {
        Text(text, modifier = Modifier.padding(horizontal = 13.dp, vertical = 9.dp), color = if (selected) Color.White else AvocadoColors.SecondaryText, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun HorizontalChips(items: List<String>, selected: String, onSelected: (String) -> Unit) {
    Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { item -> ChoiceChip(item, item == selected) { onSelected(item) } }
    }
}

@Composable
fun TimelineItem(title: String, detail: String, time: String, type: BadgeType, isLast: Boolean = false) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val (color, icon) = when (type) {
                BadgeType.SUCCESS -> AvocadoColors.SuccessBackground to "✓"
                BadgeType.WARNING -> AvocadoColors.WarningBackground to "…"
                BadgeType.DANGER -> AvocadoColors.DangerBackground to "!"
                BadgeType.PRIMARY -> AvocadoColors.IconBackground to "●"
                BadgeType.NEUTRAL -> AvocadoColors.SecondarySurface to "•"
            }
            Box(Modifier.size(34.dp).clip(CircleShape).background(color), contentAlignment = Alignment.Center) {
                Text(icon, color = AvocadoColors.Maroon, fontWeight = FontWeight.Bold)
            }
            if (!isLast) Spacer(Modifier.width(1.dp).height(32.dp).background(AvocadoColors.Border))
        }
        Column(Modifier.weight(1f).padding(bottom = 8.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, Modifier.weight(1f), color = AvocadoColors.PrimaryText, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(time, color = AvocadoColors.MutedText, fontSize = 10.sp)
            }
            Text(detail, color = AvocadoColors.SecondaryText, fontSize = 12.sp, lineHeight = 17.sp)
        }
    }
}

@Composable
fun InfoBanner(text: String, emoji: String = "ℹ️", background: Color = AvocadoColors.SecondarySurface) {
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(background).padding(12.dp), horizontalArrangement = Arrangement.spacedBy(9.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(emoji, fontSize = 18.sp)
        Text(text, color = AvocadoColors.SecondaryText, fontSize = 12.sp, lineHeight = 17.sp)
    }
}

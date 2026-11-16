package com.example.petshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshop.R
import com.example.petshop.ui.theme.BorderDivider
import com.example.petshop.ui.theme.CardSurface
import com.example.petshop.ui.theme.MutedText
import com.example.petshop.ui.theme.PrimaryCTA

sealed class HomeTab(val route: String, val title: String, val iconRes: Int) {
    object Home : HomeTab("home", "Trang chủ", R.drawable.ic_home)
    object Category : HomeTab("category", "Danh mục", R.drawable.ic_category)
    object Assistant : HomeTab("assistant", "Trợ lý AI", R.drawable.ic_ai_assistant)
    object Profile : HomeTab("profile", "Cá nhân", R.drawable.ic_person)
}

@Composable
fun PetShopBottomNavBar(
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf(
        HomeTab.Home,
        HomeTab.Category,
        HomeTab.Assistant,
        HomeTab.Profile
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), ambientColor = BorderDivider, spotColor = BorderDivider)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(CardSurface)
            .border(1.dp, BorderDivider, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .padding(vertical = 10.dp, horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                val isSelected = selectedTab.route == tab.route

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = tab.iconRes),
                        contentDescription = tab.title,
                        tint = if (isSelected) PrimaryCTA else MutedText,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = tab.title,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) PrimaryCTA else MutedText
                    )
                }
            }
        }
    }
}

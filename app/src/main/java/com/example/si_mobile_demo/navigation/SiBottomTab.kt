package com.example.si_mobile_demo.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.si_mobile_demo.ui.theme.PrimitiveColors
import com.example.si_mobile_demo.ui.theme.PrimitiveElevation
import com.example.si_mobile_demo.ui.theme.PrimitiveIconSize
import com.example.si_mobile_demo.ui.theme.PrimitiveShape
import com.example.si_mobile_demo.ui.theme.PrimitiveSpacing


data class BottomTabItem(
    val title: String,
    val route: AppRoute,
    val icon: ImageVector,
)


@Composable
fun SiBottomNavigation(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onItemClick: (AppRoute) -> Unit,
) {
    val tabs = remember {
        listOf(
            BottomTabItem("SEARCH", SearchRoute, Icons.Default.Search),
            BottomTabItem("ATTENDANCE", AttendanceRoute, Icons.Default.CalendarMonth),
            BottomTabItem("SCORES", ScoreRoute, Icons.Default.Star),
            BottomTabItem("PROFILE", ProfileRoute, Icons.Default.Person),
        )
    }

    Surface(
        modifier = modifier
            .padding(horizontal = PrimitiveSpacing.Md, vertical = PrimitiveSpacing.Md)
            .fillMaxWidth()
            .height(PrimitiveSpacing.Huge),
        shape = PrimitiveShape.Full,
        color = PrimitiveColors.Blue46.copy(alpha = 0.9f),
        shadowElevation = PrimitiveElevation.High
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = PrimitiveSpacing.Sm2),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                val isSelected = currentDestination?.hasRoute(tab.route::class) == true

                TabItem(
                    tab = tab,
                    isSelected = isSelected,
                    onClick = { onItemClick(tab.route) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}



@Composable
private fun TabItem(
    tab: BottomTabItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) PrimitiveColors.BlueDeep else PrimitiveColors.White.copy(alpha = 0.7f),
        label = "contentColor"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) PrimitiveColors.Mint200 else Color.Transparent,
        label = "backgroundColor"
    )

    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(vertical = PrimitiveSpacing.Sm2, horizontal = PrimitiveSpacing.Xs)
            .clip(PrimitiveShape.Lg)
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = tab.icon,
            contentDescription = tab.title,
            tint = contentColor,
            modifier = Modifier.size(PrimitiveIconSize.Md)
        )
    }
}

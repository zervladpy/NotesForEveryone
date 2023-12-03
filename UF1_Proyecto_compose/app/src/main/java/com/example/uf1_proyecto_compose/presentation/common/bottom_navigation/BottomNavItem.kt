package com.example.uf1_proyecto_compose.presentation.common.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.uf1_proyecto_compose.app_navigation.Routes

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val outlinedIcon: ImageVector,
    val route: Routes
)

val mainNavItems: List<BottomNavItem> = listOf(
    BottomNavItem(
        label = "Home",
        icon = Icons.Rounded.Home,
        outlinedIcon = Icons.Outlined.Home,
        route = Routes.Home,
    ),
    BottomNavItem(
        label = "History",
        icon = Icons.Rounded.Refresh,
        outlinedIcon = Icons.Outlined.Refresh,
        route = Routes.Home,
    ),
    BottomNavItem(
        label = "Settings",
        icon = Icons.Rounded.Settings,
        outlinedIcon = Icons.Outlined.Settings,
        route = Routes.Home,
    )
)
package com.example.uf1_proyecto_compose.componets.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDrawerItem(
    val id: Int,
    val label: String,
    val route: String,
    val icon: ImageVector,
    val desc: String?,
) {
    object Dashboard :
        NavDrawerItem(
            id = 0,
            label = "DashBoard",
            route = "dashboard",
            icon = Icons.Rounded.Home,
            desc = "go to dashboard"
        )

    object Calendar : NavDrawerItem(
        id = 1,
        label = "Calendar",
        route = "calendar",
        icon = Icons.Rounded.DateRange,
        desc = "go to calendar"
    )
}

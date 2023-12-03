package com.example.uf1_proyecto_compose.presentation.common.bottom_navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.uf1_proyecto_compose.presentation.navigation.bottom_router.BottomRoutes

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val outlinedIcon: ImageVector,
    val route: BottomRoutes
)

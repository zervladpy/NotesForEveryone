package com.example.uf1_proyecto_compose.presentation.common.drawer

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val label: String,
    val description: String,
    val icon: ImageVector,
    val route: String,
)

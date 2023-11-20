package com.example.uf1_proyecto_compose.data

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: Int,
    val label: String,
    val description: String,
    val icon: ImageVector,
)

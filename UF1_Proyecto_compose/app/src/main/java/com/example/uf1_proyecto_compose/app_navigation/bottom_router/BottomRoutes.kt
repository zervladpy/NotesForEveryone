package com.example.uf1_proyecto_compose.app_navigation.bottom_router

sealed class BottomRoutes(val route: String) {

    object Home : BottomRoutes("home")
    object History : BottomRoutes("history")
    object Settings : BottomRoutes("Settings")

}

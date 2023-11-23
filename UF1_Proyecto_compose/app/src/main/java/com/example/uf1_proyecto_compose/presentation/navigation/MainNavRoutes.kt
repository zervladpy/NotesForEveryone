package com.example.uf1_proyecto_compose.presentation.navigation

sealed class MainNavRoutes(val route: String) {
    object HomeScreen : MainNavRoutes("home")
    object TaskScreen : MainNavRoutes("task")

}

package com.example.uf1_proyecto_compose.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen : AppScreens("dashboard")
    object CalendarScreen : AppScreens("calendar")
    object TaskScreen : AppScreens("task")

}

package com.example.uf1_proyecto_compose.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen : AppScreens("home")
    object CalendarScreen : AppScreens("calendar")
    object TaskScreen : AppScreens("task")

}

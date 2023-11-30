package com.example.uf1_proyecto_compose.presentation.navigation.app

sealed class AppNavigation(val route: String) {

    object SplashScreen : AppNavigation(route = "splash")

    object AuthScreen : AppNavigation(route = "auth")

    object LoginScreen : AppNavigation(route = "auth/login")

    object SignUpScreen : AppNavigation(route = "auth/signup")

    object HomeScreen : AppNavigation(route = "home")

    object ProfileScreen : AppNavigation(route = "profile")

    object TasksScreen : AppNavigation(route = "tasks")

    data class TaskScreen(val uid: String) : AppNavigation(route = "tasks/${uid}")

    object CreateTaskScreen : AppNavigation(route = "tasks/create")

}
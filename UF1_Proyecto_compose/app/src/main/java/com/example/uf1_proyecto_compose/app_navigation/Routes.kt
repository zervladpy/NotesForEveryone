package com.example.uf1_proyecto_compose.app_navigation

sealed class Routes(val route: String) {
    object Root : Routes("/")
    object Splash : Routes("splash")
    object Auth : Routes("auth")
    object Landing : Routes("landing")
    object Login : Routes("login")
    object Signup : Routes("signup")
    object Home : Routes("home")
    object Dashboard : Routes("dashboard")
    object Tasks : Routes("tasks")
    object TaskList : Routes("task_list")
    object TaskCreate : Routes("create")
    object TaskDetail : Routes("{uid}")
}
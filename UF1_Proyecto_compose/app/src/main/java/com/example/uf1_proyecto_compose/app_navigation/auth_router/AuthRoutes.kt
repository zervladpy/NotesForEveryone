package com.example.uf1_proyecto_compose.app_navigation.auth_router

sealed class AuthRoutes(val route: String) {
    object Landing : AuthRoutes("landing")
    object Login : AuthRoutes("login")
    object SignUp : AuthRoutes("signup")
}

package com.example.uf1_proyecto_compose.presentation.navigation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.landing_screen.LandingScreen
import com.example.uf1_proyecto_compose.presentation.login_screen.LoginScreen
import com.example.uf1_proyecto_compose.presentation.register_screen.RegisterScreen

@Composable
fun AuthNavigation(
) {

    val routes = AuthNavRoutes

    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = routes.LANDING_SCREEN,
    ) {

        composable(
            route = routes.LANDING_SCREEN
        ) {
            LandingScreen(navController = navController)
        }

        composable(
            route = routes.LOGIN_SCREEN
        ) {
            LoginScreen()
        }

        composable(
            route = routes.REGISTER_SCREEN
        ) {
            RegisterScreen()
        }

    }

}
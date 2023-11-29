package com.example.uf1_proyecto_compose.presentation.navigation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.screens.auth.LoginScreen
import com.example.uf1_proyecto_compose.presentation.screens.auth.SignUpScreen
import com.example.uf1_proyecto_compose.presentation.screens.landing.LandingScreen

@Composable
fun AuthNavigation(
    modifier: Modifier = Modifier,
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
            LandingScreen(
                navController = navController
            )
        }

        composable(
            route = routes.LOGIN_SCREEN
        ) {
            LoginScreen(navController = navController)
        }

        composable(
            route = routes.REGISTER_SCREEN
        ) {
            SignUpScreen(navController = navController)
        }

    }

}
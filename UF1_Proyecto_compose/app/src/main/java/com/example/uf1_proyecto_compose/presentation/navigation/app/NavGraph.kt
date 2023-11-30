package com.example.uf1_proyecto_compose.presentation.navigation.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.screens.auth.LoginScreen
import com.example.uf1_proyecto_compose.presentation.screens.auth.SignUpScreen
import com.example.uf1_proyecto_compose.presentation.screens.home.HomeScreen
import com.example.uf1_proyecto_compose.presentation.screens.landing.LandingScreen
import com.example.uf1_proyecto_compose.presentation.screens.splash.SplashScreen
import com.example.uf1_proyecto_compose.presentation.screens.tasks.TaskListScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppNavigation.SplashScreen.route
    ) {

        composable(
            route = AppNavigation.SplashScreen.route,
        ) {
            SplashScreen(navController = navController)
        }

        composable(
            route = AppNavigation.AuthScreen.route
        ) {
            LandingScreen(navController = navController)
        }

        composable(
            route = AppNavigation.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }

        composable(
            route = AppNavigation.SignUpScreen.route
        ) {
            SignUpScreen(navController = navController)
        }

        composable(
            route = AppNavigation.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = AppNavigation.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = AppNavigation.TasksScreen.route
        ) {
            TaskListScreen(navController = navController)
        }

    }

}
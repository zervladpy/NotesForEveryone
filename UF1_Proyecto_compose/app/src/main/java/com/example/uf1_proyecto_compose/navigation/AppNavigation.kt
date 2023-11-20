package com.example.uf1_proyecto_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.screens.CalendarScreen
import com.example.uf1_proyecto_compose.screens.HomeScreen
import com.example.uf1_proyecto_compose.screens.TaskScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen()
        }

        composable(route = AppScreens.CalendarScreen.route) {
            CalendarScreen()
        }

        composable(route = AppScreens.TaskScreen.route) {
            TaskScreen()
        }
    }
}
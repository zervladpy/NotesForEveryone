package com.example.uf1_proyecto_compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.home_screen.HomeScreen
import com.example.uf1_proyecto_compose.presentation.task_detail.TaskDetailScreen

@Composable
fun MainNavigationHost() {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = NavDestinations.HomeScreen.route) {

        /// Home Route
        composable(NavDestinations.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                scope = scope,
            )
        }

        /// Task Creation Route
        composable(NavDestinations.TaskDetails.route) {
            TaskDetailScreen(
                navController = navController,
                scope = scope,
            )
        }

    }
}
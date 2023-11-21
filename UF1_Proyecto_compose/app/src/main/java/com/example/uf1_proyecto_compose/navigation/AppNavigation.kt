package com.example.uf1_proyecto_compose.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.screens.CalendarScreen
import com.example.uf1_proyecto_compose.screens.HomeScreen
import com.example.uf1_proyecto_compose.screens.TaskScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                scope = scope,
                drawerState = drawerState,
                navController = navController
            )
        }

        composable(route = AppScreens.CalendarScreen.route) {
            CalendarScreen(
                scope = scope,
                drawerState = drawerState,
                navController = navController
            )
        }

        composable(route = AppScreens.TaskScreen.route) {
            TaskScreen()
        }
    }
}
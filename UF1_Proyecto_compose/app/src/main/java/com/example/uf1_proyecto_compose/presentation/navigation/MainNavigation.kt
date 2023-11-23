package com.example.uf1_proyecto_compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uf1_proyecto_compose.presentation.home_screen.HomeScreen
import com.example.uf1_proyecto_compose.presentation.task_screen.TaskScreen
import com.example.uf1_proyecto_compose.presentation.viewmodels.TasksViewModel

@Composable
fun MainNavigation(
    tasksViewModel: TasksViewModel,
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainNavRoutes.HomeScreen.route) {

        composable(MainNavRoutes.HomeScreen.route) {
            HomeScreen(
                tasksViewModel = tasksViewModel,
                navController = navController
            )
        }

        composable(MainNavRoutes.TaskScreen.route) {
            TaskScreen(
                tasksViewModel = tasksViewModel,
                navController = navController,
            )
        }

        composable(
            route = "${MainNavRoutes.TaskScreen.route}/{taskUid}",
            arguments = listOf(navArgument("taskUid") { type = NavType.StringType })
        ) {

            TaskScreen(
                tasksViewModel = tasksViewModel,
                navController = navController,
                taskUid = it.arguments?.getString("taskUid")
            )
        }

    }

}
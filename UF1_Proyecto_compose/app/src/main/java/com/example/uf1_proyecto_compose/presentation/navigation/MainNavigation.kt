package com.example.uf1_proyecto_compose.presentation.navigation

import android.util.Log
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uf1_proyecto_compose.presentation.calendar_screen.CalendarScreen
import com.example.uf1_proyecto_compose.presentation.common.drawer.DrawerContent
import com.example.uf1_proyecto_compose.presentation.home_screen.HomeScreen
import com.example.uf1_proyecto_compose.presentation.task_screen.TaskScreen
import com.example.uf1_proyecto_compose.presentation.viewmodels.TasksViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(
    tasksViewModel: TasksViewModel,
) {

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            DrawerContent(
                navController = navController
            )
        }
    ) {

        fun openDrawer() {
            scope.launch {
                drawerState.apply {
                    Log.d("Drawer State", "opening")
                    open()
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = NavigationRoute.HOME_SCREEN
        ) {

            composable(
                route = NavigationRoute.HOME_SCREEN
            ) {
                HomeScreen(
                    tasksViewModel = tasksViewModel,
                    navController = navController,
                    openDrawer = { openDrawer() }
                )
            }

            composable(
                route = NavigationRoute.TASK_SCREEN
            ) {
                TaskScreen(
                    tasksViewModel = tasksViewModel,
                    navController = navController,
                )
            }

            composable(
                route = NavigationRoute.TASK_SCREEN_TASK_UID,
                arguments = listOf(navArgument("taskUid") { type = NavType.StringType })
            ) {

                TaskScreen(
                    tasksViewModel = tasksViewModel,
                    navController = navController,
                    taskUid = it.arguments?.getString("taskUid")
                )
            }

            composable(
                route = NavigationRoute.CALENDAR_SCREEN,
            ) {
                CalendarScreen(
                    openDrawer = { openDrawer() }
                )
            }


        }
    }

}
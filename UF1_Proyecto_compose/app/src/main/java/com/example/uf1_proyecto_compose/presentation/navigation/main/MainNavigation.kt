package com.example.uf1_proyecto_compose.presentation.navigation.main

import android.util.Log
import androidx.compose.material3.DrawerValue
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
import kotlinx.coroutines.launch

@Composable
fun MainNavigation() {

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
            startDestination = MainNavRoutes.HOME_SCREEN
        ) {

            composable(
                route = MainNavRoutes.HOME_SCREEN
            ) {
                HomeScreen(
                    navController = navController,
                    openDrawer = { openDrawer() }
                )
            }

            composable(
                route = MainNavRoutes.TASK_SCREEN
            ) {
                TaskScreen(
                    navController = navController,
                )
            }

            composable(
                route = MainNavRoutes.TASK_SCREEN_TASK_UID,
                arguments = listOf(navArgument("taskUid") { type = NavType.StringType })
            ) {

                TaskScreen(
                    navController = navController,
                    taskUid = it.arguments?.getString("taskUid")
                )
            }

            composable(
                route = MainNavRoutes.CALENDAR_SCREEN,
            ) {
                CalendarScreen(
                    openDrawer = { openDrawer() }
                )
            }


        }
    }

}
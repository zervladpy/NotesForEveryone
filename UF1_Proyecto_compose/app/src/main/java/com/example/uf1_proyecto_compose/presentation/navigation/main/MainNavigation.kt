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
import com.example.uf1_proyecto_compose.presentation.common.drawer.DrawerContent
import com.example.uf1_proyecto_compose.presentation.screens.home.HomeScreen
import com.example.uf1_proyecto_compose.presentation.screens.tasks.TaskCreateScreen
import com.example.uf1_proyecto_compose.presentation.screens.tasks.TaskDetailScreen
import com.example.uf1_proyecto_compose.presentation.screens.tasks.TaskListScreen
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
            startDestination = "home"
        ) {

            composable(
                route = "home"
            ) {
                HomeScreen(
                    navController = navController,
                    openDrawer = { openDrawer() },
                )
            }

            composable(
                route = "tasks"
            ) {
                TaskListScreen(
                    navController = navController,
                    openDrawer = { openDrawer() },
                )
            }

            composable(
                route = "tasks/{taskUid}",
                arguments = listOf(navArgument("taskUid") { type = NavType.StringType })
            ) {

                TaskDetailScreen(
                    navController = navController,
                    uid = it.arguments!!.getString("taskUid")!!
                )
            }

            composable(
                route = "tasks/create",
            ) {
                TaskCreateScreen(
                    navController = navController,
                )
            }

        }
    }

}
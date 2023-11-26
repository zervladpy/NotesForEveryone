package com.example.uf1_proyecto_compose.presentation.common.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.navigation.main.MainNavRoutes

@Composable
fun DrawerContent(
    navController: NavController = rememberNavController(),
) {
    Drawer(
        navController = navController,
        actions = {
            DrawerItem(
                item = MenuItem(
                    icon = Icons.Rounded.Add,
                    description = "",
                    route = MainNavRoutes.TASK_SCREEN,
                    label = "Create Task",
                ),
                onSelect = {
                    navController.navigate(MainNavRoutes.TASK_SCREEN)
                }
            )
        },
        categories = listOf(
            MenuCategory(
                label = "Really good label",
                items = listOf(
                    MenuItem(
                        label = "Home",
                        description = "",
                        route = MainNavRoutes.HOME_SCREEN,
                        icon = Icons.Rounded.Home
                    ),
                    MenuItem(
                        label = "Calendar",
                        description = "",
                        route = MainNavRoutes.CALENDAR_SCREEN,
                        icon = Icons.Rounded.DateRange
                    )
                )
            )
        )
    )
}
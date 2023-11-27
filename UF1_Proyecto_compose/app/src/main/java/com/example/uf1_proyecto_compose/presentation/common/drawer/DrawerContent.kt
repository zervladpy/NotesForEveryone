package com.example.uf1_proyecto_compose.presentation.common.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DrawerContent(
    navController: NavController = rememberNavController(),
) {
    Drawer(
        navController = navController,
        categories = listOf(
            MenuCategory(
                label = "Really good label",
                items = listOf(
                    MenuItem(
                        label = "Dashboard",
                        description = "",
                        route = "home",
                        icon = Icons.Rounded.Home
                    ),
                    MenuItem(
                        label = "Tasks",
                        description = "",
                        route = "tasks",
                        icon = Icons.Rounded.ShoppingCart
                    )
                )
            )
        )
    )
}
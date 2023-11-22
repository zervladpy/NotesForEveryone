package com.example.uf1_proyecto_compose.presentation.navigation

sealed class NavDestinations(val route: String) {

    object HomeScreen : NavDestinations("home")
    object TaskDetails : NavDestinations("taskDetails")

}

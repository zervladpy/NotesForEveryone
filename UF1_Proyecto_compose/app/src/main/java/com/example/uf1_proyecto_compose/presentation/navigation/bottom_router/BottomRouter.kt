package com.example.uf1_proyecto_compose.presentation.navigation.bottom_router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uf1_proyecto_compose.presentation.screens.history.HistoryScreen
import com.example.uf1_proyecto_compose.presentation.screens.home.HomeScreen
import com.example.uf1_proyecto_compose.presentation.screens.settings.SettingsScreen
import com.example.uf1_proyecto_compose.presentation.viewmodels.profile.ProfileEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.profile.ProfileState
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksState

@Composable
fun BottomRouter(
    modifier: Modifier,
    navController: NavHostController,
    sharedTasksState: SharedTasksState,
    onSharedTaskEvent: (SharedTasksEvent) -> Unit,
    profileState: ProfileState,
    onProfileEvent: (ProfileEvent) -> Unit,
    navigateToTaskDetail: (String) -> Unit,
    navigateToCreateTask: () -> Unit,
    onLogout: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomRoutes.Home.route
    ) {

        composable(
            route = BottomRoutes.Home.route
        ) {
            HomeScreen(
                modifier = modifier,
                state = sharedTasksState,
                onEvent = onSharedTaskEvent,
                navigateToTask = navigateToTaskDetail,
                navigateToCreateTask = navigateToCreateTask,
                logout = onLogout,
            )
        }

        composable(
            route = BottomRoutes.History.route
        ) {
            HistoryScreen(
                modifier = modifier,
                sharedTasksState = sharedTasksState,
                navigateToTask = navigateToTaskDetail
            )
        }

        composable(
            route = BottomRoutes.Settings.route
        ) {
            SettingsScreen(
                modifier = modifier,
                onEvent = onProfileEvent,
                profileState = profileState,
                logout = onLogout
            )
        }
    }

}
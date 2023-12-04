package com.example.uf1_proyecto_compose.presentation.navigation.bottom_router

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        startDestination = BottomRoutes.Home.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
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
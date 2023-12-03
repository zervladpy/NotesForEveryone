package com.example.uf1_proyecto_compose.presentation.screens.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.app_navigation.bottom_router.BottomRouter
import com.example.uf1_proyecto_compose.app_navigation.bottom_router.BottomRoutes
import com.example.uf1_proyecto_compose.presentation.common.bottom_navigation.BottomNavItem
import com.example.uf1_proyecto_compose.presentation.common.bottom_navigation.N4EBottomNavigationBar
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.profile.ProfileEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.profile.ProfileState
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    sharedTasksState: SharedTasksState,
    onSharedTaskEvent: (SharedTasksEvent) -> Unit,
    navigateToTaskDetail: (String) -> Unit,
    navigateToCreateTask: () -> Unit,
    onLogout: () -> Unit,
    profileState: ProfileState,
    onProfileEvent: (ProfileEvent) -> Unit
    // homeState
) {

    val navController = rememberNavController()


    val mainNavItems: List<BottomNavItem> = listOf(
        BottomNavItem(
            label = stringResource(id = R.string.bottom_navigation_home_label),
            icon = Icons.Rounded.Home,
            outlinedIcon = Icons.Outlined.Home,
            route = BottomRoutes.Home,
        ),
        BottomNavItem(
            label = stringResource(id = R.string.bottom_navigation_history_label),
            icon = Icons.Rounded.History,
            outlinedIcon = Icons.Outlined.History,
            route = BottomRoutes.History,
        ),
        BottomNavItem(
            label = stringResource(id = R.string.bottom_navigation_settings_label),
            icon = Icons.Rounded.Settings,
            outlinedIcon = Icons.Outlined.Settings,
            route = BottomRoutes.Settings,
        )
    )

    Scaffold(
        bottomBar = {
            N4EBottomNavigationBar(
                navItems = mainNavItems,
                navController = navController,
                currentRoute = currentRoute(navController)
            )
        },
        content = {
            Box(
                modifier = modifier.padding(
                    bottom = it.calculateBottomPadding()
                )
            ) {
                BottomRouter(
                    modifier = modifier,
                    navController = navController,
                    sharedTasksState = sharedTasksState,
                    onLogout = onLogout,
                    onSharedTaskEvent = onSharedTaskEvent,
                    navigateToTaskDetail = navigateToTaskDetail,
                    navigateToCreateTask = navigateToCreateTask,
                    profileState = profileState,
                    onProfileEvent = onProfileEvent,
                )
            }
        }
    )
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    Notes4EveryoneTheme {
        MainScreen(
            sharedTasksState = SharedTasksState(),
            navigateToCreateTask = {},
            navigateToTaskDetail = {},
            onSharedTaskEvent = {},
            onLogout = {},
            onProfileEvent = {},
            profileState = ProfileState()
        )
    }
}
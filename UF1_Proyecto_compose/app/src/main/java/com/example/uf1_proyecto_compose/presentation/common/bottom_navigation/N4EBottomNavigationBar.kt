package com.example.uf1_proyecto_compose.presentation.common.bottom_navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.presentation.navigation.bottom_router.BottomRoutes
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme

@Composable
fun N4EBottomNavigationBar(
    modifier: Modifier = Modifier,
    navItems: List<BottomNavItem>,
    navController: NavHostController,
    currentRoute: String?
) {


    NavigationBar {

        navItems.forEach {
            val selected = currentRoute == it.route.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(it.route.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) {
                            it.icon
                        } else it.outlinedIcon,
                        contentDescription = ""
                    )
                },
                label = { Text(text = it.label) }
            )
        }

    }
}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {


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

    Notes4EveryoneTheme {
        N4EBottomNavigationBar(
            navItems = mainNavItems,
            navController = rememberNavController(),
            currentRoute = BottomRoutes.Home.route
        )
    }

}
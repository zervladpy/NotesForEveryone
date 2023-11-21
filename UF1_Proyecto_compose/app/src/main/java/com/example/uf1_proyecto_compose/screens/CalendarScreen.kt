package com.example.uf1_proyecto_compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.componets.appbar.MainAppbar
import com.example.uf1_proyecto_compose.componets.drawer.MainDrawer
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainDrawer(navigationController = navController)
        }
    ) {
        Scaffold(
            topBar = {
                MainAppbar(
                    scope = scope,
                    drawerState = drawerState,
                )
            },
            content = {
                CalendarContent(paddingValues = it)
            }
        )
    }
}

@Composable
fun CalendarContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = modifier
            .padding(paddingValues)
            .padding(10.dp)
    ) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CalendarScreenPreview() {
    CalendarScreen(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        scope = rememberCoroutineScope(),
        navController = rememberNavController(),
    )
}

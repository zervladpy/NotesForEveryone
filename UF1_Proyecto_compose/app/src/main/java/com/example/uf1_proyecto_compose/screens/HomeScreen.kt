package com.example.uf1_proyecto_compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.componets.SearchBar
import com.example.uf1_proyecto_compose.componets.TaskProgressionCard
import com.example.uf1_proyecto_compose.componets.appbar.MainAppbar
import com.example.uf1_proyecto_compose.componets.drawer.MainDrawer
import com.example.uf1_proyecto_compose.componets.fabs.FabAddTaskButton
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    navController: NavController,
    drawerState: DrawerState,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainDrawer(
                navigationController = navController
            )
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
                HomeContent(paddingValues = it)
            },
            floatingActionButton = {
                FabAddTaskButton(
                    onClick = {}
                )
            },
            floatingActionButtonPosition = FabPosition.Center

        )
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {

    var searchText: String by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .padding(horizontal = 7.dp)
            .fillMaxSize()
    ) {
        SearchBar(
            searchText = searchText,
            onChange = { searchText = it }
        )

        Column(
            modifier = Modifier.padding(
                top = 7.dp
            )
        ) {
            if (searchText.isEmpty()) {
                DefaultBodyLayout()
            } else {
                IsSearchingLayout()
            }
        }

    }
}

@Composable
fun IsSearchingLayout() {
    Text(text = "Searching ...")
}

@Composable
fun DefaultBodyLayout() {
    Column {
        TaskProgressionCard()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        scope = rememberCoroutineScope(),
        navController = rememberNavController(),
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
}

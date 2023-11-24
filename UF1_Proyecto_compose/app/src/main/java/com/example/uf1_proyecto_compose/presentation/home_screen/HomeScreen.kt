package com.example.uf1_proyecto_compose.presentation.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.buttons.FabButton
import com.example.uf1_proyecto_compose.presentation.common.cards.TaskPreviewCard
import com.example.uf1_proyecto_compose.presentation.navigation.NavigationRoute
import com.example.uf1_proyecto_compose.presentation.viewmodels.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel,
    navController: NavController,
    openDrawer: () -> Unit = {},
) {

    Scaffold(
        topBar = {
            CenteredAppbar(
                title = "Home",
                onNavigationIconClick = openDrawer
            )
        },
        content = {
            Column(
                modifier = Modifier.padding(it)
            ) {
                LazyColumn {
                    items(tasksViewModel.tasks) { task ->
                        TaskPreviewCard(
                            task = task,
                            onClick = {
                                navController.navigate(NavigationRoute.TASK_SCREEN + "/" + task.uid)
                            }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FabButton(
                icon = Icons.Rounded.Add,
                title = "Create Task",
                onClick = {
                    navController.navigate(NavigationRoute.TASK_SCREEN)
                }
            )
        }
    )
}

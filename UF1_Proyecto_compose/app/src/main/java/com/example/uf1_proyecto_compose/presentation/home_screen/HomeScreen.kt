package com.example.uf1_proyecto_compose.presentation.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.cards.TaskPreviewCard
import com.example.uf1_proyecto_compose.presentation.navigation.main.MainNavRoutes
import com.example.uf1_proyecto_compose.presentation.viewmodels.tasks.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel = hiltViewModel(),
    navController: NavController,
    openDrawer: () -> Unit = {},
) {

    val tasks = tasksViewModel.state.value.tasks

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
                LazyRow(
                    modifier = Modifier
                        .padding(7.dp)
                        .height(30.dp)
                ) {
                    // TODO (ADD PILLS)
                }
                LazyColumn {
                    items(tasks) { task ->
                        TaskPreviewCard(
                            task = task,
                            onClick = { task ->
                                navController.navigate(MainNavRoutes.TASK_SCREEN + "/" + task.uid)
                            },
                            progress = if (task.done) 1f else 0f
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            N4EFabButton(
                icon = Icons.Rounded.Add,
                title = "Create Task",
                onClick = {
                    navController.navigate(MainNavRoutes.TASK_SCREEN)
                }
            )
        }
    )
}

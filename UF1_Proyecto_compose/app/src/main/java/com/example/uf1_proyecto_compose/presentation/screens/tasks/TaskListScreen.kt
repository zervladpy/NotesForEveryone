package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.cards.TaskPreviewCard
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskListViewModel

@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    openDrawer: () -> Unit = {},
) {

    Scaffold(
        topBar = { TaskListAppbar(openDrawer = openDrawer) },
        floatingActionButton = {
            FabButton {
                navController.navigate("tasks/create")
            }
        },
        content = { Content(Modifier.padding(it), navController = navController) },

        )
}

@Composable
private fun FabButton(
    onClick: () -> Unit,
) {

    N4EFabButton(
        icon = Icons.Rounded.Add,
        title = "Create Task",
        onClick = onClick
    )

}

@Composable
private fun TaskListAppbar(
    openDrawer: () -> Unit,
) {
    CenteredAppbar(
        onNavigationIconClick = openDrawer
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    viewModel: TaskListViewModel = hiltViewModel(),
    navController: NavController,
) {

    var tasks = emptyList<Task>()

    viewModel.state.value.tasks?.observeForever {
        Log.d("tasks", it.toString())
        tasks = it
    }

    TaskList(
        tasks = tasks,
        onClick = {
            navController.navigate("tasks/${it}")
        }
    )
}

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    tasks: List<Task> = emptyList(),
    onClick: (String) -> Unit,
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(tasks) {
            TaskPreviewCard(
                task = it,
                onClick = { it -> onClick(it.uid) }
            )
        }
    }

}
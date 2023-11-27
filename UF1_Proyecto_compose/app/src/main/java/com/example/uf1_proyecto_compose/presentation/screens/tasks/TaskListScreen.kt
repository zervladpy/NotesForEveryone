package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.cards.TaskPreviewCard
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskListViewModel
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    openDrawer: () -> Unit = {},
) {
    Scaffold(
        topBar = { TaskListAppbar(openDrawer = openDrawer) },
        floatingActionButton = {
            TaskListFabButton {
                navController.navigate("tasks/create")
            }
        },
        content = { TaskListContent(Modifier.padding(it), navController = navController) },

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
private fun TaskListContent(
    modifier: Modifier = Modifier,
    viewModel: TaskListViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state = viewModel.state.value

    LazyColumn(modifier) {
        items(state.tasks) {
            TaskPreviewCard(
                task = it,
                onClick = { task ->
                    navController.navigate("tasks/${task.uid}")
                }
            )
        }
    }
}

@Composable
private fun TaskListFabButton(
    onClick: () -> Unit,
) {

    N4EFabButton(
        icon = Icons.Rounded.Add,
        title = "Create Task",
        onClick = onClick
    )

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TaskListScreenPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TaskListScreen(
                navController = rememberNavController()
            )
        }
    }
}
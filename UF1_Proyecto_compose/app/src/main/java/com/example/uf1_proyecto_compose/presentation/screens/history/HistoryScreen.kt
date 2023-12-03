package com.example.uf1_proyecto_compose.presentation.screens.history

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.presentation.common.cards.TaskPreviewCard
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksState

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    sharedTasksState: SharedTasksState,
    navigateToTask: (String) -> Unit
) {
    Scaffold(
        topBar = { Appbar() },
        content = {
            Content(
                modifier = modifier.padding(it),
                tasks = sharedTasksState.tasks,
                navigateToTask = navigateToTask
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Appbar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.history_screen_title))
        }
    )
}

@Composable
private fun Content(
    modifier: Modifier,
    tasks: List<Task>,
    navigateToTask: (String) -> Unit
) {

    LazyColumn(
        modifier = modifier.padding(
            vertical = 10.dp,
            horizontal = 20.dp

        )
    ) {
        items(tasks) { task ->
            if (task.done) {
                TaskPreviewCard(
                    modifier = Modifier.padding(bottom = 10.dp),
                    task = task,
                    onClick = {
                        navigateToTask(task.uid)
                    }
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    Notes4EveryoneTheme {
        HistoryScreen(
            sharedTasksState = SharedTasksState(
                tasks = listOf(
                    Task(
                        title = "Test Task",
                        done = true,
                        progression = 1f
                    ),
                    Task(
                        title = "Test Task",
                        done = true,
                    ),
                )
            ),
            navigateToTask = {}
        )
    }
}
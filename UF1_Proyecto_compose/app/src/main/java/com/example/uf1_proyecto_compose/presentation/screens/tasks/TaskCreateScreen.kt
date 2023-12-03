package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.screens.tasks.component.SubtaskCreatePreview
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.create_task.CreateTaskEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.create_task.CreateTaskState
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksEvent


@Composable
fun TaskCreateScreen(
    modifier: Modifier = Modifier,
    state: CreateTaskState,
    onCreateTaskViewModelEvent: (CreateTaskEvent) -> Unit,
    onTasksViewModelAddEvent: (SharedTasksEvent.AddTask) -> Unit,
    navigateBack: () -> Unit,
    navigateToNewTask: (String) -> Unit,
) {

    Scaffold(
        topBar = {
            Appbar(
                navigateBack = navigateBack
            )
        },
        floatingActionButton = {
            FabButton(
                onClick = {
                    if (state.taskTitleError.isEmpty() && !state.isLoading) {
                        onTasksViewModelAddEvent(SharedTasksEvent.AddTask(state.task))
                        navigateToNewTask(state.task.uid)
                    }
                }
            )
        },
        content = {
            Content(
                modifier = modifier
                    .padding(it)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                state = state,
                eventHandler = onCreateTaskViewModelEvent
            )
        }
    )

}

@Composable
private fun FabButton(
    onClick: () -> Unit
) {
    N4EFabButton(
        icon = Icons.Rounded.Add,
        title = "Create Task",
        onClick = onClick
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Appbar(
    navigateBack: () -> Unit,
) {

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(text = "New Task")
        }
    )
}

@Composable
private fun Content(
    modifier: Modifier,
    state: CreateTaskState,
    eventHandler: (CreateTaskEvent) -> Unit,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {

        val titleLabel = "Task Title"
        N4ETextField(
            label = titleLabel,
            placeholder = titleLabel,
            value = state.task.title,
            onEdit = { eventHandler(CreateTaskEvent.TaskTitleChanged(it)) },
            isError = state.taskTitleError.isNotEmpty(),
            errorMessage = state.taskTitleError
        )

        val descriptionLabel = "Task Description"
        N4ETextField(
            label = descriptionLabel,
            placeholder = descriptionLabel,
            value = state.task.description,
            onEdit = { eventHandler(CreateTaskEvent.TaskDescriptionChanged(it)) }
        )

        val subtaskLabel = "Subtasks"
        val subtaskPlaceholder = "Add Subtask"
        N4ETextField(
            label = subtaskLabel,
            placeholder = subtaskPlaceholder,
            value = state.subtaskTitle,
            onEdit = { eventHandler(CreateTaskEvent.TaskSubtaskTitleChanged(it)) },
            trailingIcon = Icons.Rounded.Add,
            trailingAction = { eventHandler(CreateTaskEvent.TaskSubtaskAdd()) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        ShowSubtasks(
            items = state.task.subtasks,
            removeItem = { eventHandler(CreateTaskEvent.TaskSubtaskRemove(it.uid)) }
        )

    }
}

@Composable
fun ShowSubtasks(
    items: List<Subtask>,
    removeItem: (Subtask) -> Unit,
) {
    Column {
        for (item in items) {
            SubtaskCreatePreview(
                subtask = item,
                action = { removeItem(item) }
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TaskCreateScreenPreview() {

    Notes4EveryoneTheme {
        TaskCreateScreen(
            state = CreateTaskState(),
            navigateBack = {},
            navigateToNewTask = {},
            onCreateTaskViewModelEvent = {},
            onTasksViewModelAddEvent = {}
        )
    }

}

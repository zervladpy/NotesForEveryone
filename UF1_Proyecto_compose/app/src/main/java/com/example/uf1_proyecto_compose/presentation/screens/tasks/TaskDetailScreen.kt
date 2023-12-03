package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.detail_task.DetailTaskEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.detail_task.DetailTaskState
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksEvent
import kotlinx.coroutines.launch
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    state: DetailTaskState,
    onTaskDetailEvent: (DetailTaskEvent) -> Unit,
    onSharedTasksEvent: (SharedTasksEvent) -> Unit,
    navigateBack: () -> Unit,
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    fun notify(message: String) {
        scope.launch {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            Appbar(
                popBack = navigateBack
            )
        },
        content = {
            Content(
                modifier = modifier.padding(it),
                state = state,
                onEvent = onTaskDetailEvent,
            )
        },
        bottomBar = {
            BottomAppbar(
                onDelete = {
                    onSharedTasksEvent(SharedTasksEvent.RemoveTask(state.task))
                    navigateBack()
                }
            )
        },
        floatingActionButton = {
            FabButton(
                icon = if (state.isEditing) {
                    Icons.Rounded.Done
                } else Icons.Rounded.Edit,
                onClick = {
                    if (state.isEditing) {
                        onSharedTasksEvent(SharedTasksEvent.UpdateTask(state.task))
                    }
                    onTaskDetailEvent(DetailTaskEvent.ToggleEditing(!state.isEditing))
                }
            )
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Appbar(
    popBack: () -> Unit,
) {

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = popBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(text = "Task Detail")
        }
    )

}

@Composable
private fun BottomAppbar(
    onDelete: () -> Unit
) {
    BottomAppBar {
        IconButton(
            onClick = onDelete
        ) {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete task")
        }
    }
}

@Composable
private fun FabButton(
    icon: ImageVector,
    onClick: () -> Unit = {},
) {

    N4EFabButton(
        icon = icon,
        onClick = onClick
    )

}

@Composable
private fun Content(
    modifier: Modifier,
    state: DetailTaskState,
    onEvent: (DetailTaskEvent) -> Unit
) {

    val scrollState = rememberScrollState()

    val labelStyle = MaterialTheme.typography.titleSmall.copy(
        color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = 0.7f
        )
    )

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .verticalScroll(
                state = scrollState
            )
    ) {
        Column {
            val label: String = "Task Title"
            Text(text = label, style = labelStyle)
            N4ETextField(
                value = state.task.title,
                placeholder = label,
                onEdit = { onEvent(DetailTaskEvent.TitleTaskChanged(it)) },
                isEditable = state.isEditing,
                errorMessage = state.taskTitleError,
                isError = state.taskTitleError.isNotEmpty()
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val label: String = "Creation Date"
            Text(text = label, style = labelStyle)
            val date = state.task.creationDate

            val dateString = "${date.dayOfMonth}" +
                    " ${
                        date.month.getDisplayName(
                            TextStyle.SHORT,
                            Locale("es", "es")
                        )
                    }" +
                    " ${date.year}" +
                    " at ${date.hour}:${date.minute}"

            InputChip(

                selected = false,
                onClick = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(text = dateString)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val label: String = "Due date"
            Text(text = label, style = labelStyle)
            val date = state.task.creationDate

            val dateString = "undefined"

            InputChip(
                selected = false,
                onClick = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(text = dateString)
                }
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column {
            val label: String = "Task Description"
            Text(text = label, style = labelStyle)
            N4ETextField(
                value = state.task.description,
                placeholder = label,
                onEdit = {
                    onEvent(DetailTaskEvent.TaskDescriptionChanged(it))
                },
                isEditable = state.isEditing
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column {
            val label: String = "Progress"
            Text(text = label, style = labelStyle)

            TaskProgressionIndicator(
                progression = state.task.progression,
                onChange = {
                    onEvent(DetailTaskEvent.TaskProgressionChanged(it))
                },
                enabled = state.isEditing
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Column {
            val label: String = "Subtasks"
            Text(text = label, style = labelStyle)

            if (state.isEditing) {
                N4ETextField(
                    placeholder = "Add subtask",
                    value = state.subtaskTitle,
                    onEdit = {
                        onEvent(DetailTaskEvent.TaskSubtaskTitleChanged(it))
                    },
                    trailingAction = {
                        onEvent(DetailTaskEvent.AddSubtask())
                    },
                    trailingIcon = Icons.Rounded.Add,

                    )
            }

            ListSubtasks(subtasks = state.task.subtasks)

        }

        Spacer(modifier = Modifier.height(15.dp))
    }


}

@Composable
fun TaskProgressionIndicator(
    progression: Float,
    onChange: (p: Float) -> Unit,
    enabled: Boolean = true
) {

    Slider(
        modifier = Modifier.fillMaxWidth(),
        value = progression,
        onValueChangeFinished = {},
        onValueChange = onChange,
        enabled = enabled
    )

}

@Composable
fun ListSubtasks(
    subtasks: List<Subtask>,
) {

    Column(
    ) {

        for (subtask in subtasks) {
            SubtaskDetailCard(subtask = subtask)
        }

    }
}

@Composable
fun SubtaskDetailCard(
    subtask: Subtask,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = subtask.title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onBackground.copy(
                    if (subtask.done) 0.5f else 1f
                )
            ),
        )

        Checkbox(
            checked = subtask.done,
            onCheckedChange = {},
        )

    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TaskDetailScreenPreview() {
    Notes4EveryoneTheme {
        TaskDetailScreen(
            state = DetailTaskState(),
            navigateBack = {},
            onSharedTasksEvent = {},
            onTaskDetailEvent = {}
        )
    }
}

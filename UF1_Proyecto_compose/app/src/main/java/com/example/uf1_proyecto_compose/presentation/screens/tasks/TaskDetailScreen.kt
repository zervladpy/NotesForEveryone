package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskDetailState
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskDetailViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun TaskDetailScreen(
    navController: NavController,
    viewModel: TaskDetailViewModel = hiltViewModel(),
    uid: String,
) {

    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var isEditing by remember { mutableStateOf(false) }

    fun popBack() {
        navController.navigate("tasks")
    }

    fun notify(message: String) {
        scope.launch {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppbar(
                popBack = { popBack() }
            )
        },
        content = {
            Content(
                modifier = Modifier.padding(it),
                state = state,
                viewModel = viewModel,
                isEditing = isEditing
            )
        },
        bottomBar = {
            BottomAppbar()
        },
        floatingActionButton = {
            FabButton(
                icon = if (isEditing) {
                    Icons.Rounded.Done
                } else Icons.Rounded.Edit,
                onClick = {

                    isEditing = if (isEditing) {
                        viewModel.update(
                            notify = { notify(it) }
                        )

                        false
                    } else {
                        viewModel.reset()
                        true
                    }

                }
            )
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppbar(
    popBack: () -> Unit,
) {

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { popBack() }
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

) {
    BottomAppBar {
        IconButton(
            onClick = {
                // delete
            }
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
    viewModel: TaskDetailViewModel,
    state: TaskDetailState,
    isEditing: Boolean,
) {

    val labelStyle = MaterialTheme.typography.titleSmall.copy(
        color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = 0.7f
        )
    )

    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Column {
            Column {
                val label: String = "Task Title"
                Text(text = label, style = labelStyle)
                N4ETextField(
                    value = state.task?.title ?: "",
                    placeholder = label,
                    onEdit = {
                        viewModel.setTitle(it)
                    },
                    isEditable = isEditing
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
                val date = state.task?.creationDate ?: LocalDateTime.now()

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

            Spacer(modifier = Modifier.height(15.dp))

            Column {
                val label: String = "Task Description"
                Text(text = label, style = labelStyle)
                N4ETextField(
                    value = state.task?.description ?: "",
                    placeholder = label,
                    onEdit = {
                        viewModel.setDescription(it)
                    },
                    isEditable = isEditing,
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column {
                val label: String = "Progress"
                Text(text = label, style = labelStyle)

                TaskProgressionIndicator(
                    progression = 0.1f,
                    onChange = {}
                )

            }

            Spacer(modifier = Modifier.height(15.dp))

            Column {
                val label: String = "Subtasks"
                Text(text = label, style = labelStyle)

                if (isEditing) {
                    N4ETextField(
                        placeholder = "Add subtask",
                        value = "",
                        onEdit = {},
                        trailingAction = {},
                        trailingIcon = Icons.Rounded.Add
                    )
                }

                ListSubtasks(subtasks = state.task?.subtasks ?: emptyList())

            }

            Spacer(modifier = Modifier.height(15.dp))
        }
    }

}

@Composable
fun TaskProgressionIndicator(
    progression: Float,
    onChange: (p: Float) -> Unit,
) {

    Slider(
        modifier = Modifier.fillMaxWidth(),
        value = progression,
        onValueChangeFinished = {},
        onValueChange = onChange,
        enabled = false
    )

}

@Composable
fun ListSubtasks(
    subtasks: List<Subtask>,
) {

    LazyColumn(
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {

        items(subtasks, key = { it.uid }) {

            SubtaskDetailCard(subtask = it)

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
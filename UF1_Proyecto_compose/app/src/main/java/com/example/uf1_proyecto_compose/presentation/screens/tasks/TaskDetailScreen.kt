package com.example.uf1_proyecto_compose.presentation.screens.tasks

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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskDetailState
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskDetailViewModel
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

    fun popBack() {
        navController.navigate("tasks")
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
                state = state
            )
        }
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
private fun Content(
    modifier: Modifier,
    state: TaskDetailState,
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
                    placeholder = label
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
                    placeholder = label
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

                N4ETextField(
                    placeholder = "Add subtask",
                    value = "",
                    onEdit = {},
                    trailingAction = {},
                    trailingIcon = Icons.Rounded.Add
                )

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
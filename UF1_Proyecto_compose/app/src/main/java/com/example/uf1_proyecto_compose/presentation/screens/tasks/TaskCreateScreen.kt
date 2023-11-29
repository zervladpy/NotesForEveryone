package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.screens.tasks.component.SubtaskCreatePreview
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskCreateViewModel
import java.util.UUID

@Composable
fun TaskCreateScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {


    Scaffold(
        topBar = {
            TaskCreateAppbar {
                navController.popBackStack()
            }
        },
        content = {
            TaskCreateContent(
                modifier.padding(it),
                navController,
            )
        },
    )
}

@Composable
private fun TaskCreateAppbar(
    onNavigationClick: () -> Unit,
) {

    CenteredAppbar(
        navIcon = Icons.AutoMirrored.Rounded.ArrowBack,
        onNavigationIconClick = onNavigationClick,
        title = "New Task"
    )

}

@Composable
private fun TaskCreateContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    createTaskViewModel: TaskCreateViewModel = hiltViewModel(),
) {

    val state = createTaskViewModel.state.value

    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    fun onTitleChanged(value: String) {
        title = value
        createTaskViewModel.checkTitle(title)
    }

    var description by remember { mutableStateOf("") }
    fun onDescriptionChanged(value: String) {
        description = value
    }

    var subtaskTitle by remember { mutableStateOf("") }

    fun onSubtaskTitleChanged(value: String) {
        subtaskTitle = value
    }

    fun resetSubtaskTitle() {
        subtaskTitle = ""
    }

    var subtasks by remember { mutableStateOf(emptyList<Subtask>()) }
    fun addSubtask(item: Subtask) {
        val helpSubtasks = mutableListOf<Subtask>();
        subtasks.forEach { helpSubtasks.add(it) }
        helpSubtasks.add(item)
        subtasks = helpSubtasks
    }

    fun removeSubtask(item: Subtask) {
        val helpSubtasks = subtasks.filter { it != item };
        subtasks = helpSubtasks
    }

    fun createSubtask() {
        if (subtaskTitle.isEmpty()) return
        val subtask = Subtask(
            uid = UUID.randomUUID().toString(),
            title = subtaskTitle,
            done = false
        )

        addSubtask(subtask)
        resetSubtaskTitle()
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            val taskTitleLabel = "Task Title"
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp),
                text = taskTitleLabel,
                style = MaterialTheme.typography.titleMedium
            )
            N4ETextField(
                modifier = Modifier.padding(),
                placeholder = taskTitleLabel,
                value = title,
                onEdit = { onTitleChanged(it) },
                isError = state.titleError.isNotEmpty(),
                errorMessage = state.titleError,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            val taskDescriptionLabel = "Task Description"
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp),
                text = taskDescriptionLabel,
                style = MaterialTheme.typography.titleMedium
            )
            N4ETextField(
                placeholder = taskDescriptionLabel,
                value = description,
                onEdit = { onDescriptionChanged(it) },
                maxLines = 3
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            val subtaskLabel = "Subtasks"

            N4ETextField(
                modifier = Modifier.padding(
                    top = 10.dp
                ),
                placeholder = "Add Subtask",
                value = subtaskTitle,
                onEdit = { onSubtaskTitleChanged(it) },
                trailingIcon = Icons.Rounded.Add,
                trailingAction = {
                    createSubtask()
                }
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp),
                text = subtaskLabel,
                style = MaterialTheme.typography.titleSmall
            )

            LazyColumn {
                // Show subtasks with a erase button
                items(subtasks) { subtask ->
                    SubtaskCreatePreview(
                        modifier = Modifier.padding(top = 10.dp),
                        subtask = subtask,
                        action = { removeSubtask(it) }
                    )
                }
            }
        }

        Spacer(modifier = modifier.weight(1f))

        // TODO( button goes out of the screen)

        N4EButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Create",
            onClick = {
                createTaskViewModel.createTask(
                    title = title,
                    description = description,
                    showState = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            enabled = state.titleError.isEmpty(),
        )
    }
}

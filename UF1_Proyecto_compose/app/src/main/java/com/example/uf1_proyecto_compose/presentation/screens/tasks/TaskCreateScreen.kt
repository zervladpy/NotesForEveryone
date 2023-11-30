package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.navigation.main.MainNavRoutes
import com.example.uf1_proyecto_compose.presentation.screens.tasks.component.SubtaskCreatePreview
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskCreateViewModel
import kotlinx.coroutines.launch


@Composable
fun TaskCreateScreen(
    navController: NavController,
    viewModel: TaskCreateViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    fun navigateBack() = navController.popBackStack()

    fun onSuccessNavigate(taskUid: String) {
        navController.navigate("${MainNavRoutes.TASK_SCREEN}s/${taskUid}")
    }

    fun onStateChangeMsg(msg: String? = "Unexpected Error") {
        scope.launch {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            Appbar(
                navigateBack = { navigateBack() }
            )
        },
        content = {
            Content(
                modifier = Modifier
                    .padding(it)
                    .padding(20.dp),
                viewModel = viewModel
            )
        },
        floatingActionButton = {
            N4EFabButton(
                icon = Icons.Rounded.Add,
                title = "Create Task",
                onClick = {
                    viewModel.createTask(
                        onComplete = { onSuccessNavigate(it) },
                        onStateChange = { onStateChangeMsg(it) }
                    )
                }
            )
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Appbar(
    navigateBack: () -> Unit,
) {

    TopAppBar(
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
    viewModel: TaskCreateViewModel,
) {

    val state = viewModel.state

    var titleSubtask by remember { mutableStateOf("") }


    Column(
        modifier = modifier
    ) {


        Column(
            Modifier.padding(vertical = 10.dp)
        ) {

            val label = "Task Title"

            Text(text = label, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(7.dp))
            N4ETextField(
                placeholder = label,
                value = state.value.title,
                onEdit = { viewModel.onTitleChange(it) }
            )
        }

        Column(
            Modifier.padding(vertical = 10.dp)
        ) {

            val label = "Task Description"

            Text(text = label, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(7.dp))
            N4ETextField(
                placeholder = label,
                value = state.value.description,
                onEdit = { viewModel.onDescriptionChange(it) }
            )
        }

        Column(
            Modifier.padding(vertical = 10.dp)
        ) {
            val label = "Subtasks"

            Text(text = label, style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(7.dp))
            N4ETextField(
                placeholder = "Add Subtask",
                value = titleSubtask,
                onEdit = { titleSubtask = it },
                trailingIcon = Icons.Rounded.Add,
                trailingAction = { viewModel.addSubtask(titleSubtask) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        ShowSubtasks(
            items = state.value.subtasks,
            removeItem = { viewModel.removeSubtask(it) }
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

@Composable
fun ShowSubtasksLazy(
    items: List<Subtask>,
    removeItem: (Subtask) -> Unit,
) {
    LazyColumn {
        items(items) {
            SubtaskCreatePreview(
                subtask = it,
                action = { removeItem(it) }
            )
        }
    }
}
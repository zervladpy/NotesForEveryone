package com.example.uf1_proyecto_compose.presentation.task_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.presentation.common.appbars.BottomTaskAppbar
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithLabel
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithPlaceHolder
import com.example.uf1_proyecto_compose.presentation.viewmodels.tasks.TasksViewModel
import kotlinx.coroutines.launch

@Composable
fun TaskScreen(
    tasksViewModel: TasksViewModel = hiltViewModel(),
    navController: NavController,
    taskUid: String? = null,
) {


    val task = tasksViewModel.state.value.tasks.find {
        it.uid == taskUid
    } ?: Task()

    var title: String by remember { mutableStateOf(task.title) }
    var description: String by remember { mutableStateOf(task.description) }
    var isEditable: Boolean by remember { mutableStateOf(taskUid == null) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    fun showSnackBar(message: String): Unit {


    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CenteredAppbar(
                navIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navDescription = "Navigate back",
                onNavigationIconClick = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(15.dp)
            ) {

                EditableTitle(
                    title = title,
                    onEdit = { it -> title = it },
                    isEditable = isEditable
                )

                EditableBody(
                    text = description,
                    onEdit = { it -> description = it },
                    isEditable = isEditable
                )
            }
        },

        bottomBar = {
            BottomTaskAppbar(
                fabIcon = if (isEditable) {
                    Icons.Rounded.Check
                } else {
                    Icons.Rounded.Create
                },
                onFabClick = {
                    tasksViewModel.insertTask(
                        title,
                        description
                    ) {
                        scope.launch {
                            snackbarHostState
                                .showSnackbar(
                                    message = it,
                                    duration = SnackbarDuration.Short,
                                    withDismissAction = true
                                )
                        }
                    }


                },
                actions = {
                    /// TODO (Delete)
                }
            )
        }
    )
}

@Composable
fun EditableTitle(
    title: String = "",
    onEdit: (String) -> Unit = {},
    isEditable: Boolean = false,
) {
    InputTextFieldWithLabel(
        modifier = Modifier.fillMaxWidth(),
        label = "Title",
        value = title,
        onEdit = onEdit,
        isError = title.isEmpty(),
        errorText = "* Required Field",
        isEditable = isEditable
    )
}

@Composable
fun EditableBody(
    text: String = "",
    onEdit: (String) -> Unit = {},
    isEditable: Boolean = false,
) {
    InputTextFieldWithPlaceHolder(
        modifier = Modifier.fillMaxSize(),
        placeholder = "Description",
        value = text,
        onEdit = onEdit,
        isEditable = isEditable
    )
}

@Composable
fun EndDateSelector() {

    /****
     * TODO (Add limit date selector)
     ****/

}

/**
 * Previews
 * */


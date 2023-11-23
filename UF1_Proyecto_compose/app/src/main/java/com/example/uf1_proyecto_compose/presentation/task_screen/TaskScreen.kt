package com.example.uf1_proyecto_compose.presentation.task_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.domain.models.Task
import com.example.uf1_proyecto_compose.presentation.common.appbars.BottomTaskAppbar
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithLabel
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithPlaceHolder
import com.example.uf1_proyecto_compose.presentation.viewmodels.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    tasksViewModel: TasksViewModel,
    navController: NavController,
    taskUid: String? = null
) {


    val task = tasksViewModel.tasks.find {
        it.uid == taskUid
    } ?: Task("", "", "", false)

    var title: String by remember { mutableStateOf(task.title) }
    var description: String by remember { mutableStateOf(task.description) }
    var isEditable: Boolean by remember { mutableStateOf(taskUid == null) }

    Scaffold(
        topBar = {
            CenteredAppbar(
                navIcon = Icons.Rounded.ArrowBack,
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

                Divider(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
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
                    isEditable = if (isEditable) {
                        if (title.isNotEmpty() && taskUid.isNullOrEmpty()) {
                            tasksViewModel.addTask(title, description)
                            false
                        } else {
                            tasksViewModel.updateTask(
                                task = task,
                                title = title,
                                description = description
                            )
                            false
                        }
                    } else true

                },
                actions = {
                    if (!taskUid.isNullOrEmpty()) {
                        IconButton(
                            onClick = {
                                tasksViewModel.deleteTask(task)
                                navController.popBackStack()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "Delete Task"
                            )
                        }
                    }
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

/**
 * Previews
 * */


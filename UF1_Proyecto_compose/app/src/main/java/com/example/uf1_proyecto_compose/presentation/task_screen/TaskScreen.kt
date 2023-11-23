package com.example.uf1_proyecto_compose.presentation.task_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.common.appbars.BottomTaskAppbar
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    taskViewModel: TaskViewModel,
) {

    Scaffold(
        topBar = {
            CenteredAppbar(
                navIcon = Icons.Rounded.ArrowBack,
                navDescription = "Navigate back",
                onNavigationIconClick = {}
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(15.dp)
            ) {

                EditableTitle(
                    title = "Task Title",
                    onEdit = {}
                )

                EditableBody(
                    text = "Super Good Description",
                    onEdit = {}
                )
            }
        },
        bottomBar = {
            BottomTaskAppbar(
                fabIcon = Icons.Rounded.Edit,
                onFabClick = {
                    /// TODO (Toggle edit mode)
                }
            )
        }
    )
}

@Composable
fun EditableTitle(
    title: String = "",
    isEditable: Boolean = false,
    onEdit: (String) -> Unit = {},
) {

    Box(modifier = Modifier) {
        if (!isEditable) {
            Text(
                modifier = Modifier,
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )
        } else {
            // TODO(Add Editable Text Field)

        }
    }
}

@Composable
fun EditableBody(
    text: String = "",
    isEditable: Boolean = false,
    onEdit: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier.padding(
            vertical = 40.dp
        )
    ) {
        if (isEditable) {
            ///
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

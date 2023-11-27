package com.example.uf1_proyecto_compose.presentation.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.presentation.screens.tasks.component.TaskDetailDescription
import com.example.uf1_proyecto_compose.presentation.screens.tasks.component.TaskDetailHeader
import com.example.uf1_proyecto_compose.presentation.screens.tasks.component.TaskProgressionIndicator
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskDetailState
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskDetailViewModel

@Composable
fun TaskDetailScreen(
    navController: NavController,
    viewModel: TaskDetailViewModel = hiltViewModel(),
    uid: String,
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TaskDetailAppbar(
                state = state,
                onNavigationClick = { navController.popBackStack() }
            )
        },
        content = {
            TaskDetailContent(
                Modifier.padding(it),
                state = state
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskDetailAppbar(
    onNavigationClick: () -> Unit,
    state: TaskDetailState
) {
    CenterAlignedTopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "go back"
                )
            }
        },
        title = {
            Text(text = "Task Detail")
        }
    )
}

@Composable
private fun TaskDetailContent(
    modifier: Modifier = Modifier,
    state: TaskDetailState
) {

    var progress by remember { mutableFloatStateOf(0f) }
    fun onProgressChange(value: Float) {
        progress = value
    }


    state.task?.let {
        Column(modifier) {

            // Task Title container
            TaskDetailHeader(task = it)

            Column(
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 20.dp
                )
            ) {
                TaskDetailDescription(task = it)

                TaskProgressionIndicator(
                    progress = progress,
                    onProgressChange = { onProgressChange(it) },
                )
            }

        }
    }

}

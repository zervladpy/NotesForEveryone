package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskDetailViewModel
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@Composable
fun TaskDetailScreen(
    navController: NavController,
    uid: String,
) {

    Scaffold(
        topBar = {
            TaskDetailAppbar {
                navController.popBackStack()
            }
        },
        content = {
            TaskDetailContent(
                Modifier.padding(it),
            )
        }
    )
}

@Composable
private fun TaskDetailAppbar(
    onNavigationClick: () -> Unit,
) {
    CenteredAppbar(
        navIcon = Icons.AutoMirrored.Rounded.ArrowBack,
        onNavigationIconClick = onNavigationClick
    )
}

@Composable
private fun TaskDetailContent(
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Column(modifier) {

        Log.d("TaskDetailScreen", state.task?.uid ?: "null")

        state.task?.let {
            Text(it.title)
        }

    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskDetailScreenPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TaskDetailScreen(
                navController = rememberNavController(),
                uid = "",
            )
        }
    }
}
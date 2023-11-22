package com.example.uf1_proyecto_compose.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.uf1_proyecto_compose.presentation.viewmodels.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tasks")
                }
            )
        }
    ) { it ->
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {

            LazyColumn {
                items(tasksViewModel.tasks) {
                    Text(text = it.title)
                    Text(text = it.description)

                }
            }

        }
    }
}

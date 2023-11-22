package com.example.uf1_proyecto_compose.presentation.home_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.presentation.common.FabButton
import com.example.uf1_proyecto_compose.presentation.navigation.NavDestinations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    navController: NavController,
) {
    Scaffold(
        modifier.background(color = MaterialTheme.colorScheme.background),
        content = {
            Surface(
                modifier = modifier
                    .padding(it),
            ) {
                TasksListView(
                    tasks = listOf(
                        Task("aa", "task 1", "task 1", false),
                        Task("aa", "task 1", "task 1", false),
                        Task("aa", "task 1", "task 1", false),
                        Task("aa", "task 1", "task 1", false),
                    )
                )
            }
        },
        floatingActionButton = {
            FabButton(icon = Icons.Rounded.Add, onClick = {
                scope.launch {
                    navController.navigate(NavDestinations.TaskDetails.route)
                }
            })
        }
    )
}

@Composable
fun TasksListView(
    tasks: List<Task>,
) {

    LazyColumn {
        items(tasks) {
            TaskPreview(it)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskPreview(
    task: Task,
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        onClick = {},
        shape = RoundedCornerShape(7.dp),
    ) {
        Text(
            modifier = Modifier.padding(7.dp),
            text = task.title
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = false)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        scope = rememberCoroutineScope(),
    )
}
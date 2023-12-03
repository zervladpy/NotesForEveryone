package com.example.uf1_proyecto_compose.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.cards.TaskPreviewCard
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksState
import java.time.LocalDateTime


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: SharedTasksState,
    onEvent: (SharedTasksEvent) -> Unit,
    navigateToTask: (String) -> Unit,
    navigateToCreateTask: () -> Unit,
    logout: () -> Unit
) {

    Scaffold(
        topBar = {
            Appbar(
                isLoading = state.isLoading,
                logout = logout
            )
        },
        content = {
            Content(
                modifier = modifier.padding(it),
                tasks = state.tasks,
                navigateToTask = navigateToTask
            )
        },
        floatingActionButton = {
            FabButton(
                navigateToCreateTask = navigateToCreateTask
            )
        },
        bottomBar = {
            BottomNavigation()
        }
    )
}

@Composable
private fun BottomNavigation() {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = "Home")
            }
        )
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Done,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = "History")
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = "Settings")
            }
        )
    }
}

@Composable
private fun FabButton(
    navigateToCreateTask: () -> Unit
) {

    N4EFabButton(icon = Icons.Rounded.Add, onClick = navigateToCreateTask)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Appbar(
    isLoading: Boolean = false,
    toggleDrawer: () -> Unit = {},
    logout: () -> Unit = {}
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Home")
            },
            actions = {
                IconButton(
                    onClick = logout
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ExitToApp,
                        contentDescription = "log out"
                    )
                }
            }
        )

        val date = LocalDateTime.now().toLocalDate()

        val month = date.month.toString()
        val year = date.year
        val day = date.dayOfMonth
        val dayOfWeek = date.dayOfWeek

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            OutlinedCard {

                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = "date"
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "$dayOfWeek", style = LocalTextStyle.current)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "$day", style = LocalTextStyle.current)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = month, style = LocalTextStyle.current)
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "$year", style = LocalTextStyle.current)
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            N4ETextField(
                value = "",
                onEdit = {},
                placeholder = "Search",
                trailingIcon = Icons.Rounded.Search,
                trailingAction = {}
            )
        }

        if (isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    navigateToTask: (String) -> Unit,
    tasks: List<Task> = emptyList()
) {

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {

        LazyColumn {
            items(tasks) {
                TaskPreviewCard(task = it, onClick = { task -> navigateToTask(task.uid) })
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    Notes4EveryoneTheme {
        HomeScreen(
            state = SharedTasksState(),
            onEvent = {},
            navigateToTask = {},
            logout = {},
            navigateToCreateTask = {}
        )
    }
}
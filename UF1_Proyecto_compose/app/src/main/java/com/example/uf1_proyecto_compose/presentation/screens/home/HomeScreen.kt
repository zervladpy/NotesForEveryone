package com.example.uf1_proyecto_compose.presentation.screens.home

import android.app.LocaleConfig
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EFabButton
import com.example.uf1_proyecto_compose.presentation.common.cards.TaskPreviewCard
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksState
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: SharedTasksState,
    onEvent: (SharedTasksEvent) -> Unit,
    navigateToTask: (String) -> Unit,
    navigateToCreateTask: () -> Unit,
    logout: () -> Unit,
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
            FabButton(navigateToCreateTask)
        }
    )
}


@Composable
private fun FabButton(
    navigateToCreateTask: () -> Unit,
) {
    N4EFabButton(icon = Icons.Rounded.Add, onClick = navigateToCreateTask)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Appbar(
    isLoading: Boolean = false,
    logout: () -> Unit = {},
) {
    Column {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.home_screen_title))
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

        Column(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 10.dp
                )
                .fillMaxWidth()
        ) {
            OutlinedCard {

                val date = LocalDateTime.now()

                val dateString = "${date.dayOfMonth}" +
                        " ${
                            date.month.getDisplayName(
                                TextStyle.SHORT,
                                Locale(LocaleConfig.TAG_LOCALE, LocaleConfig.TAG_LOCALE)
                            )
                        }" +
                        " ${date.year}"

                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = "date"
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = dateString)
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            N4ETextField(
                value = "",
                onEdit = {},
                placeholder = stringResource(id = R.string.search_placeholder),
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
    tasks: List<Task> = emptyList(),
) {

    Column(
        modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            )
    ) {

        LazyColumn {
            items(tasks) {
                if (!it.done) {
                    TaskPreviewCard(
                        modifier = Modifier.padding(bottom = 10.dp),
                        task = it, onClick = { task -> navigateToTask(task.uid) })
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
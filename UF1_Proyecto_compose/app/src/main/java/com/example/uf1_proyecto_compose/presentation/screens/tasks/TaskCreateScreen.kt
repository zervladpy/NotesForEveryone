package com.example.uf1_proyecto_compose.presentation.screens.tasks

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithLabel
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithPlaceHolder
import com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel.TaskCreateViewModel
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme
import kotlinx.coroutines.launch

@Composable
fun TaskCreateScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun sendStateChange(message: String) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
            )
        }
    }

    Scaffold(
        topBar = {
            TaskCreateAppbar {
                navController.popBackStack()
            }
        },
        content = {
            TaskCreateContent(
                modifier.padding(it),
                navController,
                sendStateChange = { msg -> sendStateChange(msg) }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    )
}

@Composable
private fun TaskCreateAppbar(
    onNavigationClick: () -> Unit,
) {

    CenteredAppbar(
        navIcon = Icons.AutoMirrored.Rounded.ArrowBack,
        onNavigationIconClick = onNavigationClick
    )

}

@Composable
private fun TaskCreateContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    sendStateChange: (String) -> Unit,
    createTaskViewModel: TaskCreateViewModel = hiltViewModel(),
) {


    val state = createTaskViewModel.state.value

    var title by remember { mutableStateOf("") }
    fun onTitleChanged(value: String) {
        title = value
        createTaskViewModel.checkTitle(title)
    }

    var description by remember { mutableStateOf("") }
    fun onDescriptionChanged(value: String) {
        description = value
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        InputTextFieldWithLabel(
            modifier = modifier.fillMaxWidth(),
            label = "Title",
            value = title,
            onEdit = { onTitleChanged(it) },
            errorText = state.titleError,
            isError = state.message.isNotEmpty()
        )

        InputTextFieldWithPlaceHolder(
            modifier = modifier.fillMaxWidth(),
            placeholder = "Description",
            value = description,
            onEdit = { onDescriptionChanged(it) },
        )

        N4EButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Create",
            onClick = {
                createTaskViewModel.createTask(
                    title = title,
                    description = description,
                    onSuccess = {
                        navController.popBackStack()
                    },
                    onStateChange = {
                        sendStateChange(it)
                    }
                )
            },
            enabled = state.titleError.isEmpty()
        )
    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TaskCreateScreenPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TaskCreateScreen(
                navController = rememberNavController()
            )
        }
    }
}
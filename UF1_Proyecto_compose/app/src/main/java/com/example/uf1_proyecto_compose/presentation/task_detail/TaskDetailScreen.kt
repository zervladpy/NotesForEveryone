package com.example.uf1_proyecto_compose.presentation.task_detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.common.NButton
import com.example.uf1_proyecto_compose.presentation.common.NTextButton
import com.example.uf1_proyecto_compose.presentation.common.NTextField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    navController: NavController,
) {


    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    Scaffold(
        content = {
            Column(
                modifier = modifier
                    .padding(it)
                    .padding(7.dp)
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Create a new Task",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(10.dp))
                NTextField(
                    label = "Title",
                    value = taskTitle,
                    onChange = { value ->
                        taskTitle = value
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                NTextField(
                    label = "Description",
                    value = taskDescription,
                    onChange = { value ->
                        taskDescription = value
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    NTextButton(label = "Cancel", onClick = {
                        scope.launch {
                            navController.popBackStack()
                        }
                    })
                    NButton(label = "Create", onClick = {})
                }

            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TaskDetailPreview() {
    TaskDetailScreen(
        scope = rememberCoroutineScope(),
        navController = rememberNavController()
    )
}
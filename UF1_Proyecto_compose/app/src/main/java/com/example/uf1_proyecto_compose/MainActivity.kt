package com.example.uf1_proyecto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.presentation.navigation.MainNavigation
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tasksViewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        tasksViewModel.onCreate()

        setContent {
            UF1_Proyecto_composeTheme {
                MainNavigation(tasksViewModel = tasksViewModel)
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true)
@Composable
fun TemplatePreview(
    modifier: Modifier = Modifier
) {
    UF1_Proyecto_composeTheme {
        Surface(
            modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}
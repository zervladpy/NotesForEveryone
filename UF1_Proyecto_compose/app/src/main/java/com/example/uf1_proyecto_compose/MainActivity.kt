package com.example.uf1_proyecto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.uf1_proyecto_compose.presentation.home_screen.HomeScreen
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val tasksViewModel: TasksViewModel by viewModels()
        tasksViewModel.onCrete()

        setContent {
            UF1_Proyecto_composeTheme {
                HomeScreen(tasksViewModel = tasksViewModel)
            }
        }
    }
}
package com.example.uf1_proyecto_compose.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.uf1_proyecto_compose.app_navigation.Router
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Notes4EveryoneTheme { Router() }
        }
    }
}
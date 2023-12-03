package com.example.uf1_proyecto_compose.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.example.uf1_proyecto_compose.app_navigation.router.Router
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                Color.Transparent.hashCode(), Color.Transparent.hashCode()
            ),
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.hashCode(), Color.Transparent.hashCode()
            ),
        )
        
        setContent {
            Notes4EveryoneTheme {
                Router()
            }
        }
    }
}
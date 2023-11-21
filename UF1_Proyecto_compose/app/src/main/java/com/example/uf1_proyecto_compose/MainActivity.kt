package com.example.uf1_proyecto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.data.task.repositories.local.TaskLocalRepo
import com.example.uf1_proyecto_compose.navigation.AppNavigation
import com.example.uf1_proyecto_compose.ui.theme.UF1_Proyecto_composeTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val file = File(filesDir, "notes4everyone.sqlite3")

        val localRepo = TaskLocalRepo.getInstance()
        localRepo.file = file
        val localConnection = localRepo.connection

        setContent {
            UF1_Proyecto_composeTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    AppNavigation()
}

@Preview
@Composable
fun MainContentPreview() {
    MainContent()
}
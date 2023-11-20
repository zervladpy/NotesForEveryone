package com.example.uf1_proyecto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.componets.DrawerBody
import com.example.uf1_proyecto_compose.componets.DrawerHeader
import com.example.uf1_proyecto_compose.navigation.AppNavigation
import com.example.uf1_proyecto_compose.ui.theme.UF1_Proyecto_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UF1_Proyecto_composeTheme {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerHeader()
            DrawerBody(items = listOf(), onItemClick = {}
            )
        }) {
        AppNavigation()
    }
}

@Preview
@Composable
fun MyAppPreview() {
    AppNavigation()
}

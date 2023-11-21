package com.example.uf1_proyecto_compose.componets.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppbar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    title: String? = null,
) {

    TopAppBar(
        title = {
            if (title != null) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "open drawer")
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, group = "App Bar")
@Composable
fun MainAppbarPreviewWithTitle() {
    MainAppbar(
        scope = rememberCoroutineScope(),
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        title = "Home"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, group = "App Bar")
@Composable
fun MainAppbarPreviewNoTitle() {
    MainAppbar(
        scope = rememberCoroutineScope(),
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        title = null
    )
}
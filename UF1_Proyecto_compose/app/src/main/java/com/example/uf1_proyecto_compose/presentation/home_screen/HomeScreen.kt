package com.example.uf1_proyecto_compose.presentation.home_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.buttons.FabButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenteredAppbar(
                title = "Home"
            )
        },
        content = {
            Column(
                modifier = Modifier.padding(it)
            ) {
                // TODO("Show Task List")
            }
        },
        floatingActionButton = {
            FabButton(
                icon = Icons.Rounded.Add,
                title = "Create Task",
                onClick = {
                    // TODO("Add Create Task Screen")
                }
            )
        }
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES, name = "Dark")
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
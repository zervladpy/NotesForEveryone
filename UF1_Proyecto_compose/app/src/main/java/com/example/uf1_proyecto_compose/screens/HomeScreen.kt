package com.example.uf1_proyecto_compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.componets.SearchBar
import com.example.uf1_proyecto_compose.componets.TaskProgressionCard


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()

    Scaffold(

        modifier = modifier,

        topBar = { TopHomeAppBar() },
        content = { HomeContent(modifier = modifier.padding(it)) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHomeAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer")
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notifications"
                )
            }

        }
    )
}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        /// Search for tasks
        SearchBar()
        /// Rest content
        Column(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            TaskProgressionCard()
            /// Urgent Tasks
        }
    }
}
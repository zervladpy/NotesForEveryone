package com.example.uf1_proyecto_compose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { CalendarTopAppBar() },
        content = { CalendarContent(modifier = modifier.padding(it)) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTopAppBar(modifier: Modifier = Modifier) {
    MediumTopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer")
            }
        },
        title = {
            Text(text = "Calendar Screen")
        })
}

@Composable
fun CalendarContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Calendar Screen")
    }
}
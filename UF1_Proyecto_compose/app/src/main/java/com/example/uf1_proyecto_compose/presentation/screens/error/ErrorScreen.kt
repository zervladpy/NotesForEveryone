package com.example.uf1_proyecto_compose.presentation.screens.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {

    Scaffold(
        topBar = { AppBar(navigateBack) },
        content = { Content(modifier = modifier.padding(it)) }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    navigateBack: () -> Unit = {},
) {

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "navigate back"
                )
            }
        },
        title = {  }
    )

}

@Composable
private fun Content(
    modifier: Modifier = Modifier,

    ) {

    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = stringResource(id = R.string.error_not_found))

    }

}
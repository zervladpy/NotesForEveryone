package com.example.uf1_proyecto_compose.presentation.calendar_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
) {
    Scaffold(
        topBar = { CalendarTopAppbar(navClick = openDrawer) },
        content = { CalendarContent(modifier = modifier.padding(it)) }
    )
}

@Composable
fun CalendarTopAppbar(
    navClick: () -> Unit,
) = CenteredAppbar(
    title = "Calendar",
    onNavigationIconClick = navClick,
)

@Composable
fun CalendarContent(
    modifier: Modifier = Modifier,
) {
}

package com.example.uf1_proyecto_compose.presentation.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.screens.home.viewmodel.HomeViewModel
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    openDrawer: () -> Unit = {},
) {
    Scaffold(
        topBar = { HomeAppbar(openDrawer = openDrawer) },
        content = { HomeContent(modifier.padding(it)) }
    )
}

@Composable
private fun HomeAppbar(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
) {
    CenteredAppbar(
        onNavigationIconClick = openDrawer
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value

    Column(
        modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        /// TODO(Total Task Progression indicator)

    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                navController = rememberNavController()
            )
        }
    }
}
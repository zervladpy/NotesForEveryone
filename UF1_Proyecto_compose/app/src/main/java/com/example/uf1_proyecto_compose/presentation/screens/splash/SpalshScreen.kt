package com.example.uf1_proyecto_compose.presentation.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.common.texts.AppTitle
import com.example.uf1_proyecto_compose.presentation.navigation.app.AppNavigation
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.AuthViewModel
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    val state = authViewModel.state

    LaunchedEffect(key1 = true) {
        delay(1000L)
        navController.popBackStack()
        if (state.value.user == null) {
            navController.navigate(AppNavigation.AuthScreen.route)
        } else {
            navController.navigate(AppNavigation.HomeScreen.route)
        }
    }


    Scaffold(
        content = { Content(Modifier.padding(it)) }
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        AppTitle()
    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LandingScreenPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            SplashScreen(
                navController = rememberNavController()
            )
        }
    }
}
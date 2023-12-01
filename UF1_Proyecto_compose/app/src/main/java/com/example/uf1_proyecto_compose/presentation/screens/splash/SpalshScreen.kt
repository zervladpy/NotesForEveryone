package com.example.uf1_proyecto_compose.presentation.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.common.texts.AppTitle
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.AuthViewModel

@Composable
fun SplashScreen(
    authState: AuthViewModel,
    navigateHome: () -> Unit,
    navigateToLanding: () -> Unit,
) {

    val state = authState.state

    LaunchedEffect(key1 = state.value.isLoading) {

        if (!state.value.isLoading) {
            if (state.value.isAuthenticated) {
                navigateHome()
            } else {
                navigateToLanding()
            }
        }

    }

    Scaffold(
        content = {
            Content(
                modifier = Modifier.padding(it)
            )
        }
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        AppTitle()
    }

}

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
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth.AuthState
import com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks.SharedTasksState

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    authState: AuthState,
    sharedTasksState: SharedTasksState,
    navigateHome: () -> Unit,
    navigateToLanding: () -> Unit,
) {

    LaunchedEffect(key1 = authState.isLoading, key2 = sharedTasksState.isLoading) {

        if (!authState.isLoading) {
            if (authState.isAuthenticated) {
                if (!sharedTasksState.isLoading) {
                    navigateHome()
                }
            } else {
                navigateToLanding()
            }
        }

    }

    Scaffold(
        content = {
            Content(
                modifier = modifier.padding(it)
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

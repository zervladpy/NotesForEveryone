package com.example.uf1_proyecto_compose.presentation.screens.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.app_navigation.auth_router.AuthRouter
import com.example.uf1_proyecto_compose.presentation.common.texts.AppTitle
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth.AuthEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth.AuthState

@Composable
fun AuthScreen(
    modifier: Modifier,
    authState: AuthState,
    onAuthEvent: (AuthEvent) -> Unit,
    navigateToMain: () -> Unit,
) {

    LaunchedEffect(key1 = authState.isAuthenticated) {
        if (authState.isAuthenticated) {
            navigateToMain()
        }
    }

    Scaffold(
        content = {
            AppTitle()
            AuthRouter(
                modifier = modifier.padding(it),
                onAuthEvent = onAuthEvent,
            )
        }
    )

}

@Preview
@Preview
@Composable
private fun Preview() {

    Notes4EveryoneTheme {
        AuthScreen(
            modifier = Modifier,
            authState = AuthState(),
            onAuthEvent = {},
            navigateToMain = {}
        )
    }

}
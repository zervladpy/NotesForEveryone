package com.example.uf1_proyecto_compose.presentation.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton


@Composable
fun LandingScreen(
    navController: NavController
) {
    Scaffold(
        content = { LandingContent(Modifier.padding(it), navController) }
    )
}

@Composable
private fun LandingContent(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    /**
     * TODO (Create Screen)
     * */

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        N4EButton(
            text = "Go to Login",
            onClick = {
                navController.navigate("login")
            }
        )

        N4EButton(
            text = "Go to Register",
            onClick = {navController.navigate("register")}
        )
    }

}

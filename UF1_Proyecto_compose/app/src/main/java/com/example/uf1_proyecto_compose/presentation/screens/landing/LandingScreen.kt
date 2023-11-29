package com.example.uf1_proyecto_compose.presentation.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EOutlinedButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4ETextButton
import com.example.uf1_proyecto_compose.presentation.common.texts.AppTitle
import com.example.uf1_proyecto_compose.presentation.navigation.auth.AuthNavRoutes


@Composable
fun LandingScreen(
    navController: NavController,
) {
    Scaffold(
        content = { LandingContent(Modifier.padding(it), navController) }
    )
}

@Composable
private fun LandingContent(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.weight(1f))

        AppTitle()

        Spacer(modifier = Modifier.weight(1f))

        N4EOutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = "Sign Up",
            onClick = {
                navController.navigate(AuthNavRoutes.REGISTER_SCREEN)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        N4EButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = "Log in",
            onClick = {
                navController.navigate(AuthNavRoutes.LOGIN_SCREEN)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            N4ETextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                text = "Sign In with Google",
                onClick = {

                },
                enabled = false
            )
            Spacer(modifier = Modifier.width(10.dp))
            N4ETextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                text = "Sign In with Github",
                onClick = {

                },
                enabled = false
            )
        }
    }

}

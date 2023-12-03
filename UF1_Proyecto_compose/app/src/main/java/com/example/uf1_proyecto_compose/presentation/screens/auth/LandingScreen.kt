package com.example.uf1_proyecto_compose.presentation.screens.auth

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EOutlinedButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4ETextButton
import com.example.uf1_proyecto_compose.presentation.common.texts.AppTitle


@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    Scaffold(
        content = {
            Content(
                modifier = modifier.padding(it),
                navigateToRegister = navigateToRegister,
                navigateToLogin = navigateToLogin,
            )
        }
    )

}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit = {},
    navigateToRegister: () -> Unit = {},
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
            text = stringResource(id = R.string.sign_up_button_label),
            onClick = navigateToRegister
        )

        Spacer(modifier = Modifier.height(20.dp))

        N4EButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = stringResource(id = R.string.login_button_label),
            onClick = navigateToLogin
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            N4ETextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                text = stringResource(id = R.string.login_with_google_button_label),
                onClick = {

                },
                enabled = false
            )
            Spacer(modifier = Modifier.width(10.dp))
            N4ETextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                text = stringResource(id = R.string.login_with_github_button_label),
                onClick = {

                },
                enabled = false
            )
        }
    }
}

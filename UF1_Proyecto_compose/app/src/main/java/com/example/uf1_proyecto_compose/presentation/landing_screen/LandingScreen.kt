package com.example.uf1_proyecto_compose.presentation.landing_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EOutlinedButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EProviderButton
import com.example.uf1_proyecto_compose.presentation.navigation.auth.AuthNavRoutes
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@Composable
fun LandingScreen(
    navController: NavController
) {

    fun goToLoginScreen() {
        navController.navigate(AuthNavRoutes.LOGIN_SCREEN)
    }

    fun goToRegisterScreen() {
        navController.navigate(AuthNavRoutes.REGISTER_SCREEN)
    }

    Scaffold(
        content = {
            LandingContent(
                paddingValues = it,
                goToLoginScreen = { goToLoginScreen() },
                goToRegisterScreen = { goToRegisterScreen() }
            )
        }
    )

}

@Composable
fun LandingContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    goToLoginScreen: () -> Unit = {},
    goToRegisterScreen: () -> Unit = {},
) {

    Column(modifier = modifier
        .padding(paddingValues)
        .padding(20.dp)) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Notes4",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Everyone",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.weight(1f))

        N4EOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Register",
            onClick = goToRegisterScreen
        )
        N4EButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Login",
            onClick = goToLoginScreen
        )

        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier.weight(1f)
            )
            Text(
                modifier = Modifier.padding(horizontal = 7.dp),
                text = "or login with",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.5f
                    )
                )
            )
            HorizontalDivider(
                modifier.weight(1f)
            )
        }

        Row(
            modifier = modifier.fillMaxWidth(),
            Arrangement.SpaceBetween
        ) {

            N4EProviderButton(
                Modifier.weight(1f),
                text = "Google",
                enabled = false,
                onClick = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.width(10.dp))
            N4EProviderButton(
                Modifier.weight(1f),
                text = "No account",
                enabled = true,
                onClick = { /*TODO*/ }
            )
        }


    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LandingScreenPreview(
    modifier: Modifier = Modifier
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = modifier.padding(10.dp)) {

                LandingScreen(
                    navController = rememberNavController()
                )
            }
        }
    }
}
package com.example.uf1_proyecto_compose.presentation.screens.landing

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme


@Composable
fun LandingScreen() {
    Scaffold(
        content = { LandingContent(Modifier.padding(it)) }
    )
}

@Composable
fun LandingContent(
    modifier: Modifier = Modifier,
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
            onClick = { /*TODO*/ }
        )

        N4EButton(
            text = "Go to Register",
            onClick = { /*TODO*/ }
        )
    }

}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LandingScreenPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            LandingScreen()
        }
    }
}
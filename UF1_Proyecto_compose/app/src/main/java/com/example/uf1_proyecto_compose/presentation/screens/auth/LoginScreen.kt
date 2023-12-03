package com.example.uf1_proyecto_compose.presentation.screens.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4ETextButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.ui.theme.Notes4EveryoneTheme
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth.AuthEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.login.LoginEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.login.LoginState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginState: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onAuthEvent: (AuthEvent) -> Unit,
    navigateBack: () -> Unit,
) {

    Scaffold(
        topBar = { Appbar(navigateBack) },
        content = {
            Content(
                modifier.padding(it),
                loginState = loginState,
                onEvent = onEvent,
                onAuthEvent = onAuthEvent
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Appbar(
    navigateBack: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "pop back"
                )
            }
        },
        title = { Text(text = stringResource(id = R.string.login_screen_title)) }
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    loginState: LoginState = LoginState(),
    onEvent: (LoginEvent) -> Unit,
    onAuthEvent: (AuthEvent) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        N4ETextField(
            modifier = Modifier.padding(
                bottom = 30.dp
            ),
            placeholder = stringResource(id = R.string.email_placeholder),
            value = loginState.email,
            onEdit = { onEvent(LoginEvent.EmailChanged(it)) },
            leadingIcon = Icons.Rounded.Email,
            isError = loginState.emailError.isNotEmpty(),
            errorMessage = loginState.emailError,
        )

        var isPasswordVisible by remember { mutableStateOf(true) }

        N4ETextField(
            modifier = Modifier.padding(
                bottom = 40.dp
            ),
            placeholder = stringResource(id = R.string.password_placeholder),
            value = loginState.password,
            onEdit = { onEvent(LoginEvent.PasswordChanged(it)) },
            leadingIcon = Icons.Rounded.Lock,
            isError = loginState.passwordError.isNotEmpty(),
            errorMessage = loginState.passwordError,
            isPassword = isPasswordVisible,
            trailingIcon = if (isPasswordVisible) {
                Icons.Rounded.VisibilityOff
            } else Icons.Rounded.Visibility,
            trailingAction = {
                isPasswordVisible = !isPasswordVisible
            }
        )

        N4EButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = stringResource(id = R.string.login_button_label),
            onClick = {
                onAuthEvent(
                    AuthEvent.Login(
                        loginState.email,
                        loginState.password
                    )
                )
            },
            enabled = loginState.emailError.isEmpty()
                    && loginState.passwordError.isEmpty()
                    && !loginState.isLoading
                    && loginState.email.isNotEmpty()
                    && loginState.password.isNotEmpty()
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

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreview() {
    Notes4EveryoneTheme {
        LoginScreen(
            loginState = LoginState(),
            onEvent = {},
            navigateBack = {},
            onAuthEvent = {}
        )
    }
}
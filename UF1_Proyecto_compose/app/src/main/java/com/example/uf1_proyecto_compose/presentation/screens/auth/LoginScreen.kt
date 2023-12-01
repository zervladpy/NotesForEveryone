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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4ETextButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.common.texts.AppTitle
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.AuthViewModel
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.login.LoginEvent
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.login.LoginState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    navigateBack: () -> Unit,
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit,
) {

    LaunchedEffect(key1 = viewModel.state.value.isAuthenticated) {
        if (viewModel.state.value.isAuthenticated) {
            navigateToHome()
        }
    }

    Scaffold(
        topBar = { Appbar(navigateBack) },
        content = {
            Content(modifier.padding(it),
                loginState = viewModel.loginState.value,
                eventHandler = { event -> viewModel.handleLoginEvent(event) }
            )
        }
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
        title = {}
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    loginState: LoginState = LoginState(),
    eventHandler: (LoginEvent) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))

        AppTitle()

        Spacer(modifier = Modifier.weight(1f))

        N4ETextField(
            modifier = Modifier.padding(
                bottom = 30.dp
            ),
            placeholder = "Email",
            value = loginState.email,
            onEdit = { eventHandler(LoginEvent.EmailChanged(it)) },
            leadingIcon = Icons.Rounded.Email,
            isError = loginState.emailError.isNotEmpty(),
            errorMessage = loginState.emailError,
        )

        var isPasswordVisible by remember { mutableStateOf(true) }

        N4ETextField(
            modifier = Modifier.padding(
                bottom = 40.dp
            ),
            placeholder = "Password",
            value = loginState.password,
            onEdit = { eventHandler(LoginEvent.PasswordChanged(it)) },
            leadingIcon = Icons.Rounded.Lock,
            isError = loginState.passwordError.isNotEmpty(),
            errorMessage = loginState.passwordError,
            isPassword = isPasswordVisible,
            trailingIcon = Icons.Rounded.Info,
            trailingAction = {
                isPasswordVisible = !isPasswordVisible
            }
        )

        N4EButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = "Log in",
            onClick = {
                eventHandler(LoginEvent.Submit)
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
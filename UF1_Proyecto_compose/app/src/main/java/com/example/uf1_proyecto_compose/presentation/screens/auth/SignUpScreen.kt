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
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.AuthViewModel
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.signup.SignupEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.signup.SignupState

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {

    LaunchedEffect(
        key1 = viewModel.state.value.isAuthenticated
    ) {

        if (viewModel.state.value.isAuthenticated) {
            navigateToHome()
        }

    }

    Scaffold(
        topBar = {
            Appbar(navigateBack)
        },
        content = {
            Content(
                modifier = modifier.padding(it),
                state = viewModel.signupState.value,
                handleEvent = { event ->
                    viewModel.handleRegisterEvent(event)
                },
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
    state: SignupState,
    handleEvent: (SignupEvent) -> Unit,
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
            value = state.email,
            onEdit = { SignupEvent.EmailChanged(it) },
            leadingIcon = Icons.Rounded.Email,
            isError = state.emailError.isNotEmpty(),
            errorMessage = state.emailError,
        )

        var isPasswordVisible by remember { mutableStateOf(true) }

        N4ETextField(
            modifier = Modifier.padding(
                bottom = 30.dp
            ),
            placeholder = "Password",
            value = state.password,
            onEdit = { SignupEvent.PasswordChanged(it) },
            leadingIcon = Icons.Rounded.Lock,
            isError = state.passwordError.isNotEmpty(),
            errorMessage = state.passwordError,
            isPassword = isPasswordVisible,
            trailingIcon = Icons.Rounded.Info,
            trailingAction = {
                isPasswordVisible = !isPasswordVisible
            }
        )

        var isRepeatPasswordVisible by remember { mutableStateOf(true) }

        N4ETextField(
            modifier = Modifier.padding(
                bottom = 40.dp
            ),
            placeholder = "Repeat Password",
            value = state.repeatPassword,
            onEdit = { SignupEvent.RepeatPasswordChanged(it) },
            leadingIcon = Icons.Rounded.Lock,
            isError = state.repeatPasswordError.isNotEmpty(),
            errorMessage = state.repeatPasswordError,
            isPassword = isPasswordVisible,
            trailingIcon = Icons.Rounded.Info,
            trailingAction = {
                isRepeatPasswordVisible = !isRepeatPasswordVisible
            }
        )

        N4EButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = "Log in",
            onClick = { SignupEvent.Submit },
            enabled = state.emailError.isEmpty()
                    && state.passwordError.isEmpty()
                    && state.repeatPasswordError.isEmpty()
                    && !state.isLoading
                    && state.email.isNotEmpty()
                    && state.password.isNotEmpty()
                    && state.repeatPassword.isNotEmpty()
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
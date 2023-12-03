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
import androidx.compose.ui.unit.dp
import com.example.uf1_proyecto_compose.R
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4ETextButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth.AuthEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.signup.SignUpEvent
import com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.signup.SignupState

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    signupState: SignupState,
    onEvent: (SignUpEvent) -> Unit,
    onAuthEvent: (AuthEvent) -> Unit,
    navigateBack: () -> Unit,
) {

    Scaffold(
        topBar = {
            Appbar(navigateBack)
        },
        content = {
            Content(
                modifier = modifier.padding(it),
                state = signupState,
                onEvent = onEvent,
                onAuthEvent = onAuthEvent
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
        title = {
            Text(
                text = stringResource(
                    id = R.string.signup_screen_title
                )
            )
        }
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: SignupState,
    onEvent: (SignUpEvent) -> Unit,
    onAuthEvent: (AuthEvent) -> Unit
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        N4ETextField(
            modifier = Modifier.padding(
                bottom = 30.dp
            ),
            placeholder = stringResource(id = R.string.email_placeholder),
            value = state.email,
            onEdit = {
                onEvent(SignUpEvent.EmailChanged(it))
            },
            leadingIcon = Icons.Rounded.Email,
            isError = state.emailError.isNotEmpty(),
            errorMessage = state.emailError,
        )

        var isPasswordVisible by remember { mutableStateOf(true) }

        N4ETextField(
            modifier = Modifier.padding(
                bottom = 30.dp
            ),
            placeholder = stringResource(id = R.string.password_placeholder),
            value = state.password,
            onEdit = { onEvent(SignUpEvent.PasswordChanged(it)) },
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
            placeholder = stringResource(id = R.string.repeat_password_placeholder),
            value = state.repeatPassword,
            onEdit = { onEvent(SignUpEvent.RepeatPasswordChanged(it)) },
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
            text = stringResource(id = R.string.sign_up_button_label),
            onClick = {
                onAuthEvent(
                    AuthEvent.SignUp(
                        state.email,
                        state.password
                    )
                )
            },
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
package com.example.uf1_proyecto_compose.presentation.screens.auth

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithLabel
import com.example.uf1_proyecto_compose.presentation.screens.auth.viewmodel.SignUpViewModel
import com.example.uf1_proyecto_compose.presentation.ui.theme.UF1_Proyecto_composeTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
) {

    Scaffold(
        content = { SignUpContent(modifier.padding(it)) }
    )

}

@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
) {

    val state = viewModel.state.value

    var email by remember { mutableStateOf("") }
    fun onEmailChanged(value: String) {
        email = value
        viewModel.checkEmail(email)
    }

    var password by remember { mutableStateOf("") }
    fun onPasswordChanged(value: String) {
        password = value
        viewModel.checkPassword(password)
    }

    var repeatPassword by remember { mutableStateOf("") }
    fun onRepeatPasswordChanged(value: String) {
        repeatPassword = value
        viewModel.checkRepeatPassword(password, repeatPassword)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputTextFieldWithLabel(
            label = "Email",
            value = email,
            onEdit = {
                onEmailChanged(it)
            },
            errorText = state.emailError,
            isError = state.emailError.isNotEmpty()
        )

        InputTextFieldWithLabel(
            label = "Password",
            value = password,
            onEdit = {
                onPasswordChanged(it)
            },
            errorText = state.passwordError,
            isError = state.passwordError.isNotEmpty()
        )

        InputTextFieldWithLabel(
            label = "Repeat Password",
            value = email,
            onEdit = {
                onRepeatPasswordChanged(it)
            },
            errorText = state.repeatPasswordError,
            isError = state.repeatPasswordError.isNotEmpty()
        )

        val enabledSignUpButton: Boolean =
            state.emailError.isEmpty() &&
                    state.passwordError.isEmpty() &&
                    state.repeatPasswordError.isEmpty() &&
                    !state.isLoading

        N4EButton(
            text = "Sign Up",
            onClick = {
                viewModel.register(email, password, repeatPassword)
            },
            enabled = enabledSignUpButton
        )

    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SignUpPreview(
    modifier: Modifier = Modifier,
) {
    UF1_Proyecto_composeTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            SignUpScreen()
        }
    }
}
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4ETextButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.N4ETextField
import com.example.uf1_proyecto_compose.presentation.common.texts.AppTitle
import com.example.uf1_proyecto_compose.presentation.screens.auth.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    Scaffold(
        topBar = { SignupTopAppbar(navController = navController) },
        content = { SignUpContent(modifier.padding(it), navController) }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignupTopAppbar(
    navController: NavController,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
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
fun SignUpContent(
    modifier: Modifier = Modifier,
    navController: NavController,
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
            value = email,
            onEdit = { onEmailChanged(it) },
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
            value = password,
            onEdit = { onPasswordChanged(it) },
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
            value = repeatPassword,
            onEdit = { onRepeatPasswordChanged(it) },
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
            onClick = { viewModel.register(email, password, repeatPassword) },
            enabled = state.emailError.isEmpty()
                    && state.passwordError.isEmpty()
                    && state.repeatPasswordError.isEmpty()
                    && !state.isLoading
                    && email.isNotEmpty()
                    && password.isNotEmpty()
                    && repeatPassword.isNotEmpty()
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
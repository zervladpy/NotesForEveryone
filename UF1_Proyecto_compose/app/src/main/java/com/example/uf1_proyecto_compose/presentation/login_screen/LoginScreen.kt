package com.example.uf1_proyecto_compose.presentation.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uf1_proyecto_compose.presentation.common.appbars.CenteredAppbar
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4ETextButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithLabel
import com.example.uf1_proyecto_compose.presentation.viewmodels.auth.auth.AuthViewModel
import com.example.uf1_proyecto_compose.presentation.viewmodels.auth.login.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CenteredAppbar(
                navIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            )
        },
        content = {
            LoginContent(
                paddingValues = it,
                viewModel = viewModel,
                authViewModel = authViewModel
            )
        }
    )
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: LoginViewModel,
    authViewModel: AuthViewModel
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .padding(paddingValues)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextFieldWithLabel(
            label = "Email",
            value = email,
            onEdit = { email = it }
        )
        InputTextFieldWithLabel(
            label = "Password",
            value = password,
            onEdit = { password = it }
        )

        N4EButton(text = "Login", onClick = {
            viewModel.loginWithEmailAndPassword(email, password)
            authViewModel.refresh()
        })
        N4ETextButton(text = "I don't have account", onClick = {
            // TODO(Navigate to register screen)
        })
    }
}
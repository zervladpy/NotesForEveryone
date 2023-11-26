package com.example.uf1_proyecto_compose.presentation.register_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
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
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4EButton
import com.example.uf1_proyecto_compose.presentation.common.buttons.N4ETextButton
import com.example.uf1_proyecto_compose.presentation.common.inputs.InputTextFieldWithLabel
import com.example.uf1_proyecto_compose.presentation.viewmodels.auth.auth.AuthViewModel
import com.example.uf1_proyecto_compose.presentation.viewmodels.auth.register.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
) {
    Scaffold(
        content = { RegisterContent(paddingValues = it, viewModel = viewModel) }
    )
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: RegisterViewModel,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }


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
        InputTextFieldWithLabel(
            label = "Repeat Password",
            value = repeatPassword,
            onEdit = { repeatPassword = it }
        )
        N4EButton(text = "Register", onClick = {
            viewModel.registerWithEmailAndPassword(email, password, repeatPassword)
            authViewModel.refresh()
        })
        N4ETextButton(text = "I have account", onClick = { /*TODO*/ })
    }
}
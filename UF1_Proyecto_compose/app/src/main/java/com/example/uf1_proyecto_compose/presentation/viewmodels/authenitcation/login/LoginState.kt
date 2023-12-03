package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.login

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val error: String = "",
)

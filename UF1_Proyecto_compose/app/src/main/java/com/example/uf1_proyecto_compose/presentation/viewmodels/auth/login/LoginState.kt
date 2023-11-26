package com.example.uf1_proyecto_compose.presentation.viewmodels.auth.login

data class LoginState(
    val isLoading: Boolean = false,
    val emailErrorMessage: String = "",
    val passwordErrorMessage: String = "",
)

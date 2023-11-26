package com.example.uf1_proyecto_compose.presentation.viewmodels.auth.register

data class RegisterState(
    val isLoading: Boolean = false,
    val displayNameErrorMessage: String = "",
    val emailErrorMessage: String = "",
    val passwordErrorMessage: String = "",
    val repeatPasswordErrorMessage: String = "",
    val message: String = "",
)

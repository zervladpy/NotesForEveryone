package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.signup

data class SignupState(
    val isLoading: Boolean = false,
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String = "",
    val repeatPassword: String = "",
    val repeatPasswordError: String = "",
    val error: String = "",
    val isPasswordVisible: Boolean = false,
)

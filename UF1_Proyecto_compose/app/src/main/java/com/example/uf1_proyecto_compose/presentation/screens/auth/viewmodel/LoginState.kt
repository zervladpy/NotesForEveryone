package com.example.uf1_proyecto_compose.presentation.screens.auth.viewmodel

data class LoginState(
    val isLoading: Boolean = false,
    val emailError: String = "",
    val passwordError: String = "",
    val message: String = "",
)

package com.example.uf1_proyecto_compose.presentation.screens.auth.viewmodel

data class SignUpState(
    val isLoading: Boolean = false,
    val emailError: String = "",
    val passwordError: String = "",
    val repeatPasswordError: String = "",
    val message: String = "",
)

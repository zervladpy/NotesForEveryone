package com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.signup

sealed class SignupEvent(val value: String? = null) {
    class EmailChanged(email: String) : SignupEvent(email)
    class PasswordChanged(password: String) : SignupEvent(password)
    class RepeatPasswordChanged(repeatPassword: String) : SignupEvent(repeatPassword)
    object Submit : SignupEvent()
}

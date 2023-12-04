package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.login

sealed class LoginEvent(val value: String? = null) {
    class EmailChanged(val email: String) : LoginEvent(email)
    class PasswordChanged(val password: String) : LoginEvent(password)
    class SwitchVisibility : LoginEvent()
}

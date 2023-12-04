package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.signup

sealed class SignUpEvent(val value: String? = null) {
    class EmailChanged(email: String) : SignUpEvent(email)
    class PasswordChanged(password: String) : SignUpEvent(password)
    class RepeatPasswordChanged(repeatPassword: String) : SignUpEvent(repeatPassword)
    class SwitchVisibility : SignUpEvent()
}

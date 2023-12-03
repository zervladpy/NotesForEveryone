package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth

sealed class AuthEvent(val email: String? = null, val password: String? = null) {

    class Login(email: String, password: String) : AuthEvent(email, password)
    class SignUp(email: String, password: String) : AuthEvent(email, password)
    class LogOut : AuthEvent()

}
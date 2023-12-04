package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onEvent(event: LoginEvent) {

        when (event) {
            is LoginEvent.EmailChanged -> emailChanged(event.value!!)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value!!)
            is LoginEvent.SwitchVisibility -> switchVisibility()
        }
    }

    private fun emailChanged(value: String) {

        val error: String = checkEmail(value)

        _state.value = state.value.copy(
            email = value,
            emailError = error
        )

    }

    private fun passwordChanged(value: String) {
        val error: String = checkPassword(value)

        _state.value = state.value.copy(
            password = value,
            passwordError = error
        )
    }

    private fun switchVisibility() {
        _state.value = state.value.copy(
            isPasswordVisible = !state.value.isPasswordVisible
        )
    }

    private fun checkEmail(
        value: String,
    ): String {

        return if (!value.contains("@")) {
            "Bad Email Format"
        } else if (value.isEmpty()) {
            "* Required Field"
        } else {
            ""
        }
    }

    private fun checkPassword(
        value: String,
    ): String {

        return if (value.isEmpty()) {
            "* Required Field"
        } else {
            ""
        }
    }


}
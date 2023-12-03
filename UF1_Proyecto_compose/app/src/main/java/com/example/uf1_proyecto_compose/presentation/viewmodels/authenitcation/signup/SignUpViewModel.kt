package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _state = mutableStateOf(SignupState())
    val state: State<SignupState> = _state

    fun onEvent(event: SignUpEvent) {

        when (event) {
            is SignUpEvent.EmailChanged -> emailChanged(event.value!!)
            is SignUpEvent.PasswordChanged -> passwordChanged(event.value!!)
            is SignUpEvent.RepeatPasswordChanged -> repeatPasswordChanged(
                event.value!!,
                state.value.password
            )

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

    private fun repeatPasswordChanged(p: String, p1: String) {
        val error: String = checkRepeatPassword(p, p1)

        _state.value = state.value.copy(
            repeatPassword = p,
            repeatPasswordError = error
        )
    }

    private fun checkEmail(
        value: String
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
        value: String
    ): String {

        return if (value.isEmpty()) {
            "* Required Field"
        } else {
            ""
        }
    }

    private fun checkRepeatPassword(
        value: String,
        value2: String
    ): String {

        return if (value.isEmpty()) {
            "* Required Field"
        } else if (value != value2) {
            "Password don't match"
        } else {
            ""
        }
    }

}
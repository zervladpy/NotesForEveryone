package com.example.uf1_proyecto_compose.presentation.screens.auth.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.auth.LoginAnonymously
import com.example.uf1_proyecto_compose.domain.use_case.auth.LoginWithEmailAndPassword
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val loginWithEmailAndPassword: LoginWithEmailAndPassword,
    private val loginAnonymously: LoginAnonymously,
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    /**
     * Login method with
     * @param email
     * @param password
     * */
    fun login(email: String, password: String) {

        if (state.value.isLoading) return

        checkEmail(email)
        checkPassword(password)

        loginWithEmailAndPassword(email, password).onEach { response ->
            when (response) {
                is Response.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }

                is Response.Success -> {
                    _state.value = LoginState(isLoading = false)
                }

                is Response.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        message = response.message ?: "Unexpected Error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Login anonymously
     * */
    fun login() {
        loginAnonymously().onEach { response ->
            when (response) {
                is Response.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }

                is Response.Success -> {}

                is Response.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun checkEmail(email: String) {

        if (email.isEmpty()) {
            _state.value = state.value.copy(
                emailError = "* Required field"
            )
        } else if (!email.contains("@")) {
            _state.value = state.value.copy(
                emailError = "* Email is badly formatted"
            )
        } else {
            _state.value = state.value.copy(
                emailError = ""
            )
        }


    }

    fun checkPassword(password: String) {

        if (password.isEmpty()) {
            _state.value = state.value.copy(
                passwordError = "* Required field"
            )
        } else {
            _state.value = state.value.copy(
                passwordError = ""
            )
        }

    }

}
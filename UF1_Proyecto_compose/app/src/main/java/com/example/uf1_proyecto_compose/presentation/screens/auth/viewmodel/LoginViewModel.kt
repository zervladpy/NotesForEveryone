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
        loginWithEmailAndPassword(email, password).onEach { response ->
            when (response) {
                is Response.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }

                is Response.Success -> {}

                is Response.Error -> {}
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

    fun checkEmail(email: String) {}

    fun checkPassword(password: String) {}

}
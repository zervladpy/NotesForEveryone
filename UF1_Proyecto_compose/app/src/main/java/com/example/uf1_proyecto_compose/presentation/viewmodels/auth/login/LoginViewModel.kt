package com.example.uf1_proyecto_compose.presentation.viewmodels.auth.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.auth.LoginAnonymously
import com.example.uf1_proyecto_compose.domain.use_case.auth.LoginWithEmailAndPassword
import com.example.uf1_proyecto_compose.domain.use_case.auth.LoginWithProvider
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val loginAnonymouslyUseCase: LoginAnonymously,
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPassword,
    private val loginWithProviderUseCase: LoginWithProvider,
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun loginWithEmailAndPassword(email: String, password: String) {

        if (_state.value.isLoading) return

        loginWithEmailAndPasswordUseCase(email, password).onEach { result ->
            when (result) {
                is Response.Success -> {
                    _state.value = LoginState()
                }

                is Response.Error -> {
                    _state.value = LoginState()
                }

                is Response.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

    }

    fun loginAnonymously() {
        if (_state.value.isLoading) return

        loginAnonymouslyUseCase().onEach { result ->
            when (result) {
                is Response.Success -> {
                    _state.value = LoginState()
                }

                is Response.Error -> {
                    _state.value = LoginState()
                }

                is Response.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}
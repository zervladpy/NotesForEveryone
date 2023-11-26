package com.example.uf1_proyecto_compose.presentation.viewmodels.auth.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetCurrentUserUseCase
import com.example.uf1_proyecto_compose.domain.use_case.auth.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    init {
        _state.value = AuthState(isLoading = true)

        if (getCurrentUserUseCase() != null) {
            _state.value = AuthState(isAuthenticated = true)
        } else {
            _state.value = AuthState()
        }
    }

    fun logout() {

        logoutUseCase()

        _state.value = AuthState()

    }

    fun refresh() {
        _state.value = AuthState(isLoading = true)

        if (getCurrentUserUseCase() != null) {
            _state.value = AuthState(isAuthenticated = true)
        } else {
            _state.value = AuthState()
        }
    }

}
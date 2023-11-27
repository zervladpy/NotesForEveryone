package com.example.uf1_proyecto_compose.presentation.screens.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    init {
        _state.value = AuthState(getCurrentUserUseCase() != null)
    }

    fun refreshAuthState() {
        _state.value = AuthState(getCurrentUserUseCase() != null)
    }

}
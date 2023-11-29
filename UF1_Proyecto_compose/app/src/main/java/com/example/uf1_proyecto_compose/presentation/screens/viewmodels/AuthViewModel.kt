package com.example.uf1_proyecto_compose.presentation.screens.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetCurrentUserUseCase
import com.example.uf1_proyecto_compose.utils.AuthState
import com.example.uf1_proyecto_compose.utils.AuthState.Authenticated
import com.example.uf1_proyecto_compose.utils.AuthState.UnAuthenticated
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<AuthState>(UnAuthenticated())
    val state: State<AuthState> = _state

    fun changeAuthState(user: User?) {
        if (user != null) {
            _state.value = Authenticated(user)
        } else {
            _state.value = UnAuthenticated()
        }
    }

}
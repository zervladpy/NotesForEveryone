package com.example.uf1_proyecto_compose.presentation.viewmodels.auth.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.auth.RegisterWithEmailAndPassword
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject constructor(
    private val registerWithEmailAndPasswordUseCase: RegisterWithEmailAndPassword,
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    init {

    }

    fun registerWithEmailAndPassword(
        email: String,
        password: String,
        repeatPassword: String,
    ) {

        if (_state.value.isLoading) return

        if (password != repeatPassword) {
            RegisterState(repeatPasswordErrorMessage = "Passwords don't match")
        }

        registerWithEmailAndPasswordUseCase(email, password).onEach { result ->


            when (result) {
                is Response.Error -> {
                    RegisterState(message = result.message ?: "Unexpected Error")
                }

                is Response.Success -> {
                    RegisterState(message = "Logged in")

                }

                is Response.Loading -> {
                    RegisterState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

    }


}
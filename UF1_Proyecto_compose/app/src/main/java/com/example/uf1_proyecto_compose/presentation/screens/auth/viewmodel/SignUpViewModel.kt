package com.example.uf1_proyecto_compose.presentation.screens.auth.viewmodel

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
class SignUpViewModel
@Inject constructor(
    private val registerWithEmailAndPassword: RegisterWithEmailAndPassword,
) : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    fun register(email: String, password: String, repeatPassword: String) {

        if (state.value.isLoading) return

        registerWithEmailAndPassword(email, password).onEach { response ->
            when (response) {
                is Response.Loading -> {
                    _state.value = SignUpState(isLoading = true)
                }

                is Response.Success -> {}

                is Response.Error -> {}
            }
        }.launchIn(viewModelScope)

    }

    fun checkEmail(value: String) {}
    fun checkPassword(value: String) {}
    fun checkRepeatPassword(passwd: String, repeatPasswd: String) {}

}
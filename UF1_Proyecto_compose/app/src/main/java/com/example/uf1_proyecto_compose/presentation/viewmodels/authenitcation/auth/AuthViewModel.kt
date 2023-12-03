package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetAuthState
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetCurrentUserUseCase
import com.example.uf1_proyecto_compose.domain.use_case.auth.LoginWithEmailAndPassword
import com.example.uf1_proyecto_compose.domain.use_case.auth.LogoutUseCase
import com.example.uf1_proyecto_compose.domain.use_case.auth.RegisterWithEmailAndPassword
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val getAuthState: GetAuthState,
    private val loginUseCase: LoginWithEmailAndPassword,
    private val signupUseCase: RegisterWithEmailAndPassword,
    private val logoutUseCase: LogoutUseCase,
    private val getUser: GetCurrentUserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    init {
        _state.value = state.value.copy(
            isLoading = true
        )

        checkAuthState()

        _state.value = state.value.copy(
            isLoading = false
        )
    }

    private fun checkAuthState() {
        _state.value = state.value.copy(
            isAuthenticated = getAuthState(),
            user = getUser()
        )
    }


    fun onEvent(event: AuthEvent) {

        Log.d("OnAuth Event", event.toString())

        when (event) {
            is AuthEvent.Login -> login(event.email!!, event.password!!)
            is AuthEvent.SignUp -> signup(event.email!!, event.password!!)
            is AuthEvent.LogOut -> logout()
        }

    }

    private fun login(email: String, password: String) {

        loginUseCase(email, password).onEach {

            when (it) {
                is Response.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        isAuthenticated = false,
                    )
                }

                is Response.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                    )
                }

                is Response.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        isAuthenticated = true
                    )
                }
            }

        }.launchIn(viewModelScope)

    }

    private fun signup(email: String, password: String) {
        signupUseCase(email, password).onEach {

            when (it) {
                is Response.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        isAuthenticated = false
                    )
                }

                is Response.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                    )
                }

                is Response.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        isAuthenticated = true
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun logout() {
        logoutUseCase().onEach {
            when (it) {
                is Response.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }

                is Response.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }


                is Response.Success -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                        isAuthenticated = false,
                        user = getUser()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


}
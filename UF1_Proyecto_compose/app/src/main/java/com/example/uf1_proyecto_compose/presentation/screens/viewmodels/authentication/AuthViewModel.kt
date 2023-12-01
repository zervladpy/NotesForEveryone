package com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetAuthState
import com.example.uf1_proyecto_compose.domain.use_case.auth.LoginWithEmailAndPassword
import com.example.uf1_proyecto_compose.domain.use_case.auth.RegisterWithEmailAndPassword
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.login.LoginEvent
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.login.LoginState
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.signup.SignupEvent
import com.example.uf1_proyecto_compose.presentation.screens.viewmodels.authentication.signup.SignupState
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val getAuthState: GetAuthState,
    private val loginUsecase: LoginWithEmailAndPassword,
    private val signupUsecase: RegisterWithEmailAndPassword,
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _signupState = mutableStateOf(SignupState())
    val signupState: State<SignupState> = _signupState

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
            isAuthenticated = getAuthState()
        )
    }

    fun handleLoginEvent(event: LoginEvent) {

        val currentLoginState = loginState.value

        fun emailChanged(value: String) {
            val error = validateEmail(value)

            _loginState.value = currentLoginState.copy(
                email = value,
                emailError = error,
            )

        }

        fun passwordChanged(value: String) {
            _loginState.value = currentLoginState.copy(
                password = value,
            )
        }

        fun submit() {
            if (loginState.value.isLoading) return

            val isError: Boolean = loginState.value.emailError.isNotEmpty()
                    || loginState.value.passwordError.isNotEmpty()

            if (isError) return

            loginUsecase(
                currentLoginState.email,
                currentLoginState.password
            ).onEach {
                when (it) {
                    is Response.Loading -> _loginState.value = currentLoginState.copy(
                        isLoading = true
                    )

                    is Response.Error -> _loginState.value = currentLoginState.copy(
                        isLoading = false,
                        error = it.message ?: "Unexpected Error"
                    )

                    is Response.Success -> {
                        _loginState.value = currentLoginState.copy(
                            isLoading = false,
                        )
                        checkAuthState()
                    }
                }
            }.launchIn(viewModelScope)
        }

        when (event) {
            is LoginEvent.EmailChanged -> emailChanged(event.value!!)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value!!)
            is LoginEvent.Submit -> submit()
        }

    }

    fun handleRegisterEvent(event: SignupEvent) {

        val currentSignupState = signupState.value

        fun emailChanged(value: String) {
            val error = validateEmail(value)

            _signupState.value = currentSignupState.copy(
                email = value,
                emailError = error,
            )

        }

        fun passwordChanged(value: String) {
            val error = validatePassword(value)

            _signupState.value = currentSignupState.copy(
                password = value,
                passwordError = error,
            )
        }

        fun repeatPasswordChanged(value: String) {

            val error = validateRepeatPassword(value, signupState.value.password)

            _signupState.value = currentSignupState.copy(
                repeatPassword = value,
                repeatPasswordError = error,
            )

        }

        fun submit() {
            if (signupState.value.isLoading) return

            signupUsecase(
                currentSignupState.email,
                currentSignupState.password
            ).onEach {
                when (it) {
                    is Response.Loading -> {
                        _signupState.value = currentSignupState.copy(
                            isLoading = true
                        )
                    }

                    is Response.Error -> {
                        _signupState.value = currentSignupState.copy(
                            isLoading = false,
                            error = it.message ?: "Unexpected Error"
                        )
                    }

                    is Response.Success -> {
                        _signupState.value = currentSignupState.copy(
                            isLoading = false,
                        )
                        checkAuthState()
                    }
                }
            }.launchIn(viewModelScope)

        }

        when (event) {
            is SignupEvent.EmailChanged -> emailChanged(event.value!!)
            is SignupEvent.PasswordChanged -> passwordChanged(event.value!!)
            is SignupEvent.RepeatPasswordChanged -> repeatPasswordChanged(event.value!!)
            is SignupEvent.Submit -> submit()
        }
    }

    private fun validateEmail(value: String): String {

        if (!value.contains("@")) {
            return "Email badly formatted"
        }

        return ""
    }

    private fun validatePassword(value: String): String {

        if (value.isEmpty()) {
            return "Password is Required"
        }

        if (value.length < 5) {
            return "Password min length of 5 is required"
        }

        return ""
    }

    private fun validateRepeatPassword(value: String, value2: String): String {

        if (value != value2) {
            return "Passwords don`t match"
        }
        return ""
    }
}
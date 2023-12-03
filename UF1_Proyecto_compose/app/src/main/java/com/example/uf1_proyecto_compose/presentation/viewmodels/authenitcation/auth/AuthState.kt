package com.example.uf1_proyecto_compose.presentation.viewmodels.authenitcation.auth

import com.example.uf1_proyecto_compose.domain.model.User

data class AuthState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val user: User = User()
)
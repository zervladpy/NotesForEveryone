package com.example.uf1_proyecto_compose.utils

import com.example.uf1_proyecto_compose.domain.model.User

sealed class AuthState(val user: User?) {

    class Authenticated(user: User) : AuthState(user = user)
    class UnAuthenticated() : AuthState(user = null)

}

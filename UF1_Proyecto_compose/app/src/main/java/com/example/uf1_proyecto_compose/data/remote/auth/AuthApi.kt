package com.example.uf1_proyecto_compose.data.remote.auth

import com.example.uf1_proyecto_compose.data.remote.dto.UserDto

interface AuthApi {
    fun isAuthenticated(): Boolean
    fun getCurrentUser(): UserDto
    suspend fun loginWithEmailAndPassword(email: String, password: String)
    suspend fun registerWithEmailAndPassword(email: String, password: String)
    suspend fun logout()
}
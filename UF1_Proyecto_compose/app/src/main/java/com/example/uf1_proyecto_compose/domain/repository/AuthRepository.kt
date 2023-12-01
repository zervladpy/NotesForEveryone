package com.example.uf1_proyecto_compose.domain.repository

interface AuthRepository {
    fun isAuthenticated(): Boolean
    suspend fun loginWithEmailAndPassword(email: String, password: String)
    suspend fun registerWithEmailAndPassword(email: String, password: String)
    suspend fun logout()
}
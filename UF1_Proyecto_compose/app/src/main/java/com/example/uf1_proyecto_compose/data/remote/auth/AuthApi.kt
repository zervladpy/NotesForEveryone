package com.example.uf1_proyecto_compose.data.remote.auth

import com.example.uf1_proyecto_compose.domain.model.User

interface AuthApi {

    val user: User?

    suspend fun loginAnonymously()

    suspend fun loginWithEmailAndPassword(email: String, password: String)

    suspend fun loginWithProvider()

    suspend fun registerWithEmailAndPassword(email: String, password: String)

    suspend fun logout()

}
package com.example.uf1_proyecto_compose.data.remote.auth

import com.example.uf1_proyecto_compose.data.remote.dto.UserDto

interface AuthApi {

    val currentUser: UserDto?

    suspend fun loginAnonymously()

    suspend fun loginWithEmailAndPassword(email: String, password: String)

    suspend fun loginWithProvider()

    suspend fun registerWithEmailAndPassword(email: String, password: String)

    suspend fun logout()

}
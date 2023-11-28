package com.example.uf1_proyecto_compose.domain.repository

import com.example.uf1_proyecto_compose.domain.model.User

interface AuthRepository {

    val currentUser: User?

    suspend fun loginAnonymously()

    suspend fun loginWithEmailAndPassword(email: String, password: String)

    suspend fun loginWithProvider()

    suspend fun registerWithEmailAndPassword(email: String, password: String)

    suspend fun logout()

    suspend fun addUserToLocal(user: User)

}
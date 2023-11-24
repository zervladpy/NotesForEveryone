package com.example.uf1_proyecto_compose.data.database.repository.auth

interface AuthApiClient {

    suspend fun loginAnonymously(): UserModel

    suspend fun loginWithEmailAndPassword(
        email: String,
        password: String,
    ): UserModel

    suspend fun registerWithEmailAndPassword(
        email: String,
        password: String,
    ): UserModel

    /**
     * TODO( Provider Authentication )
     */

}
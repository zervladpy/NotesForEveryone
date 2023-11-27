package com.example.uf1_proyecto_compose.data.repository

import com.example.uf1_proyecto_compose.data.remote.auth.AuthApi
import com.example.uf1_proyecto_compose.data.remote.dto.toDomain
import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val api: AuthApi,
) : AuthRepository {

    override val currentUser: User?
        get() = api.currentUser?.toDomain()

    override suspend fun loginAnonymously() {
        api.loginAnonymously()
    }

    override suspend fun loginWithEmailAndPassword(email: String, password: String) {
        api.loginWithEmailAndPassword(email, password)
    }

    override suspend fun loginWithProvider() {
        api.loginWithProvider()
    }

    override suspend fun registerWithEmailAndPassword(email: String, password: String) {
        api.registerWithEmailAndPassword(email, password)
    }

    override suspend fun logout() {
        api.logout()
    }
    
}
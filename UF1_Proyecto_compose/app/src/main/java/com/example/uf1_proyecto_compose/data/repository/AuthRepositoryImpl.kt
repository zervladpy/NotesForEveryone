package com.example.uf1_proyecto_compose.data.repository

import com.example.uf1_proyecto_compose.data.local.dao.AppDao
import com.example.uf1_proyecto_compose.data.remote.auth.AuthApi
import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.domain.model.toEntity
import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
    private val api: AuthApi,
    private val dao: AppDao,
) : AuthRepository {

    override val user: User? = api.user

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

    override suspend fun addUserToLocal(user: User) {
        dao.insertUser(user.toEntity())
    }

}
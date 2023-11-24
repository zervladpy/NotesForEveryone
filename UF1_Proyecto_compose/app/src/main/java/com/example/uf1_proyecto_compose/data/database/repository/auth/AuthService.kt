package com.example.uf1_proyecto_compose.data.database.repository.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthService
@Inject constructor(
    private val auth: FirebaseAuth,
) : AuthApiClient {
    override suspend fun loginAnonymously(): UserModel {
        withContext(Dispatchers.IO) {
            auth.signInAnonymously().await()
        }

    }

    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String,
    ): UserModel {
        withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).await()
        }
    }

    override suspend fun registerWithEmailAndPassword(
        email: String,
        password: String,
    ): UserModel {
        withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).await()

        }
    }
}

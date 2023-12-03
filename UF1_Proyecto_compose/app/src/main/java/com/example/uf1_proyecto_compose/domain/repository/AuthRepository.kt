package com.example.uf1_proyecto_compose.domain.repository

import android.net.Uri
import com.example.uf1_proyecto_compose.domain.model.User

interface AuthRepository {
    fun isAuthenticated(): Boolean
    fun getCurrentUser(): User
    suspend fun loginWithEmailAndPassword(email: String, password: String)
    suspend fun registerWithEmailAndPassword(email: String, password: String)
    suspend fun logout()

    suspend fun updateProfile(displayName: String, photoUri: Uri)
}
package com.example.uf1_proyecto_compose.domain.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.example.uf1_proyecto_compose.domain.model.User

interface AuthRepository {

    val _currentUser: MutableState<User?>
    val currentUser: State<User?>

    suspend fun loginAnonymously()

    suspend fun loginWithEmailAndPassword(email: String, password: String)

    suspend fun loginWithProvider()

    suspend fun registerWithEmailAndPassword(email: String, password: String)

    suspend fun logout()

    suspend fun addUserToLocal(user: User)

}
package com.example.uf1_proyecto_compose.data.remote.auth

import com.example.uf1_proyecto_compose.data.remote.dto.UserDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthFirebaseApi @Inject constructor(
    private val api: FirebaseAuth
) : AuthApi {

    override var currentUser: UserDto? = null
        get() = api.currentUser?.toDto()
        private set

    override suspend fun loginAnonymously() {

        val result = api.signInAnonymously().await().user

        currentUser = result?.toDto()

    }

    override suspend fun loginWithEmailAndPassword(email: String, password: String) {

        val result = api.signInWithEmailAndPassword(email, password).await().user

        currentUser = result?.toDto()
    }

    /**
     * @throws NotImplementedError Not yet implemented
     * */
    override suspend fun loginWithProvider() {
        throw NotImplementedError()
    }

    override suspend fun registerWithEmailAndPassword(email: String, password: String) {

        val result = api.createUserWithEmailAndPassword(email, password).await().user

        currentUser = result?.toDto()
    }

    override suspend fun logout() {
        api.signOut()
        currentUser = null
    }
}

fun FirebaseUser.toDto(): UserDto {
    return UserDto(
        uid = uid,
        displayName = displayName ?: "",
    )
}
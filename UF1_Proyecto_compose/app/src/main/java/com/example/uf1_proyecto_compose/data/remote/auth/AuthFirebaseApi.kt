package com.example.uf1_proyecto_compose.data.remote.auth

import com.example.uf1_proyecto_compose.data.remote.dto.UserDto
import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.domain.model.toDomain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * On init adds AuthStateListener
 * */
class AuthFirebaseApi @Inject constructor(
    private val api: FirebaseAuth,
) : AuthApi {

    override val user: User?
        get() = api.currentUser?.toDomain()

    override suspend fun loginAnonymously() {
        api.signInAnonymously().await().user
    }

    override suspend fun loginWithEmailAndPassword(email: String, password: String) {
        api.signInWithEmailAndPassword(email, password).await().user
    }

    /**
     * @throws NotImplementedError Not yet implemented
     * */
    override suspend fun loginWithProvider() {
        throw NotImplementedError()


    }

    override suspend fun registerWithEmailAndPassword(email: String, password: String) {
        val user = api.createUserWithEmailAndPassword(email, password).await().user

    }

    override suspend fun logout() {
        api.signOut()
    }

}

fun FirebaseUser.toDto(): UserDto {
    return UserDto(
        uid = uid,
        displayName = displayName ?: "",
    )
}
package com.example.uf1_proyecto_compose.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 *
 * */
class AuthFirebaseApi @Inject constructor(
    private val api: FirebaseAuth,
) : AuthApi {

    /**
     * @return if user is authenticated
     * */
    override fun isAuthenticated() = api.currentUser != null

    /**
     *
     * */
    override suspend fun loginWithEmailAndPassword(email: String, password: String) {
        api.signInWithEmailAndPassword(email, password).await()
    }

    /**
     *
     * */
    override suspend fun registerWithEmailAndPassword(email: String, password: String) {
        api.createUserWithEmailAndPassword(email, password).await()

    }

    /**
     *
     * */
    override suspend fun logout() {
        api.signOut()
    }
}

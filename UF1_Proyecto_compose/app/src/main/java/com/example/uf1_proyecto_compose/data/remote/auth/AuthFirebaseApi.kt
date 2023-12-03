package com.example.uf1_proyecto_compose.data.remote.auth

import android.net.Uri
import com.example.uf1_proyecto_compose.data.remote.dto.UserDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 *
 * */
class AuthFirebaseApi @Inject constructor(
    private val api: FirebaseAuth,
    private val storage: FirebaseStorage,
) : AuthApi {

    /**
     * @return if user is authenticated
     * */
    override fun isAuthenticated() = api.currentUser != null

    /**
     *
     * */
    override fun getCurrentUser(): UserDto {
        return api.currentUser?.toDto() ?: UserDto(
            uid = "",
            email = "",
            displayName = "",
            photoUrl = Uri.EMPTY
        )
    }

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

    /**
     *
     * */
    override suspend fun updateProfile(
        displayName: String,
        profilePhoto: Uri
    ) {

        val ref = storage.reference.child("profile_pictures").child(api.currentUser!!.uid)

        val photoUri = ref.putFile(profilePhoto).await()

        val updatedProfile =
            UserProfileChangeRequest.Builder().setDisplayName(displayName)
                .setPhotoUri(photoUri.uploadSessionUri)
                .build()

        api.currentUser?.updateProfile(updatedProfile)
    }
}

fun FirebaseUser.toDto(): UserDto {
    return UserDto(
        uid = uid,
        displayName = displayName,
        email = email,
        photoUrl = photoUrl
    )
}

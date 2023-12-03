package com.example.uf1_proyecto_compose.domain.model

import android.net.Uri
import com.example.uf1_proyecto_compose.data.local.entity.UserEntity
import com.google.firebase.auth.FirebaseUser

data class User(
    var uid: String = "",
    var email: String = "",
    val displayName: String = "",
    val photoUrl: Uri = Uri.EMPTY,
)

fun FirebaseUser.toDomain() = User(
    uid = uid,
    email = email ?: "",
    displayName = displayName ?: "",
    photoUrl = photoUrl ?: Uri.EMPTY
)

fun User.toEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        email = email,
        displayName = displayName
    )
}
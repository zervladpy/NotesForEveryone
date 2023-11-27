package com.example.uf1_proyecto_compose.domain.model

import com.example.uf1_proyecto_compose.data.local.entity.UserEntity
import com.google.firebase.auth.FirebaseUser

data class User(
    var uid: String = "",
    val displayName: String = "",
    var isAuthenticated: Boolean = false
) {

}

fun FirebaseUser.toDomain(isAuthenticated: Boolean = false) = User(
    uid = uid,
    displayName = displayName ?: "",
    isAuthenticated = isAuthenticated
)

fun User.toEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        displayName = displayName
    )
}
package com.example.uf1_proyecto_compose.data.remote.dto

import android.net.Uri
import com.example.uf1_proyecto_compose.domain.model.User

data class UserDto(
    val uid: String,
    val displayName: String?,
    val email: String?,
    val photoUrl: Uri?,
)

fun UserDto.toDomain(): User {
    return User(
        uid = uid,
        displayName = displayName ?: "",
        email = email ?: "",
        photoUrl = photoUrl ?: Uri.EMPTY
    )
}
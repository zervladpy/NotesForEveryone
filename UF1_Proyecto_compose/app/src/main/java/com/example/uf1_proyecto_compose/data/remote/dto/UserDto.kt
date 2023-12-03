package com.example.uf1_proyecto_compose.data.remote.dto

import com.example.uf1_proyecto_compose.domain.model.User

data class UserDto(
    val uid: String,
    val displayName: String?,
    val email: String?,
)

fun UserDto.toDomain(): User {
    return User(
        uid = uid,
        displayName = displayName ?: "",
    )
}
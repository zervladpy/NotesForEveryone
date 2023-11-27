package com.example.uf1_proyecto_compose.data.remote.dto

import com.example.uf1_proyecto_compose.data.local.entity.UserEntity
import com.example.uf1_proyecto_compose.domain.model.User

data class UserDto(
    val uid: String = "",
    val displayName: String = "",
    val isAuthenticated: Boolean = false
)

fun UserDto.toDomain(): User {
    return User(
        uid = uid,
        displayName = displayName
    )
}

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        displayName = displayName
    )
}


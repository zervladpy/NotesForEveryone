package com.example.uf1_proyecto_compose.data.database.repository.auth

import com.google.firebase.auth.FirebaseUser

data class UserModel(
    val uid: String?,
    val email: String? = null,
)

fun FirebaseUser.toData() = UserModel(uid)
package com.example.uf1_proyecto_compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uf1_proyecto_compose.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val uid: String,
    @ColumnInfo(name = "display_name")
    val displayName: String?,
    @ColumnInfo(name = "email")
    val email: String?,
)

fun UserEntity.toDomain(): User {
    return User(
        uid = uid,
        displayName = displayName ?: ""
    )
}

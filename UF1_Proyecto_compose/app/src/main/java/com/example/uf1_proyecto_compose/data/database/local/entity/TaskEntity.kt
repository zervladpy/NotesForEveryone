package com.example.uf1_proyecto_compose.data.database.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uf1_proyecto_compose.domain.models.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val uid: String = "",
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "done") val done: Boolean = false
)

fun Task.toDatabase() = TaskEntity(uid, title, description, done)
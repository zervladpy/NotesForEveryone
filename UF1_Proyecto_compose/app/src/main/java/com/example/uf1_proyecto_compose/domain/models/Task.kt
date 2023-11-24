package com.example.uf1_proyecto_compose.domain.models

import com.example.uf1_proyecto_compose.data.database.local.TaskEntity
import com.example.uf1_proyecto_compose.data.database.repository.task.TaskModel

data class Task(
    val uid: String,
    val title: String,
    val description: String,
    val done: Boolean,
)

fun TaskModel.toDomain() = Task(uid, title, description, done)

fun TaskEntity.toDomain() = Task(uid, title, description, done)


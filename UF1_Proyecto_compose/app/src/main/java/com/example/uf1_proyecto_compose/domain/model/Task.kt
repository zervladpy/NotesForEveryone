package com.example.uf1_proyecto_compose.domain.model

import com.example.uf1_proyecto_compose.data.source.local.roomdb.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.source.remote.dto.TaskDto

data class Task(
    val uid: String,
    val title: String,
    val description: String,
    val done: Boolean,
)

/// Mapper to TaskDto
fun Task.toDto() = TaskDto(uid, title, description, done)

/// Mapper to TaskEntity
fun Task.toEntity() = TaskEntity(uid, title, description, done)
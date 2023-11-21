package com.example.uf1_proyecto_compose.data.source.remote.dto

import com.example.uf1_proyecto_compose.data.source.local.roomdb.entity.TaskEntity

data class TaskDto(
    val uid: String,
    val title: String,
    val description: String,
)

/// Mapper to Entity
fun TaskDto.toEntity() = TaskEntity(uid, title, description)

/// Mapper to Movie
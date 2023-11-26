package com.example.uf1_proyecto_compose.domain.model

import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.remote.dto.TaskDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Task(
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val done: Boolean = false,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val synchronized: Boolean = false,
)

fun Task.toDto(): TaskDto {
    return TaskDto(
        uid,
        title,
        description,
        done,
        creationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).toString(),
        synchronized
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        uid,
        title,
        description,
        done,
        creationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).toString(),
        synchronized
    )
}


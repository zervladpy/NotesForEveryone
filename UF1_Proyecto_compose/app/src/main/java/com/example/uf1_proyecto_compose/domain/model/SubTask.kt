package com.example.uf1_proyecto_compose.domain.model

import com.example.uf1_proyecto_compose.data.local.entity.SubTaskEntity
import com.example.uf1_proyecto_compose.data.remote.dto.SubTaskDto

data class SubTask(
    val title: String,
    val done: Boolean = false
)

fun SubTask.toEntity(taskUid: String): SubTaskEntity {
    return SubTaskEntity(
        title = title,
        done = done,
        taskUid = taskUid,
    )
}

fun SubTask.toDto(): SubTaskDto {
    return SubTaskDto(
        title = title,
        done = done
    )
}
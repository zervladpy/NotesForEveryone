package com.example.uf1_proyecto_compose.domain.model

import com.example.uf1_proyecto_compose.data.local.entity.SubTaskEntity
import com.example.uf1_proyecto_compose.data.remote.dto.SubtaskDto

data class Subtask(
    val uid: String,
    val title: String,
    val done: Boolean = false
)

fun Subtask.toEntity(taskUid: String): SubTaskEntity {
    return SubTaskEntity(
        uid = uid,
        title = title,
        done = done,
        taskUid = taskUid,
    )
}

fun Subtask.toDto(): SubtaskDto {
    return SubtaskDto(
        uid = uid,
        title = title,
        done = done
    )
}
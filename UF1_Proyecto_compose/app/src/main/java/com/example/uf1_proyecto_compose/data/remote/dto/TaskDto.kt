package com.example.uf1_proyecto_compose.data.remote.dto

import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.utils.formatter.FormatterConstraint
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class TaskDto(
    @SerializedName("uid") val uid: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("done") val done: Boolean = false,
    @SerializedName("creation_date") val creationDate: String = "",
    @SerializedName("synchronized") val synchronized: Boolean = false,
)

fun TaskDto.toDomain(): Task {
    return Task(
        uid = uid,
        title = title,
        description = description,
        done = done,
        creationDate = LocalDateTime.parse(
            creationDate,
            FormatterConstraint.dateFormat
        ),
        synchronized = synchronized
    )
}

fun TaskDto.toEntity(): TaskEntity {
    return TaskEntity(
        uid,
        title,
        description,
        done,
        creationDate,
        synchronized
    )
}

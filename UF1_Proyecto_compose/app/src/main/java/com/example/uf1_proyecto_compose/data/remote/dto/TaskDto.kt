package com.example.uf1_proyecto_compose.data.remote.dto

import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.domain.model.Task
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
        uid,
        title,
        description,
        done,
        LocalDateTime.parse(creationDate, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
        synchronized
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

package com.example.uf1_proyecto_compose.data.remote.dto

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
    @SerializedName("progression") val progression: Int = if (done) 100 else 0,
    @SerializedName("subtasks") val subtasks: List<SubTaskDto> = emptyList(),
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
        synchronized = synchronized,
        progression = progression.toFloat(),
        subtasks = subtasks.map { it.toDomain() }
    )
}

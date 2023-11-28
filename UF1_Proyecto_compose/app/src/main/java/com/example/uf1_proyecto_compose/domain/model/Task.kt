package com.example.uf1_proyecto_compose.domain.model

import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.remote.dto.TaskDto
import com.example.uf1_proyecto_compose.utils.formatter.FormatterConstraint
import java.time.LocalDateTime

data class Task(
    val uid: String = "",
    val title: String = "",
    val description: String = "",
    val done: Boolean = false,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val synchronized: Boolean = false,
    val progression: Float = 0f,
    val subtasks: List<Subtask> = emptyList(),
)

fun Task.toDto(): TaskDto {
    return TaskDto(
        uid = uid,
        title = title,
        description = description,
        done = done,
        creationDate = creationDate
            .format(FormatterConstraint.dateFormat)
            .toString(),
        synchronized = synchronized,
        progression = progression.toInt(),
        subtasks = subtasks.map { it.toDto() },
    )
}

fun Task.toEntity(userUid: String): TaskEntity {
    return TaskEntity(
        uid = uid,
        title = title,
        description = description,
        creationDate = creationDate.format(FormatterConstraint.dateFormat),
        synced = synchronized,
        progress = progression.toInt(),
        done = done,
        userUid = userUid
    )
}

fun Task.copyWith(
    title: String? = null,
    description: String? = null,
    done: Boolean? = null,
    synchronized: Boolean? = null,
    progression: Float? = null,
    subtasks: List<Subtask>? = null
): Task {
    return Task(
        uid = uid,
        title = title ?: this.title,
        description = description ?: this.description,
        done = done ?: this.done,
        creationDate = creationDate,
        synchronized = synchronized ?: this.synchronized,
        progression = progression ?: this.progression,
        subtasks = subtasks ?: this.subtasks
    )
}


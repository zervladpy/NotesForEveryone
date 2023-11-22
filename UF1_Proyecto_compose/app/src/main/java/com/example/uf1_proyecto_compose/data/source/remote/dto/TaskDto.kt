package com.example.uf1_proyecto_compose.data.source.remote.dto

import com.example.uf1_proyecto_compose.data.source.local.roomdb.entity.TaskEntity
import com.example.uf1_proyecto_compose.domain.model.Task
import com.google.firebase.firestore.DocumentId

data class TaskDto(
    @DocumentId val uid: String,
    val title: String,
    val description: String,
    val done: Boolean,
)

/// Mapper to Entity
fun TaskDto.toEntity() = TaskEntity(uid, title, description, done)

/// Mapper to Model
fun TaskDto.toModel() = Task(uid, title, description, done)

/// Mapper from Model
fun TaskDto.fromModel(task: Task) = TaskDto(task.uid, task.title, task.description, task.done)
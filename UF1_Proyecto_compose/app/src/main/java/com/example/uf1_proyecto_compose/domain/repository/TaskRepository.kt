package com.example.uf1_proyecto_compose.domain.repository

import com.example.uf1_proyecto_compose.domain.model.Task

interface TaskRepository {

    suspend fun get(userUid: String): List<Task>

    suspend fun get(userUid: String, taskUid: String): Task?

    suspend fun insert(userUid: String, task: Task)

    suspend fun update(userUid: String, task: Task)

    suspend fun delete(userUid: String, taskUid: String)

}
package com.example.uf1_proyecto_compose.domain.repository

import com.example.uf1_proyecto_compose.domain.model.Task

interface TaskRepository {

    suspend fun apiGetAll(userUid: String): List<Task>

    suspend fun apiGetOne(userUid: String, taskUid: String): Task?

    suspend fun apiInsert(userUid: String, task: Task)

    suspend fun apiUpdate(userUid: String, task: Task)

    suspend fun apiDeleteById(userUid: String, taskUid: String)

}
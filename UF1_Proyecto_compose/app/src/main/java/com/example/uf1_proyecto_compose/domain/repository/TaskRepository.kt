package com.example.uf1_proyecto_compose.domain.repository

import com.example.uf1_proyecto_compose.domain.model.Task

interface TaskRepository {

    suspend fun apiGetAll(userUid: String): List<Task>

    suspend fun apiGetById(userUid: String, taskUid: String): Task

    suspend fun apiInsert(userUid: String, task: Task)

    suspend fun apiUpdate(userUid: String, task: Task)

    suspend fun apiDeleteById(userUid: String, taskUid: String)

    suspend fun databaseGetAll(userUid: String): List<Task>

    suspend fun databaseInsertAll(userUid: String, listTasks: List<Task>)

    suspend fun databaseDeleteByUid(userUid: String, taskUid: String)

    suspend fun databaseGetByUid(userUid: String, taskUid: String): Task

    suspend fun databaseDeleteAll(userUid: String)

}
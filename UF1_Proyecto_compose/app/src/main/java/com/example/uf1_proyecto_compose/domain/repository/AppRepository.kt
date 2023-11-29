package com.example.uf1_proyecto_compose.domain.repository

import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.model.User

interface AppRepository {

    suspend fun apiGetAll(userUid: String): List<Task>

    suspend fun apiGetById(userUid: String, taskUid: String): Task

    suspend fun apiInsert(userUid: String, task: Task)

    suspend fun apiUpdate(userUid: String, task: Task)

    suspend fun apiDeleteById(userUid: String, taskUid: String)

    suspend fun databaseInsertUser(user: User)

    suspend fun databaseInsertTask(userUid: String, task: Task)

    suspend fun databaseInsertManyTasks(userUid: String, tasks: List<Task>)

    suspend fun databaseGetUser(userUid: String): User?

    suspend fun databaseGetTask(taskUid: String): Task?

    suspend fun databaseDeleteUser(user: User)

    suspend fun databaseDeleteTask(task: Task)

    suspend fun databaseUpdateUser(user: User)

    suspend fun databaseUpdateTask(task: Task)

    suspend fun databaseGetUserTasks(userUid: String): List<Task>

    suspend fun databaseDeleteUserTasks(userUid: String)

    suspend fun databaseSyncUserTasks(user: User)

}
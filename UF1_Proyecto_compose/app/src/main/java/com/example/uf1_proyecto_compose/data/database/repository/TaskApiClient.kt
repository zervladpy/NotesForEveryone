package com.example.uf1_proyecto_compose.data.database.repository

interface TaskApiClient {

    suspend fun getTasks(): List<TaskModel>

    suspend fun insertTask(task: TaskModel)

    suspend fun deleteTask(task: TaskModel)

    suspend fun updateTask(task: TaskModel)

}
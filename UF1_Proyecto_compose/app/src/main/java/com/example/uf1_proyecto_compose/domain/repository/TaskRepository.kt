package com.example.uf1_proyecto_compose.domain.repository

import com.example.uf1_proyecto_compose.data.source.remote.Response
import com.example.uf1_proyecto_compose.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getTasks(): Flow<Response<List<Task>>>

    suspend fun createTask(task: Task): Flow<Response<Unit>>

    suspend fun deleteTask(task: Task): Flow<Response<Unit>>
}
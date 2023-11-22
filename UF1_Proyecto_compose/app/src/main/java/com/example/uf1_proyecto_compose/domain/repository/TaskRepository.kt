package com.example.uf1_proyecto_compose.domain.repository

import com.example.uf1_proyecto_compose.data.source.remote.Response
import com.example.uf1_proyecto_compose.domain.model.Task

interface TaskRepository {

    suspend fun getTasks(): Response<List<Task>>

    suspend fun createTask(task: Task): Response<Unit>

    suspend fun deleteTask(task: Task): Response<Unit>
}
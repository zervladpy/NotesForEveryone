package com.example.uf1_proyecto_compose.domain.usecase

import com.example.uf1_proyecto_compose.data.source.remote.Response
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(
    private val tasksRepository: TaskRepository
) {
    suspend operator fun invoke(): Flow<Response<List<Task>>> = tasksRepository.getTasks()
}
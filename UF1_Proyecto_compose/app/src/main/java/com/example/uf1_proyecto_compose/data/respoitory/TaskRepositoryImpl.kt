package com.example.uf1_proyecto_compose.data.respoitory

import com.example.uf1_proyecto_compose.data.source.remote.Response
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl constructor() : TaskRepository {
    override suspend fun getTasks(): Flow<Response<List<Task>>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveTask(task: Task): Flow<Response<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: Task): Flow<Response<Unit>> {
        TODO("Not yet implemented")
    }

}
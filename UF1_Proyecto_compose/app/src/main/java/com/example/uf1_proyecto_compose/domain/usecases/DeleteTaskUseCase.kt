package com.example.uf1_proyecto_compose.domain.usecases

import com.example.uf1_proyecto_compose.data.database.TaskRepository
import com.example.uf1_proyecto_compose.domain.models.Task
import javax.inject.Inject

class DeleteTaskUseCase
@Inject constructor(
    private val repository: TaskRepository,
) {

    suspend fun delete(task: Task): Unit {

        repository.apiDeleteTask(task)

        repository.dbDeleteTask(task)

    }

}
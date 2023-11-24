package com.example.uf1_proyecto_compose.domain.usecases

import com.example.uf1_proyecto_compose.data.database.TaskRepository
import com.example.uf1_proyecto_compose.domain.models.Task
import javax.inject.Inject

class UpdateTaskUseCase
@Inject constructor(
    private val repository: TaskRepository,
) {

    suspend fun update(task: Task): Unit {

        /**
         * TODO (Complete implementation)
         * If update failed, mark as not synced
         * */

        return repository.apiUpdateTask(task)

    }

}
package com.example.uf1_proyecto_compose.domain.usecases

import com.example.uf1_proyecto_compose.data.database.TaskRepository
import com.example.uf1_proyecto_compose.domain.models.Task
import javax.inject.Inject

class InsertTaskUseCase
@Inject constructor(
    private val repository: TaskRepository,
) {

    suspend fun insert(
        task: Task,
    ): Unit {
        /**
         * TODO (Complete implementation)
         * Try to upload task to remote
         * if is not possible mark as not synced and store locally
         * **/

        repository.apiCreateTask(task)
    }

}
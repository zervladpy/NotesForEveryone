package com.example.uf1_proyecto_compose.domain.usecases

import android.util.Log
import com.example.uf1_proyecto_compose.data.database.TaskRepository
import com.example.uf1_proyecto_compose.data.database.local.toDatabase
import com.example.uf1_proyecto_compose.domain.models.Task
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository,

    ) {
    suspend operator fun invoke(): List<Task> {
        val tasks = repository.apiGetAllTasks()

        Log.d(GetTasksUseCase::class.toString(), tasks.toString())

        if (tasks.isNotEmpty()) {
            repository.dbClear()
            repository.dbInsertTasks(tasks.map { it.toDatabase() })
        }

        /** If Sabela asks, unique source of truth :) */
        return getFromUniqueSource()
    }

    suspend fun getFromUniqueSource(): List<Task> {
        return repository.dbGetTasks()
    }
}


package com.example.uf1_proyecto_compose.domain

import android.util.Log
import com.example.uf1_proyecto_compose.data.database.TaskRepository
import com.example.uf1_proyecto_compose.data.database.local.toDatabase
import com.example.uf1_proyecto_compose.domain.models.Task
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository,

    ) {
    suspend operator fun invoke(): List<Task> {
        val tasks = repository.getAllTasksFromApi()

        Log.d(GetTasksUseCase::class.toString(), tasks.toString())

        return if (tasks.isNotEmpty()) {
            repository.clear()
            repository.insertTasks(tasks.map { it.toDatabase() })
            tasks
        } else {
            repository.getAllTasksFromDatabase()
        }
    }
}


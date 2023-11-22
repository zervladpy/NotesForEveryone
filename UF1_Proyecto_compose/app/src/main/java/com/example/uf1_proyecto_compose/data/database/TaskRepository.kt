package com.example.uf1_proyecto_compose.data.database

import com.example.uf1_proyecto_compose.data.database.local.dao.TaskDao
import com.example.uf1_proyecto_compose.data.database.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.database.repository.TaskService
import com.example.uf1_proyecto_compose.domain.models.Task
import com.example.uf1_proyecto_compose.domain.models.toDomain
import javax.inject.Inject

class TaskRepository
@Inject constructor(
    private val api: TaskService,
    private val taskDao: TaskDao
) {
    suspend fun getAllTasksFromApi(): List<Task> {
        return api.getTasks().map { it.toDomain() }
    }

    suspend fun getAllTasksFromDatabase(): List<Task> {
        return taskDao.getAllTasks().map { it.toDomain() }
    }

    suspend fun insertTasks(tasks: List<TaskEntity>) {
        taskDao.insertTasks(tasks)
    }

    suspend fun clear() {
        taskDao.deleteAll()
    }

}
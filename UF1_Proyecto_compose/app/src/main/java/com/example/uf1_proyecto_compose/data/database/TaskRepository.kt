package com.example.uf1_proyecto_compose.data.database

import android.util.Log
import com.example.uf1_proyecto_compose.data.database.local.TaskDao
import com.example.uf1_proyecto_compose.data.database.local.TaskEntity
import com.example.uf1_proyecto_compose.data.database.local.toDatabase
import com.example.uf1_proyecto_compose.data.database.repository.task.TaskService
import com.example.uf1_proyecto_compose.data.database.repository.task.toData
import com.example.uf1_proyecto_compose.domain.models.Task
import com.example.uf1_proyecto_compose.domain.models.toDomain
import javax.inject.Inject

class TaskRepository
@Inject constructor(
    private val api: TaskService,
    private val taskDao: TaskDao,
) {
    suspend fun apiGetAllTasks(): List<Task> {
        return api.getTasks().map {
            Log.d("Task Mapper", it.toDomain().toString())
            it.toDomain()
        }
    }

    suspend fun apiCreateTask(task: Task) {
        api.insertTask(task.toData())
    }

    suspend fun apiDeleteTask(task: Task) {
        api.deleteTask(task.toData())
    }

    suspend fun apiUpdateTask(task: Task) {
        api.updateTask(task.toData())
    }

    suspend fun dbGetTasks(): List<Task> {
        return taskDao.getAllTasks().map { it.toDomain() }
    }

    suspend fun dbInsertTasks(tasks: List<TaskEntity>) {
        taskDao.insertTasks(tasks)
    }

    suspend fun dbClear() {
        taskDao.deleteAll()
    }

    suspend fun dbDeleteTask(task: Task) {
        taskDao.delete(task.toDatabase())
    }

    suspend fun dbUpdateTask(task: Task) {
        taskDao.updateTask(task.toDatabase())
    }

    suspend fun dbUpdateTasks(tasks: List<Task>) {
        taskDao.updateTasks(tasks.map { it.toDatabase() })
    }

}
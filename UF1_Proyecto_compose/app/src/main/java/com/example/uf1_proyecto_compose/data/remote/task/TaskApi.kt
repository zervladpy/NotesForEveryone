package com.example.uf1_proyecto_compose.data.remote.task

import com.example.uf1_proyecto_compose.data.remote.dto.TaskDto

/**
 * Interface for Task api
 * */
interface TaskApi {

    suspend fun getAllTasks(userUid: String): List<TaskDto>

    suspend fun getOne(userUid: String, taskUid: String): TaskDto

    suspend fun insertTask(userUid: String, task: TaskDto)

    suspend fun deleteTask(userUid: String, taskUid: String)

    suspend fun updateTask(userUid: String, task: TaskDto)
}
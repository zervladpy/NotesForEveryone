package com.example.uf1_proyecto_compose.data.remote.task

import com.example.uf1_proyecto_compose.data.remote.dto.TaskDto

/**
 * Interface for Task api
 * */
interface TaskApi {

    suspend fun get(userUid: String): List<TaskDto>

    suspend fun get(userUid: String, taskUid: String): TaskDto

    suspend fun insert(userUid: String, task: TaskDto)

    suspend fun delete(userUid: String, taskUid: String)

    suspend fun update(userUid: String, task: TaskDto)
}
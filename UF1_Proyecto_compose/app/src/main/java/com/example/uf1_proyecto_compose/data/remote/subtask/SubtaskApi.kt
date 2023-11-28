package com.example.uf1_proyecto_compose.data.remote.subtask

import com.example.uf1_proyecto_compose.data.remote.dto.SubtaskDto

interface SubtaskApi {

    suspend fun getAllSubtask(userUid: String, taskUid: String): List<SubtaskDto>
    suspend fun insertSubtask(userUid: String, taskUid: String, subtask: SubtaskDto)
    suspend fun insertManySubtask(userUid: String, taskUid: String, subtasks: List<SubtaskDto>)
    suspend fun dropSubtask(userUid: String, taskUid: String, subtaskUid: String)
    suspend fun dropTaskSubtask(userUid: String, taskUid: String)

}
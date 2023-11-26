package com.example.uf1_proyecto_compose.data.remote.task

import com.example.uf1_proyecto_compose.data.remote.dto.TaskDto

/**
 * Interface for Task api
 * */
interface TaskApi {

    /**
     *
     * @param userUid
     * @param taskDto
     *
     * @return Unit
     * */
    suspend fun insertRecord(userUid: String, taskDto: TaskDto)

    /**
     *
     * @param userUid
     *
     * @return Unit
     * */
    suspend fun getRecords(userUid: String): List<TaskDto>

    /**
     *
     * @param userUid
     * @param taskUid
     *
     * @return Unit
     * */
    suspend fun getRecord(userUid: String, taskUid: String): TaskDto

    /**
     *
     * @param userUid
     * @param taskUid
     *
     * @return Unit
     * */
    suspend fun deleteRecord(userUid: String, taskUid: String)

}
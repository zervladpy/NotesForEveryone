package com.example.uf1_proyecto_compose.domain.repository

import com.example.uf1_proyecto_compose.domain.model.Subtask

interface SubtaskRepository {

    suspend fun get(userUid: String, taskUid: String): List<Subtask>
    suspend fun insert(userUid: String, taskUid: String, item: Subtask)

}
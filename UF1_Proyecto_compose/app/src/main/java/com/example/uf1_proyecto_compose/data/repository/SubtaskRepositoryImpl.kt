package com.example.uf1_proyecto_compose.data.repository

import com.example.uf1_proyecto_compose.data.remote.dto.toDomain
import com.example.uf1_proyecto_compose.data.remote.subtask.SubtaskApi
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.domain.repository.SubtaskRepository
import javax.inject.Inject

class SubtaskRepositoryImpl
@Inject constructor(
    private val api: SubtaskApi
) : SubtaskRepository {
    override suspend fun get(userUid: String, taskUid: String): List<Subtask> {


        return api.getAllSubtask(userUid, taskUid).map { it.toDomain() }
    }
}
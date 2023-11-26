package com.example.uf1_proyecto_compose.data.repository

import com.example.uf1_proyecto_compose.data.local.dao.TaskDao
import com.example.uf1_proyecto_compose.data.remote.dto.toDomain
import com.example.uf1_proyecto_compose.data.remote.task.TaskApi
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.model.toDto
import com.example.uf1_proyecto_compose.domain.model.toEntity
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl
@Inject constructor(
    private val api: TaskApi,
    private val dao: TaskDao
) : TaskRepository {
    override suspend fun apiGetAll(userUid: String): List<Task> {
        return api.getRecords(userUid).map { it.toDomain() }
    }

    override suspend fun apiGetById(userUid: String, taskUid: String): Task {
        return api.getRecord(userUid, taskUid).toDomain()
    }

    override suspend fun apiInsert(userUid: String, task: Task) {
        api.insertRecord(userUid, task.toDto())
    }

    override suspend fun apiUpdate(userUid: String, task: Task) {
        api.insertRecord(userUid, task.toDto())
    }

    override suspend fun apiDeleteById(userUid: String, taskUid: String) {
        api.deleteRecord(userUid, taskUid)
    }

    override suspend fun databaseInsertAll(userUid: String, listTasks: List<Task>) {
        dao.insertAll(listTasks.map { it.toEntity() })
    }

    override suspend fun databaseDeleteAll(userUid: String) {
        dao.deleteAll()
    }
}
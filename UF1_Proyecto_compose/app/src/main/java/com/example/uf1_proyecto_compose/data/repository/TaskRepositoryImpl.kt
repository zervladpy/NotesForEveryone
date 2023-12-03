package com.example.uf1_proyecto_compose.data.repository

import com.example.uf1_proyecto_compose.data.local.dao.AppDao
import com.example.uf1_proyecto_compose.data.remote.dto.toDomain
import com.example.uf1_proyecto_compose.data.remote.task.TaskApi
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.model.toDto
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl
@Inject constructor(
    private val api: TaskApi,
    private val dao: AppDao,
) : TaskRepository {


    override suspend fun get(userUid: String): List<Task> {
        val result = api.get(userUid).map { it.toDomain() }
        // TODO(insert to db)


        // TODO(return from db)

        return result
    }

    override suspend fun get(userUid: String, taskUid: String): Task {
        val result = api.get(userUid, taskUid).toDomain()


        return result
    }

    override suspend fun insert(userUid: String, task: Task) {


        api.insert(userUid, task = task.toDto())
    }

    override suspend fun update(userUid: String, task: Task) {
        
        api.update(userUid, task = task.toDto())
    }

    override suspend fun delete(userUid: String, taskUid: String) {


        api.delete(userUid, taskUid)
    }

}
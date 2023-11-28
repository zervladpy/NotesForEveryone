package com.example.uf1_proyecto_compose.data.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _tasks = mutableStateOf(emptyList<Task>())
    val tasks: State<List<Task>> = _tasks

    override suspend fun apiGetAll(userUid: String): List<Task> {
        return api.getAllTasks(userUid).map { it.toDomain() }
    }

    override suspend fun apiGetOne(userUid: String, taskUid: String): Task {
        return api.getOne(userUid, taskUid).toDomain()
    }

    override suspend fun apiInsert(userUid: String, task: Task) {
        api.insertTask(userUid, task = task.toDto())
    }

    override suspend fun apiUpdate(userUid: String, task: Task) {
        api.updateTask(userUid, task = task.toDto())
    }

    override suspend fun apiDeleteById(userUid: String, taskUid: String) {
        api.deleteTask(userUid, taskUid)
    }

}
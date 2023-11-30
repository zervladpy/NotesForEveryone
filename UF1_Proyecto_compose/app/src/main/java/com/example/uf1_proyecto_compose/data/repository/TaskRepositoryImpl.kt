package com.example.uf1_proyecto_compose.data.repository

import androidx.lifecycle.MutableLiveData
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

    override val tasks: MutableLiveData<List<Task>> by lazy {
        MutableLiveData<List<Task>>()
    }

    override suspend fun get(userUid: String): List<Task> {
        val result = api.get(userUid).map { it.toDomain() }
        // TODO(insert to db)

        tasks.value = result

        // TODO(return from db)

        return result
    }

    override suspend fun get(userUid: String, taskUid: String): Task {
        val result = api.get(userUid, taskUid).toDomain()

        val updatedResult = tasks.value?.map {
            if (it.uid == result.uid) {
                result
            } else it
        }

        tasks.value = updatedResult

        return result
    }

    override suspend fun insert(userUid: String, task: Task) {

        val updatedResult = tasks.value?.toMutableList()!!.apply { add(task) }.toList()

        tasks.value = updatedResult

        api.insert(userUid, task = task.toDto())
    }

    override suspend fun update(userUid: String, task: Task) {

        val updatedResult = tasks.value?.map {
            if (it.uid == task.uid) {
                task
            } else it
        }

        tasks.value = updatedResult

        api.update(userUid, task = task.toDto())
    }

    override suspend fun delete(userUid: String, taskUid: String) {

        val updatedResult = tasks.value?.filter { it.uid == taskUid }

        tasks.value = updatedResult

        api.delete(userUid, taskUid)
    }

}
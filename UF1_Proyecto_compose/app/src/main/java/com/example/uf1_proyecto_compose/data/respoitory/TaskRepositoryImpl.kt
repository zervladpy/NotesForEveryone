package com.example.uf1_proyecto_compose.data.respoitory

import com.example.uf1_proyecto_compose.data.source.local.roomdb.dao.TaskDao
import com.example.uf1_proyecto_compose.data.source.local.roomdb.entity.toTask
import com.example.uf1_proyecto_compose.data.source.remote.Response
import com.example.uf1_proyecto_compose.data.source.remote.dto.TaskDto
import com.example.uf1_proyecto_compose.data.source.remote.dto.toEntity
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.model.toDto
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class TaskRepositoryImpl constructor(
    private val api: CollectionReference,
    private val taskDao: TaskDao,
) : TaskRepository {
    override suspend fun getTasks(): Flow<Response<List<Task>>> = flow {

        emit(Response.Loading())

        try {
            getTasksAndStoreLocal(api, taskDao)
        } catch (e: FirebaseFirestoreException) {
            emit(Response.Error(message = e.toString()))

        } catch (e: Exception) {
            emit(Response.Error(message = e.toString()))

        }

        // Emit success
        emit(Response.Success(data = getTasksFromDao(tasksDao = taskDao)))
    }

    override suspend fun createTask(task: Task): Flow<Response<Unit>> = flow {

        emit(Response.Loading())

        try {
            insertToRepository(api, taskDao, task.toDto())
        } catch (e: FirebaseFirestoreException) {
            emit(Response.Error(message = e.toString()))
        } catch (e: Exception) {
            emit(Response.Error(message = e.toString()))
        }

        // emit Success
        emit(Response.Success())
    }

    override suspend fun deleteTask(task: Task): Flow<Response<Unit>> = flow {

        emit(Response.Loading())

        try {
            delete(api, taskDao, task.toDto())
        } catch (e: FirebaseFirestoreException) {
            emit(Response.Error(message = e.toString()))
        } catch (e: Exception) {
            emit(Response.Error(message = e.toString()))
        }

        emit(Response.Success())
    }

    private suspend fun getTasksAndStoreLocal(
        api: CollectionReference,
        taskDao: TaskDao,
    ) {
        val documents: List<DocumentSnapshot> = api.get().result.documents

        if (documents.isNotEmpty()) {
            taskDao.insertAll(documents.map { it.toObject<TaskDto>()!!.toEntity() })
        }

    }

    private suspend fun getTasksFromDao(tasksDao: TaskDao): List<Task> {
        return tasksDao.getAll().map { it.toTask() }
    }

    private suspend fun insertToRepository(
        api: CollectionReference,
        taskDao: TaskDao,
        taskDto: TaskDto,
    ) {
        api.document(taskDto.uid).set(taskDto).await()
        taskDao.insert(taskDto.toEntity())
    }

    private suspend fun delete(
        api: CollectionReference,
        taskDao: TaskDao,
        taskDto: TaskDto,
    ) {
        api.document(taskDto.uid).delete().await()
        taskDao.delete(taskDto.toEntity())
    }

}
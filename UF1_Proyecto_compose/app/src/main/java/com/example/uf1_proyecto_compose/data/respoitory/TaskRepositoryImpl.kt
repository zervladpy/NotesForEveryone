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
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TaskRepositoryImpl
@Inject
constructor(
    private val taskList: CollectionReference,
    private val taskDao: TaskDao,
) : TaskRepository {
    override suspend fun getTasks(

    ): Response<List<Task>> = try {
        Response.Success(data = getTasksAndStoreLocal(taskList, taskDao))

    } catch (e: FirebaseFirestoreException) {
        Response.Error(message = e.toString())

    } catch (e: Exception) {
        Response.Error(message = e.toString())
    }

    override suspend fun createTask(
        task: Task
    ): Response<Unit> = try {
        insertToRepository(taskList, taskDao, task.toDto())
        Response.Success()
    } catch (e: FirebaseFirestoreException) {
        Response.Error(message = e.toString())
    } catch (e: Exception) {
        Response.Error(message = e.toString())
    }

    override suspend fun deleteTask(
        task: Task
    ): Response<Unit> = try {
        delete(taskList, taskDao, task.toDto())
        Response.Success()
    } catch (e: FirebaseFirestoreException) {
        Response.Error(message = e.toString())
    } catch (e: Exception) {
        Response.Error(message = e.toString())
    }

    private suspend fun getTasksAndStoreLocal(
        api: CollectionReference,
        taskDao: TaskDao,
    ): List<Task> {
        val documents: List<DocumentSnapshot> = api.get().result.documents

        if (documents.isNotEmpty()) {
            taskDao.insertAll(documents.map { it.toObject<TaskDto>()!!.toEntity() })
        }

        return getTasksFromDao(tasksDao = taskDao)
    }

    private suspend fun getTasksFromDao(
        tasksDao: TaskDao,
    ): List<Task> {
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
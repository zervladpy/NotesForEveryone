package com.example.uf1_proyecto_compose.data.repository

import com.example.uf1_proyecto_compose.data.local.dao.AppDao
import com.example.uf1_proyecto_compose.data.local.entity.toDomain
import com.example.uf1_proyecto_compose.data.remote.dto.toDomain
import com.example.uf1_proyecto_compose.data.remote.task.TaskApi
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.domain.model.toDto
import com.example.uf1_proyecto_compose.domain.model.toEntity
import com.example.uf1_proyecto_compose.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl
@Inject constructor(
    private val api: TaskApi,
    private val dao: AppDao,
) : AppRepository {
    override suspend fun apiGetAll(userUid: String): List<Task> {
        return api.getAllTasks(userUid).map { it.toDomain() }
    }

    override suspend fun apiGetById(userUid: String, taskUid: String): Task {
        return api.getOne(userUid, taskUid).toDomain()
    }

    override suspend fun apiInsert(userUid: String, task: Task) {
        api.insertTask(userUid, task.toDto())
    }

    override suspend fun apiUpdate(userUid: String, task: Task) {
        api.insertTask(userUid, task.toDto())
    }

    override suspend fun apiDeleteById(userUid: String, taskUid: String) {
        api.deleteTask(userUid, taskUid)
    }

    override suspend fun databaseInsertUser(user: User) {
        dao.insertUser(user.toEntity())
    }

    override suspend fun databaseInsertTask(userUid: String, task: Task) {
        dao.insertTask(task.toEntity(userUid))
    }

    override suspend fun databaseInsertManyTasks(userUid: String, tasks: List<Task>) {

        val subTasks = tasks.map { it.subtasks.map { subtask -> subtask.toEntity(it.uid) } }

        dao.insertManyTasks(tasks.map { it.toEntity(userUid) })

        for (st in subTasks) {
            dao.insertManySubTasks(st)
        }

    }

    override suspend fun databaseGetUser(userUid: String): User? {
        return dao.getUserByUid(userUid)?.toDomain()
    }

    override suspend fun databaseGetTask(taskUid: String): Task {

        val tasksWithSubTasks = dao.getTaskWithSubtasks(taskUid)

        return tasksWithSubTasks.task.toDomain(tasksWithSubTasks.subtasks)
    }

    override suspend fun databaseDeleteUser(user: User) {
        throw NotImplementedError("Not implemented Error")
    }

    override suspend fun databaseDeleteTask(task: Task) {
        dao.deleteTask(task.uid)
    }

    override suspend fun databaseUpdateUser(user: User) {
        throw NotImplementedError("Update not yet implemented")
    }

    override suspend fun databaseUpdateTask(task: Task) {
        throw NotImplementedError("Update not yet implemented")
    }

    override suspend fun databaseGetUserTasks(userUid: String): List<Task> {
        return dao.getUserTasks(userUid).map { it.toDomain(emptyList()) }
    }

    override suspend fun databaseDeleteUserTasks(userUid: String) {
        dao.deleteTasksWithUser(userUid)
    }

    override suspend fun databaseSyncUserTasks(user: User) {
        throw NotImplementedError("Update not yet implemented")
    }

}
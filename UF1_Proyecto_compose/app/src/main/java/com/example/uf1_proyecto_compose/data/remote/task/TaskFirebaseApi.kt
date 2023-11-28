package com.example.uf1_proyecto_compose.data.remote.task

import com.example.uf1_proyecto_compose.data.remote.dto.TaskDto
import com.example.uf1_proyecto_compose.utils.constraint.TableConstraint
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * FirebaseFirestore implementation of TaskRepositoryApi
 *
 * @param api FirebaseFirestore instance
 *
 * @see FirebaseFirestore
 * @see TaskApi
 * @see TableConstraint
 * @see TaskDto
 * */
class TaskFirebaseApi
@Inject constructor(
    private val api: FirebaseFirestore,
) : TaskApi {

    companion object {

        private const val PARENT = "user"
        private const val DOCUMENT = "task"

    }

    override suspend fun getAllTasks(userUid: String): List<TaskDto> {

        val collection = api.collection(PARENT).document(userUid).collection(DOCUMENT)
        val documents = collection.get().await().documents

        return documents.map { it.toObject()!! }

    }

    override suspend fun getOne(userUid: String, taskUid: String): TaskDto {

        return api.collection(PARENT)
            .document(userUid)
            .collection(DOCUMENT)
            .document(taskUid).get()
            .await().toObject()!!

    }

    override suspend fun insertTask(userUid: String, task: TaskDto) {
        api.collection(PARENT)
            .document(userUid)
            .collection(DOCUMENT)
            .document(task.uid)
            .set(task).await()
    }

    override suspend fun deleteTask(userUid: String, taskUid: String) {
        api.collection(PARENT).document(userUid).collection(DOCUMENT).document(taskUid).delete()
            .await()
    }

    override suspend fun updateTask(userUid: String, task: TaskDto) {

        /**
         * Replaces the task with another
         * */
        api.collection(PARENT).document(userUid).collection(DOCUMENT).document(task.uid).set(task)
    }

}
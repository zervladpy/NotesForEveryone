package com.example.uf1_proyecto_compose.data.database.repository.task

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskService
@Inject constructor(
    private val firestore: FirebaseFirestore,
) : TaskApiClient {

    companion object {
        private const val TABLE_NAME = "tasks"
    }


    override suspend fun getTasks(): List<TaskModel> {

        val result: List<TaskModel> = withContext(Dispatchers.IO) {
            val result = firestore.collection(TABLE_NAME).get().await().documents

            Log.d("Get Task", result.toString())

            if (result.isNotEmpty()) {

                result.mapNotNull {
                    Log.d("Get Task Mapper", it.toObject<TaskModel>().toString())
                    it.toObject()
                }
            } else {
                emptyList()
            }
        }

        Log.d(TaskService::class.toString(), result.toString())

        return result

    }

    override suspend fun insertTask(task: TaskModel) {
        withContext(Dispatchers.IO) {
            firestore.collection(TABLE_NAME).document(task.uid).set(task).await()
        }
    }

    override suspend fun deleteTask(task: TaskModel) {
        withContext(Dispatchers.IO) {
            firestore.collection(TABLE_NAME).document(task.uid).delete().await()
        }
    }

    override suspend fun updateTask(task: TaskModel) {
        withContext(Dispatchers.IO) {
            firestore.collection(TABLE_NAME).document(task.uid).set(task).await()
        }
    }

}
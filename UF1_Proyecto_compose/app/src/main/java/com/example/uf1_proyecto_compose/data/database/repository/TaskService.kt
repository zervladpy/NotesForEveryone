package com.example.uf1_proyecto_compose.data.database.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskService
@Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    suspend fun getTasks(): List<TaskModel> {

        return withContext(Dispatchers.IO) {
            val result = firestore.collection(TABLE_NAME).get().await().documents

            if (result.isNotEmpty()) {
                result.mapNotNull { it.toObject() }
            } else {
                emptyList()
            }
        }

    }

    companion object {
        private const val TABLE_NAME = "tasks"
    }

}
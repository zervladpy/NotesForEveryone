package com.example.uf1_proyecto_compose.data.remote.task

import com.example.uf1_proyecto_compose.data.remote.dto.SubTaskDto
import com.example.uf1_proyecto_compose.data.remote.dto.TaskDto
import com.example.uf1_proyecto_compose.utils.constraint.TableConstraint
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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
    private val api: FirebaseFirestore
) : TaskApi {

    /**
     * @param userUid
     * @return collectionPath for TaskTable document location
     * @see TableConstraint.TASK_TABLE
     * */
    private fun getCollectionPath(
        userUid: String
    ): CollectionReference {
        return api
            .collection(userUid)
            .document("data")
            .collection(TableConstraint.TASK_TABLE)
    }


    /**
     * Gets all TaskDto records for a specified user
     * @param userUid user identification
     * @return Returns a list of founded TasksDto's
     * */
    override suspend fun getRecords(userUid: String): List<TaskDto> {

        val tasks = mutableListOf<TaskDto>()

        val collection = getCollectionPath(userUid)

        val result: QuerySnapshot = collection.get().await()

        if (result.documents.isNotEmpty()) return tasks

        for (doc in result.documents) {
            val subResult =
                getCollectionPath(userUid).document(doc.id).collection("subtasks").get().await()

            val subTasks: List<SubTaskDto> = subResult.documents.map { it.toObject()!! }


        }


        return tasks;
    }

    /**
     * Inserts a record to FirebaseFirestore document
     * @param userUid
     * @param taskDto
     * */
    override suspend fun insertRecord(userUid: String, taskDto: TaskDto) {

        val collection = getCollectionPath(userUid)

        collection.document(taskDto.uid).set(taskDto).await()

    }

    /**
     * Gets a specific TaskDto record from FirebaseFirestore document
     * @param userUid user identification
     * @param taskUid task identification
     * @return Founded TaskDto object
     * */
    override suspend fun getRecord(userUid: String, taskUid: String): TaskDto {

        val collection = getCollectionPath(userUid)

        return collection.document(taskUid).get().await().toObject() ?: TaskDto()
    }

    /**
     * Deletes a TaskDto record from FirebaseFirestore document
     *
     * @param userUid user identification
     * @param taskUid task identification
     * */
    override suspend fun deleteRecord(userUid: String, taskUid: String) {

        val documentReference = getCollectionPath(userUid)

        documentReference.document(taskUid).delete().await()
    }

}
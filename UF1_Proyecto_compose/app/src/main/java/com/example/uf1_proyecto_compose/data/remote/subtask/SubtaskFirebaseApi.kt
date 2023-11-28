package com.example.uf1_proyecto_compose.data.remote.subtask

import com.example.uf1_proyecto_compose.data.remote.dto.SubtaskDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SubtaskFirebaseApi
@Inject constructor(
    private val api: FirebaseFirestore
) : SubtaskApi {

    companion object {
        private const val PARENT = "user"
        private const val DOCUMENT_PARENT = "task"
        private const val DOCUMENT = "subtask"
    }

    override suspend fun getAllSubtask(userUid: String, taskUid: String): List<SubtaskDto> {


        val collection = api.collection(PARENT)
            .document(userUid)
            .collection(DOCUMENT_PARENT)
            .document(taskUid).collection(DOCUMENT).get().await().documents

        return collection.map { it.toObject()!! }

    }

    override suspend fun insertSubtask(userUid: String, taskUid: String, subtask: SubtaskDto) {
        api.collection(PARENT)
            .document(userUid)
            .collection(DOCUMENT_PARENT)
            .document(taskUid).collection(DOCUMENT).document(subtask.uid).set(subtask)
    }

    override suspend fun insertManySubtask(
        userUid: String,
        taskUid: String,
        subtasks: List<SubtaskDto>
    ) {
        for (subtask in subtasks) {
            insertSubtask(userUid, taskUid, subtask)
        }
    }

    override suspend fun dropSubtask(userUid: String, taskUid: String, subtaskUid: String) {
        api.collection(PARENT)
            .document(userUid)
            .collection(DOCUMENT_PARENT)
            .document(taskUid).collection(DOCUMENT).document(subtaskUid).delete()
    }

    override suspend fun dropTaskSubtask(userUid: String, taskUid: String) {
        getAllSubtask(userUid, taskUid).forEach {
            dropSubtask(userUid, taskUid, it.uid)
        }
    }

}
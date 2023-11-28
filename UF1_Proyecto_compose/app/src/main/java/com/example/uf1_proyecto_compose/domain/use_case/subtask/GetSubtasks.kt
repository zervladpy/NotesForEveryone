package com.example.uf1_proyecto_compose.domain.use_case.subtask

import android.util.Log
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.domain.repository.SubtaskRepository
import com.example.uf1_proyecto_compose.utils.Response
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSubtasks
@Inject constructor(
    private val repository: SubtaskRepository,
    private val user: FirebaseAuth
) {

    operator fun invoke(

        taskUid: String,
    ): Flow<Response<List<Subtask>>> = flow {
        try {

            val userUid: String = user.currentUser?.uid ?: ""

            if (userUid.isEmpty()) {
                throw Exception("User is empty")
            }

            emit(Response.Loading())

            val result = repository.get(userUid, taskUid)

            Log.d("SubtaskGet UseCase", result.toString())

            emit(Response.Success(result))

        } catch (e: Exception) {
            // TODO (Exception handlers)

            Log.d("Get Subtask", e.toString())
        }
    }

}
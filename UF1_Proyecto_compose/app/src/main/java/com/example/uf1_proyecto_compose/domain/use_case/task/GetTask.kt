package com.example.uf1_proyecto_compose.domain.use_case.task

import android.util.Log
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import com.example.uf1_proyecto_compose.utils.Response
import com.example.uf1_proyecto_compose.utils.Response.Error
import com.example.uf1_proyecto_compose.utils.Response.Loading
import com.example.uf1_proyecto_compose.utils.Response.Success
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTask
@Inject constructor(
    private val repository: TaskRepository,
    private val user: FirebaseAuth
) {

    operator fun invoke(taskUid: String): Flow<Response<Task>> = flow {

        try {

            val userUid: String = user.currentUser?.uid ?: ""

            if (userUid.isEmpty()) {
                throw Exception("User is Empty")
            }

            emit(Loading())

            val result = repository.get(userUid, taskUid)

            Log.d("GetTask UseCase", result.toString())

            emit(Success(result))

        } catch (e: Exception) {
            /// TODO( Handle error catching)
            emit(Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

}
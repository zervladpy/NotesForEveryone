package com.example.uf1_proyecto_compose.domain.use_case.task

import android.util.Log
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import com.example.uf1_proyecto_compose.utils.Response
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTask
@Inject constructor(
    private val authRepository: AuthRepository,
    private val taskRepository: TaskRepository,
) {

    operator fun invoke(
        userUid: String = authRepository.currentUser!!.uid,
        taskUid: String,
    ): Flow<Response<Task>> = flow {
        try {
            emit(Response.Loading())

            val result = taskRepository.databaseGetByUid(userUid, taskUid)

            Log.d("Get Task Use Case", result.toString())

            emit(Response.Success(result))
        } catch (e: NullPointerException) {
            emit(Response.Error(message = "User is null"))
            Log.d("Get Task Use Case", e.toString())
        } catch (e: FirebaseFirestoreException) {
            Log.d("Get Task Use Case", e.toString())
            emit(Response.Error(message = "Firestore Error"))
        } catch (e: HttpException) {
            Log.d("Get Task Use Case", e.toString())
            emit(Response.Error(message = "Http Error"))
        } catch (e: IOException) {
            Log.d("Get Task Use Case", e.toString())
            emit(Response.Error(message = "No internet Connection"))
        }
    }
}
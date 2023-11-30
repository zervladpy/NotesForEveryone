package com.example.uf1_proyecto_compose.domain.use_case.task

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

class UpdateTask
@Inject constructor(
    private val repository: TaskRepository,
    private val authRepository: AuthRepository,
) {

    operator fun invoke(
        userUid: String = authRepository.user!!.uid,
        task: Task,
    ): Flow<Response<Unit>> = flow {
        try {
            emit(Response.Loading())

            repository.update(userUid, task)

            emit(Response.Success())

        } catch (e: NullPointerException) {

            emit(Response.Error(message = e.localizedMessage ?: "User is null"))

        } catch (e: FirebaseFirestoreException) {

            emit(Response.Error(message = e.localizedMessage ?: "Api Error"))

        } catch (e: HttpException) {

            emit(Response.Error(message = e.localizedMessage ?: "Unexpected Error"))

        } catch (e: IOException) {
            emit(Response.Error(message = e.localizedMessage ?: "No internet connection"))
        }

    }
}
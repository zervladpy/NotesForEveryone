package com.example.uf1_proyecto_compose.domain.use_case.task

import com.example.uf1_proyecto_compose.data.repository.TaskRepositoryImpl
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import com.example.uf1_proyecto_compose.utils.Response
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use Case to delete a Task from api
 * @param repository
 * @see TaskRepositoryImpl
 * @see Task
 * @see Response
 * */
class DeleteTask
@Inject constructor(
    private val repository: TaskRepositoryImpl,
    private val authRepository: AuthRepository,
) {

    /**
     *  TODO(Add Localizations)
     */

    suspend operator fun invoke(
        userUid: String = authRepository.user!!.uid, // replace
        taskUid: String,
    ): Flow<Response<Unit>> = flow {
        try {

            emit(Response.Loading())

            repository.delete(userUid, taskUid)

            emit(Response.Success())

        } catch (e: FirebaseFirestoreException) {

            emit(Response.Error(e.localizedMessage ?: "Firebase Exception"))

        } catch (e: HttpException) {

            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))

        } catch (e: IOException) {

            emit(Response.Error(e.localizedMessage ?: "No internet connection"))

        }
    }

}
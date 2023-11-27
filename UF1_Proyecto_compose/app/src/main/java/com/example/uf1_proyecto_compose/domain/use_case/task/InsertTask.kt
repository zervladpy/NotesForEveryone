package com.example.uf1_proyecto_compose.domain.use_case.task

import com.example.uf1_proyecto_compose.data.remote.auth.AuthApi
import com.example.uf1_proyecto_compose.data.repository.AppRepositoryImpl
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.utils.Response
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


/**
 * Use Case to insert Task to api
 * @param repository
 * @see AppRepositoryImpl
 * @see Task
 * @see Response
 * */
class InsertTask
@Inject constructor(
    private val repository: AppRepositoryImpl,
    private val authApi: AuthApi,
) {

    operator fun invoke(
        userUid: String = authApi.currentUser!!.uid,
        task: Task,
    ): Flow<Response<Unit>> = flow {
        try {

            /**
             * TODO (Implement synchronization)
             * */

            emit(Response.Loading())

            repository.apiInsert(userUid, task)

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
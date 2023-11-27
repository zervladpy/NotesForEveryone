package com.example.uf1_proyecto_compose.domain.use_case.task

import android.util.Log
import com.example.uf1_proyecto_compose.data.remote.auth.AuthApi
import com.example.uf1_proyecto_compose.data.repository.AppRepositoryImpl
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use Case to retrieve Tasks from api
 * @param repository
 * @see AppRepositoryImpl
 * @see Task
 * @see Response
 * */
class GetTasks
@Inject constructor(
    private val repository: AppRepositoryImpl,
    private val authApi: AuthApi,
) {

    /**
     *  TODO(Add Localizations)
     */

    /**
     * By default current user uid
     * */
    operator fun invoke(
        userUid: String = authApi.currentUser!!.uid,
    ): Flow<Response<List<Task>>> = flow {
        try {

            emit(Response.Loading())

            val result = repository.apiGetAll(userUid)

            Log.d("GetTasksUseCase", result.toString())

            /**
             * TODO (Make more stable)
             * */


        } catch (e: HttpException) {

            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))

        } catch (e: IOException) {

            emit(Response.Error(e.localizedMessage ?: "No internet connection"))

        }
    }

}
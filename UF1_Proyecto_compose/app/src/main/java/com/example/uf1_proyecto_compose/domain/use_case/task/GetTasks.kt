package com.example.uf1_proyecto_compose.domain.use_case.task

import com.example.uf1_proyecto_compose.data.remote.auth.AuthApi
import com.example.uf1_proyecto_compose.data.repository.TaskRepositoryImpl
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
 * @see TaskRepositoryImpl
 * @see Task
 * @see Response
 * */
class GetTasks
@Inject constructor(
    private val repository: TaskRepositoryImpl,
    private val authApi: AuthApi,
) {

    operator fun invoke(
        userUid: String = authApi.currentUser!!.uid,
    ): Flow<Response<List<Task>>> = flow {
        try {

            emit(Response.Loading())

            val result = repository.apiGetAll(userUid)

            emit(Response.Success(result))

        } catch (e: HttpException) {

            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))

        } catch (e: IOException) {

            emit(Response.Error(e.localizedMessage ?: "No internet connection"))

        }
    }

}
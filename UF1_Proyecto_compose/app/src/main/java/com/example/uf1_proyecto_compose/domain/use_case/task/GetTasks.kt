package com.example.uf1_proyecto_compose.domain.use_case.task

import com.example.uf1_proyecto_compose.data.repository.TaskRepositoryImpl
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetCurrentUserUseCase
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
    private val repository: TaskRepository,
    private val getUser: GetCurrentUserUseCase,
) {

    operator fun invoke(
        userUid: String = getUser().uid,
    ): Flow<Response<List<Task>>> = flow {
        try {

            if (userUid.isEmpty()) {
                throw Exception("User is Empty")
            }

            emit(Response.Loading())

            val result = repository.get(userUid)

            emit(Response.Success(result))

        } catch (e: HttpException) {

            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))

        } catch (e: IOException) {

            emit(Response.Error(e.localizedMessage ?: "No internet connection"))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: e.message!!))
        }
    }

}
package com.example.uf1_proyecto_compose.domain.use_case.auth

import com.example.uf1_proyecto_compose.data.repository.AuthRepositoryImpl
import com.example.uf1_proyecto_compose.utils.Response
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginAnonymously
@Inject constructor(
    private val repository: AuthRepositoryImpl,
) {

    operator fun invoke(

    ): Flow<Response<Unit>> = flow {
        try {
            emit(Response.Loading())

            repository.loginAnonymously()

            emit(Response.Success())
        } catch (e: FirebaseAuthException) {
            emit(Response.Error(e.errorCode))
        } catch (e: HttpException) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Response.Error(e.localizedMessage ?: "No internet"))
        }
    }
}
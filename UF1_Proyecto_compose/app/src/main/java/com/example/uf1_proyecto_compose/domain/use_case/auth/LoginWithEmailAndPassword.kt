package com.example.uf1_proyecto_compose.domain.use_case.auth

import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import com.example.uf1_proyecto_compose.utils.Response
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginWithEmailAndPassword
@Inject constructor(
    private val repository: AuthRepository,
    private val getUser: GetCurrentUserUseCase,
) {

    operator fun invoke(
        email: String,
        password: String,
    ): Flow<Response<User>> = flow {
        try {
            emit(Response.Loading())

            repository.loginWithEmailAndPassword(email, password)
            // getUser()?.let { repository.addUserToLocal(it) }

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
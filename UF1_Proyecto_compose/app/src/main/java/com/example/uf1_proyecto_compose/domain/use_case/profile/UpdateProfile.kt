package com.example.uf1_proyecto_compose.domain.use_case.profile

import android.net.Uri
import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import com.example.uf1_proyecto_compose.utils.Response
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateProfile
@Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(
        displayName: String,
        photoUri: Uri,
    ): Flow<Response<Unit>> = flow {

        try {
            emit(Response.Loading())
            authRepository.updateProfile(displayName, photoUri)
            emit(Response.Success())
        } catch (e: FirebaseAuthException) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error"))
        }

    }
}
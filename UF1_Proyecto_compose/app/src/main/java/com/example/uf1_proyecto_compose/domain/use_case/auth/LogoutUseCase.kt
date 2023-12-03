package com.example.uf1_proyecto_compose.domain.use_case.auth

import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import com.example.uf1_proyecto_compose.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogoutUseCase
@Inject constructor(
    private val repository: AuthRepository
) {

    /**
     *
     * */
    operator fun invoke(): Flow<Response<Unit>> = flow {

        try {

            repository.logout()


        } catch (e: Exception) {

        }

    }

}
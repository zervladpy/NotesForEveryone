package com.example.uf1_proyecto_compose.domain.use_case.auth

import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase
@Inject constructor(
    private val repository: AuthRepository,
) {
    /**
     *
     * */
    operator fun invoke(): User = repository.getCurrentUser()

}


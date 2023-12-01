package com.example.uf1_proyecto_compose.domain.use_case.auth

import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import javax.inject.Inject

/**
 *
 * */
class GetAuthState
@Inject constructor(
    private val authRepository: AuthRepository,
) {
    /**
     *
     * */
    operator fun invoke(): Boolean = authRepository.isAuthenticated()
}
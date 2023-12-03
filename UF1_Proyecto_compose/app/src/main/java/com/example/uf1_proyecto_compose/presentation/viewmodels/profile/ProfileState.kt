package com.example.uf1_proyecto_compose.presentation.viewmodels.profile

import com.example.uf1_proyecto_compose.domain.model.User

data class ProfileState(
    val isLoading: Boolean = false,
    val user: User = User(),
    val isEditing: Boolean = false,
)

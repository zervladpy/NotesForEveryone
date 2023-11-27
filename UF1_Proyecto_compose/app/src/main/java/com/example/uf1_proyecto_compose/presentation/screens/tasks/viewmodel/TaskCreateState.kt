package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

data class TaskCreateState(
    val isLoading: Boolean = false,
    val titleError: String = "",
    val errorMessage: String = "",
)

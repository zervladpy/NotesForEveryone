package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import com.example.uf1_proyecto_compose.domain.model.Task

data class TaskDetailState(
    val isLoading: Boolean = false,
    val initialTask: Task? = null,
    val task: Task? = null,
    val errorMessage: String = "",
)

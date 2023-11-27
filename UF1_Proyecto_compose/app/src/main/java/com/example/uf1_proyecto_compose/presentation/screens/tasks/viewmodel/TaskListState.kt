package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import com.example.uf1_proyecto_compose.domain.model.Task

data class TaskListState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val errorMessage: String = "",
)
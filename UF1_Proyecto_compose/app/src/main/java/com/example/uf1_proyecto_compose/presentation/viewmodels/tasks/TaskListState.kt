package com.example.uf1_proyecto_compose.presentation.viewmodels.tasks

import com.example.uf1_proyecto_compose.domain.model.Task

data class TaskListState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val error: String = ""
)
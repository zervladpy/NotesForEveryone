package com.example.uf1_proyecto_compose.presentation.screens.viewmodels.tasks.task

import com.example.uf1_proyecto_compose.domain.model.Subtask

data class TaskState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String = "",
    val description: String = "",
    val progression: Float = 0f,
    val subtasks: List<Subtask> = emptyList(),
)
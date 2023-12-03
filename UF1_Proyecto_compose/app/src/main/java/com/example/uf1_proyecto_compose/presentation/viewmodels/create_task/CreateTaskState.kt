package com.example.uf1_proyecto_compose.presentation.viewmodels.create_task

import com.example.uf1_proyecto_compose.domain.model.Task

/**
 * State for CreateTaskViewModel
 * @see CreateTaskViewModel
 * */
data class CreateTaskState(
    val isLoading: Boolean = false,
    val task: Task = Task(),
    val taskTitleError: String = "",
    val subtaskTitle: String = ""
)

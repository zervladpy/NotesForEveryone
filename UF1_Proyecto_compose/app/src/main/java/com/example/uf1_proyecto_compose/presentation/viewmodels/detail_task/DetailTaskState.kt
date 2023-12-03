package com.example.uf1_proyecto_compose.presentation.viewmodels.detail_task

import com.example.uf1_proyecto_compose.domain.model.Task

/**
 * State data class for DetailTaskViewModel
 * @see DetailTaskViewModel
 * */
data class DetailTaskState(
    val isLoading: Boolean = false,
    val isEditing: Boolean = false,
    val initialTask: Task = Task(),
    val task: Task = Task(),
    val taskTitleError: String = "",
    val subtaskTitle: String = ""
)

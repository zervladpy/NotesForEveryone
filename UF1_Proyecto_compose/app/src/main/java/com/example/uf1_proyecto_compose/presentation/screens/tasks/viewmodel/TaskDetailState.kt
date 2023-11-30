package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.domain.model.Task

data class TaskDetailState(
    val isLoading: Boolean = false,
    val initialTask: Task? = null,
    val task: Task? = null,
    val errorMessage: String = "",
)

fun TaskDetailState.copyWith(
    isLoading: Boolean? = null,
    task: Task? = null,
    subtasks: List<Subtask>? = null,
    errorMessage: String? = null,
): TaskDetailState {
    return TaskDetailState(
        isLoading = isLoading ?: this.isLoading,
        task = task ?: this.task,
        errorMessage = errorMessage ?: this.errorMessage
    )
}
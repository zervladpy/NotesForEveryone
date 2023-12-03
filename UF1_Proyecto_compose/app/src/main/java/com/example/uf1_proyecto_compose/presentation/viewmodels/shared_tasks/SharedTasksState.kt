package com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks

import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.model.User

/**
 *
 * */
data class SharedTasksState(
    val isLoading: Boolean = false,
    val user: User = User(),
    val tasks: List<Task> = emptyList(),
    val searchTerm: String = "",
    val searchList: List<Task> = emptyList()
)
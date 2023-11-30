package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.uf1_proyecto_compose.domain.model.Task

data class TaskListState(
    val isLoading: Boolean = false,
    val tasks: MutableLiveData<List<Task>>? = null,
    val errorMessage: String = "",
)
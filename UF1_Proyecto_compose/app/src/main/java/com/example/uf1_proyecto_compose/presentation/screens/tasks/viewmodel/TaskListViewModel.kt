package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel
@Inject constructor(
    private val getTasks: GetTasks,
) : ViewModel() {

    private val _state = mutableStateOf(TaskListState())
    val state: State<TaskListState> = _state

    init {
        _state.value = state.value.copy(
            tasks = getTasks.liveTasks
        )
    }

}
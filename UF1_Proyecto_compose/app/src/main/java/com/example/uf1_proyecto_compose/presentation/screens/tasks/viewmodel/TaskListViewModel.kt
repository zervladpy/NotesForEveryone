package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTasks
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel
@Inject constructor(
    private val getTasks: GetTasks,
) : ViewModel() {

    private val _state = mutableStateOf(TaskListState())
    val state: State<TaskListState> = _state

    init {
        getTasks().onEach { response ->

            Log.d("TaskViewModel", response.data.toString())

            when (response) {


                is Response.Loading -> {
                    _state.value = TaskListState(isLoading = true)
                }

                is Response.Success -> {
                    _state.value = TaskListState(tasks = response.data ?: emptyList())
                }

                is Response.Error -> {
                    _state.value =
                        TaskListState(errorMessage = response.message ?: "Unexpected Error")
                }
            }
        }.launchIn(viewModelScope)

    }

}
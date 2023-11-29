package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.subtask.GetSubtasks
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTask
import com.example.uf1_proyecto_compose.utils.Response.Error
import com.example.uf1_proyecto_compose.utils.Response.Loading
import com.example.uf1_proyecto_compose.utils.Response.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel
@Inject constructor(
    private val getTask: GetTask,
    private val getSubtasks: GetSubtasks,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(TaskDetailState())
    val state: State<TaskDetailState> = _state

    init {

        val taskUid: String = savedStateHandle.get<String>("taskUid") ?: ""

        init(taskUid)

    }

    private fun init(taskUid: String) {

        getTask(taskUid).onEach { response ->

            when (response) {
                is Loading -> {
                    _state.value = state.value.copyWith(isLoading = true)
                }

                is Error -> {
                    _state.value =
                        state.value.copyWith(
                            isLoading = false,
                            errorMessage = response.message ?: "Failed to load task"
                        )
                }

                is Success -> {
                    _state.value = state.value.copyWith(
                        isLoading = false,
                        task = response.data!!
                    )
                    
                }
            }

        }.onCompletion {

            getSubtasks(taskUid).onEach { response ->
                when (response) {
                    is Loading -> {
                        _state.value = state.value.copyWith(
                            isLoading = true
                        )
                    }

                    is Error -> {
                        _state.value = state.value.copyWith(
                            isLoading = false,
                            errorMessage = response.message ?: "Failed to load subtasks"
                        )
                    }

                    is Success -> {
                        _state.value = state.value.copyWith(
                            isLoading = false,
                            subtasks = response.data ?: emptyList()
                        )
                    }
                }
            }.launchIn(viewModelScope)

        }.launchIn(viewModelScope)


    }


}
package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTask
import com.example.uf1_proyecto_compose.domain.use_case.task.UpdateTask
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel
@Inject constructor(
    private val getTask: GetTask,
    private val updateTask: UpdateTask,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(TaskDetailState())
    val state: State<TaskDetailState> = _state

    init {

        Log.d("SaveStateHandle", savedStateHandle.get<String>("taskUid") ?: "Null")

        get(savedStateHandle.get<String>("taskUid") ?: "")
    }

    fun get(
        uid: String,
    ) {
        getTask(taskUid = uid).onEach { response ->
            when (response) {
                is Response.Loading -> {

                    _state.value = TaskDetailState(isLoading = true)

                }

                is Response.Error -> {
                    _state.value =
                        TaskDetailState(
                            errorMessage = response.message ?: "Unexpected Error"
                        )
                }

                is Response.Success -> {

                    _state.value = TaskDetailState(task = response.data!!)

                }
            }
        }.launchIn(viewModelScope)
    }

}
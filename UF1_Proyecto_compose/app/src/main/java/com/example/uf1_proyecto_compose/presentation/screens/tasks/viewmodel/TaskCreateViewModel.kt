package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.use_case.task.InsertTask
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel
@Inject constructor(
    private val insertTask: InsertTask,
) : ViewModel() {

    private val _state = mutableStateOf(TaskCreateState())
    val state: State<TaskCreateState> = _state

    fun createTask(
        title: String,
        description: String,
    ) {

        if (state.value.isLoading) return

        checkTitle(title)

        if (state.value.titleError.isNotEmpty()) return

        val task = Task(
            uid = UUID.randomUUID().toString(),
            title = title,
            description = description
        )

        insertTask(task = task).onEach { response ->
            when (response) {
                is Response.Loading -> {
                    _state.value = TaskCreateState(isLoading = true)
                }

                is Response.Success -> {
                    _state.value = TaskCreateState()
                }

                is Response.Error -> {
                    _state.value =
                        TaskCreateState(
                            errorMessage = response.message ?: "Unexpected Error"
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun checkTitle(value: String) {
        if (value.isEmpty()) {
            _state.value = TaskCreateState(titleError = "Field must not be empty")
        } else {
            _state.value = TaskCreateState(titleError = "")
        }
    }

}
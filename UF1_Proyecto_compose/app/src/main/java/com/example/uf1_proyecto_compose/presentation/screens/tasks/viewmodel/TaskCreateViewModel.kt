package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.model.Subtask
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

    val tasks = state.value.subtasks

    fun addSubtask(title: String) {
        val subtask = Subtask(
            uid = UUID.randomUUID().toString(),
            title = title,
        )
        val currentState = state.value

        val subtasks = currentState.subtasks.toMutableList().apply { add(subtask) }.toList()

        _state.value = state.value.copy(
            subtasks = subtasks
        )

    }

    fun removeSubtask(item: Subtask) {
        val currentState = state.value

        val subtasks = currentState.subtasks.toMutableList().apply { remove(item) }.toList()

        _state.value = state.value.copy(
            subtasks = subtasks
        )
    }

    fun createTask(
        onStateChange: (String?) -> Unit,
        onComplete: (String) -> Unit
    ) {

        val task = Task(
            uid = UUID.randomUUID().toString(),
            title = state.value.title,
            description = state.value.description,
            subtasks = state.value.subtasks
        )

        insertTask(task = task).onEach { response ->
            when (response) {
                is Response.Error -> {
                    _state.value = state.value.copy(isLoading = false)

                    onStateChange(response.message)
                }

                is Response.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }

                is Response.Success -> {
                    _state.value = state.value.copy(isLoading = false)
                    onComplete(task.uid)
                }
            }

        }.launchIn(viewModelScope)

    }

    fun onTitleChange(value: String) {

        _state.value = state.value.copy(
            title = value
        )

        if (value.isEmpty()) {
            _state.value = state.value.copy(
                titleError = "* Required Field"
            )
        } else {
            _state.value = state.value.copy(
                titleError = ""
            )
        }
    }

    fun onDescriptionChange(value: String) {

        _state.value = state.value.copy(
            description = value
        )

    }

}

data class TaskCreateState(
    val isLoading: Boolean = false,
    val title: String = "",
    val titleError: String = "",
    val description: String = "",
    val subtasks: List<Subtask> = emptyList()
)
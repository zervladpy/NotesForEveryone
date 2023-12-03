package com.example.uf1_proyecto_compose.presentation.viewmodels.create_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.domain.model.Subtask
import java.util.UUID

/**
 *
 * */
class CreateTaskViewModel : ViewModel() {

    private val _state = mutableStateOf(CreateTaskState())
    val state: State<CreateTaskState> = _state

    init {
        onInit()
    }

    /**
     * Responsible to init the View Model
     * */
    private fun onInit() {
        _state.value = state.value.copy(
            task = state.value.task.copy(
                uid = UUID.randomUUID().toString()
            ),
            taskTitleError = "* Required Field"
        )
    }

    /**
     * Responsible to handle view model events
     * @see CreateTaskEvent
     * */
    fun onEvent(event: CreateTaskEvent) {
        when (event) {
            is CreateTaskEvent.TaskDescriptionChanged -> descriptionChanged(event.value)
            is CreateTaskEvent.TaskSubtaskAdd -> addSubtask()
            is CreateTaskEvent.TaskSubtaskRemove -> removeSubtask(event.value)
            is CreateTaskEvent.TaskSubtaskTitleChanged -> subtaskTitleChanged(event.value)
            is CreateTaskEvent.TaskTitleChanged -> titleChanged(event.value)
        }
    }

    /**
     *
     * */
    private fun titleChanged(value: String) {
        _state.value = state.value.copy(
            task = state.value.task.copy(
                title = value
            ),
            taskTitleError = if (value.isEmpty()) {
                "* Required Field"
            } else {
                ""
            }
        )
    }

    /**
     *
     * */
    private fun descriptionChanged(value: String) {
        _state.value = state.value.copy(
            task = state.value.task.copy(
                description = value
            )
        )
    }

    /**
     *
     * */
    private fun subtaskTitleChanged(value: String) {
        _state.value = state.value.copy(
            subtaskTitle = value
        )
    }

    /**
     *
     * */
    private fun addSubtask() {

        // Guard clause subtask is empty
        if (state.value.subtaskTitle.isEmpty()) return

        val currentState = state.value

        val subtask = Subtask(
            uid = UUID.randomUUID().toString(),
            title = state.value.subtaskTitle
        )

        _state.value = currentState.copy(
            task = currentState.task.copy(
                subtasks = currentState.task.subtasks.toMutableList().apply { add(subtask) }
                    .toList()
            ),
            subtaskTitle = ""
        )
    }

    /**
     *
     * */
    private fun removeSubtask(subtaskUid: String) {
        val currentState = state.value


        _state.value = currentState.copy(
            task = currentState.task.copy(
                subtasks = currentState.task.subtasks.toMutableList().apply {
                    remove(this.find { it.uid == subtaskUid })
                }.toList()
            ),
        )
    }
}
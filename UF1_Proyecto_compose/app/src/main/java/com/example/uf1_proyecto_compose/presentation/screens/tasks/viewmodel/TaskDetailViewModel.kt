package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.domain.use_case.task.DeleteTask
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTasks
import com.example.uf1_proyecto_compose.domain.use_case.task.UpdateTask
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getTasks: GetTasks,
    private val updateTask: UpdateTask,
    private val deleteTask: DeleteTask,
) : ViewModel() {

    private val _state = mutableStateOf(TaskDetailState())
    val state: State<TaskDetailState> = _state

    init {

        val taskUid: String = savedStateHandle.get<String>("taskUid") ?: ""

        val task = getTasks.liveTasks.value?.toList()?.find {
            it.uid == taskUid
        }


        _state.value = state.value.copy(initialTask = task, task = task)

    }

    fun setTitle(value: String) {

        val editedTask = state.value.task!!.copy(
            title = value
        )

        _state.value = state.value.copy(
            task = editedTask
        )

    }

    fun setDescription(value: String) {
        val editedTask = state.value.task!!.copy(
            description = value
        )

        _state.value = state.value.copy(
            task = editedTask
        )
    }

    fun addSubtask(subtask: Subtask) {

    }

    fun removeSubtask(subtask: Subtask) {}
    fun toggleDoneSubtask(subtask: Subtask) {}
    fun setProgression(value: Float) {}

    fun reset() {
        _state.value = state.value.copy(
            task = state.value.initialTask
        )
    }

    fun update(
        notify: (message: String) -> Unit,
    ) {

        val task = state.value.task!!

        updateTask(task.uid, task).onEach {
            when (it) {
                is Response.Error -> {
                    notify(it.message ?: "Update error")
                }

                is Response.Loading -> {

                }

                is Response.Success -> {


                    _state.value = state.value.copy(
                        initialTask = state.value.task
                    )

                    notify("Task updated")

                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.example.uf1_proyecto_compose.presentation.viewmodels.detail_task

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.example.uf1_proyecto_compose.domain.model.Task
import java.util.UUID

/**
 *
 * */
class DetailTaskViewModel : ViewModel() {

    private val _state = mutableStateOf(DetailTaskState())
    val state: State<DetailTaskState> = _state

    /**
     * Sets the initial task
     * */
    fun setInitialValues(
        task: Task
    ) {
        _state.value = state.value.copy(
            initialTask = task,
            task = task
        )
    }

    fun onEvent(event: DetailTaskEvent) {

        when (event) {
            is DetailTaskEvent.ResetInitial -> reset()
            is DetailTaskEvent.SubtaskMark -> changeTaskStatus(event.value!!, event.mark!!)
            is DetailTaskEvent.AddSubtask -> addSubtask()
            is DetailTaskEvent.RemoveSubtask -> removeSubtask(event.value!!)
            is DetailTaskEvent.TaskDescriptionChanged -> descriptionChange(event.value!!)
            is DetailTaskEvent.TaskProgressionChanged -> changeProgression(event.progression!!)
            is DetailTaskEvent.TaskSubtaskTitleChanged -> subtaskTitleChange(event.value!!)
            is DetailTaskEvent.TitleTaskChanged -> titleChange(event.value!!)
            is DetailTaskEvent.ToggleEditing -> toggleEditing()
        }
    }

    /**
     *
     * */
    private fun reset() {
        val initialValue = state.value.initialTask
        _state.value = DetailTaskState(initialTask = initialValue, task = initialValue)
    }

    /**
     *
     * */
    private fun toggleEditing() {
        _state.value = state.value.copy(
            isEditing = !state.value.isEditing
        )
    }

    /**
     *
     * */
    private fun titleChange(value: String) {

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
    private fun descriptionChange(value: String) {

        val task = state.value.task.copy(
            description = value
        )

        _state.value = state.value.copy(
            task = task
        )
    }

    /**
     *
     * */
    private fun subtaskTitleChange(value: String) {
        _state.value = state.value.copy(
            subtaskTitle = value
        )
    }

    /**
     *
     * */
    private fun addSubtask() {

        val subtaskTitle = state.value.subtaskTitle

        if (subtaskTitle.isEmpty()) return

        val subtask = Subtask(
            uid = UUID.randomUUID().toString(),
            title = subtaskTitle
        )

        _state.value = state.value.copy(
            task = state.value.task.copy(
                subtasks = state.value.task.subtasks.toMutableList().apply { add(subtask) }.toList()
            ),
            subtaskTitle = ""
        )

    }

    /**
     *
     * */
    private fun removeSubtask(subtaskUid: String) {

        _state.value = state.value.copy(
            task = state.value.task.copy(
                subtasks = state.value.task.subtasks.toMutableList().apply {
                    remove(this.find { it.uid == subtaskUid })
                }.toList()
            )
        )

    }

    /**
     *
     * */
    private fun changeTaskStatus(subtaskUid: String, mark: Boolean) {
        _state.value = state.value.copy(
            task = state.value.task.copy(
                subtasks = state.value.task.subtasks.toMutableList().apply {

                    val subtask = this.find { it.uid == subtaskUid }

                    remove(subtask)

                    add(subtask!!.copy(done = mark))

                }.toList()
            )
        )
    }

    /**
     *
     * */
    private fun changeProgression(value: Float) {

        Log.wtf("Progression Float", value.toString())
        Log.wtf("Progression Float", (value == 1f).toString())
        Log.wtf("Progression Int", (value * 100).toInt().toString())

        val task = state.value.task.copy(
            progression = value,
            done = value == 1f
        )

        _state.value = state.value.copy(
            task = task
        )

    }
}
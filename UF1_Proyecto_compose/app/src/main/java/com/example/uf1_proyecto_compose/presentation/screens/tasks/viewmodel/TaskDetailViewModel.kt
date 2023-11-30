package com.example.uf1_proyecto_compose.presentation.screens.tasks.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getTasks: GetTasks,
) : ViewModel() {

    private val _state = mutableStateOf(TaskDetailState())
    val state: State<TaskDetailState> = _state

    init {

        val taskUid: String = savedStateHandle.get<String>("taskUid") ?: ""

        init(taskUid)

    }

    private fun init(taskUid: String) {

        val task = getTasks.liveTasks.value?.toList()?.find {
            it.uid == taskUid
        }

        _state.value = state.value.copy(task = task)

    }


}
package com.example.uf1_proyecto_compose.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.GetTasksUseCase
import com.example.uf1_proyecto_compose.domain.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel
@Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    private var _tasks by mutableStateOf(emptyList<Task>())

    val tasks: List<Task>
        get() = _tasks


    fun onCrete() {
        viewModelScope.launch {
            val result = getTasksUseCase()
            _tasks = result

        }

    }

}
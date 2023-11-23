package com.example.uf1_proyecto_compose.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.GetTasksUseCase
import com.example.uf1_proyecto_compose.domain.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TasksViewModel
@Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    private var _tasks by mutableStateOf(emptyList<Task>())

    val tasks: List<Task>
        get() = _tasks

    fun onCreate() {
        viewModelScope.launch {
            val result = getTasksUseCase()

            Log.d("TasksViewModel", result.toString())

            _tasks = result
        }
    }

    fun addTask(title: String, description: String = "") {

        // TODO (Implement remote and local database)

        val newTask = Task(
            uid = UUID.randomUUID().toString(),
            title = title,
            description = description,
            done = false,
        )

        val newTasks = mutableListOf(newTask)

        _tasks.forEach { newTasks.add(it) }

        _tasks = newTasks.toList()

    }

    fun updateTask(
        task: Task,
        title: String? = null,
        description: String? = null,
        done: Boolean? = null
    ) {

        // TODO (Implement remote and local database)

        val updatedTask = Task(
            uid = task.uid,
            title = title ?: task.title,
            description = description ?: task.description,
            done = done ?: task.done
        )

        _tasks = tasks.map { if (it == task) updatedTask else it }

    }

    fun deleteTask(task: Task) {

        // TODO (Implement remote and local database)

        _tasks = tasks.filter { it.uid != task.uid }

    }

}
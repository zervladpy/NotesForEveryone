package com.example.uf1_proyecto_compose.presentation.task_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uf1_proyecto_compose.data.respoitory.TaskRepositoryImpl
import com.example.uf1_proyecto_compose.domain.model.Task
import java.util.UUID
import javax.inject.Inject

class TaskDetailViewModel
@Inject constructor(
    private val taskRepository: TaskRepositoryImpl,
) : ViewModel() {

    private val _task: LiveData<Task> = MutableLiveData<Task>().apply {
        Task("", "", "", false)
    }
    val task: LiveData<Task>
        get() = _task

    suspend fun createTask(
        title: String,
        description: String? = null,
        done: Boolean = false,
    ) {

        val newTask = Task(
            uid = UUID.randomUUID().toString(),
            title = title,
            description = description ?: "",
            done = done
        )

        _task.value = newTask

        taskRepository.createTask(newTask)

    }

}
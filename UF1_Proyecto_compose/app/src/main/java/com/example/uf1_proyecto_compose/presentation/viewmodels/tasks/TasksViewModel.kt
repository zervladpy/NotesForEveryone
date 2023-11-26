package com.example.uf1_proyecto_compose.presentation.viewmodels.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetCurrentUserUseCase
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTasks
import com.example.uf1_proyecto_compose.domain.use_case.task.InsertTask
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TasksViewModel
@Inject constructor(
    private val getTasksUseCase: GetTasks,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val insertTaskUseCase: InsertTask
) : ViewModel() {

    private val _state = mutableStateOf(TaskListState())
    val state: State<TaskListState> = _state

    private val _taskState = mutableStateOf(TaskState())
    val taskState: State<TaskState> = _taskState

    init {
        getTasks()
    }

    private fun getTasks() {

        val user = getCurrentUserUseCase()

        if (user?.uid?.isNotEmpty() == true)

            getTasksUseCase(user.uid).onEach { result ->

                when (result) {
                    is Response.Success -> {
                        _state.value = TaskListState(tasks = result.data!!)
                    }

                    is Response.Error -> {
                        _state.value = TaskListState(error = result.message ?: "Unexpected Error")
                    }

                    is Response.Loading -> {
                        _state.value = TaskListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun insertTask(
        title: String,
        description: String,
        showSnackbar: (String) -> Unit = {}
    ) {

        val user = getCurrentUserUseCase()

        val task = Task(
            uid = UUID.randomUUID().toString(),
            title = title,
            description = description
        )

        insertTaskUseCase(user!!.uid, task).onEach { response ->
            when (response) {
                is Response.Success -> {
                    showSnackbar("Task created!")

                }

                is Response.Error -> {
                    showSnackbar(response.message ?: "Unexpected Error")
                }

                is Response.Loading -> {
                    showSnackbar("Inserting ...")
                }
            }

        }.launchIn(viewModelScope)

    }

}
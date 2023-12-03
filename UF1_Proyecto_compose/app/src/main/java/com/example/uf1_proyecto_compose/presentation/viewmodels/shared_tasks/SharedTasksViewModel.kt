package com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.use_case.auth.GetCurrentUserUseCase
import com.example.uf1_proyecto_compose.domain.use_case.task.DeleteTask
import com.example.uf1_proyecto_compose.domain.use_case.task.GetTasks
import com.example.uf1_proyecto_compose.domain.use_case.task.InsertTask
import com.example.uf1_proyecto_compose.domain.use_case.task.UpdateTask
import com.example.uf1_proyecto_compose.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Tasks View Model
 * @param getUser Get current user instance
 * @param getTasks Retrieve tasks from repository use case
 * @param insertTask Insert task into repository use case
 * @param updateTask Update task in the repository use case
 * @param deleteTask Delete task from the repository use case
 * @see ViewModel Inherited class
 * @see SharedTasksEvent Events for View Model
 * @see SharedTasksState State of the View Model
 * */
@HiltViewModel
class SharedTasksViewModel
@Inject constructor(
    private val getUser: GetCurrentUserUseCase,
    private val getTasks: GetTasks,
    private val insertTask: InsertTask,
    private val updateTask: UpdateTask,
    private val deleteTask: DeleteTask
) : ViewModel() {

    /**
     * View Model state
     * */
    private val _state = mutableStateOf(SharedTasksState())
    val state: State<SharedTasksState> = _state

    init {
        Log.d("Shared Task ON INIT", "true")
        onInit()
    }

    /**
     * Responsible to init the View Model
     * */
    private fun onInit() {

        _state.value = state.value.copy(
            isLoading = true
        )

        val userUid = getUser().uid

        Log.d("CREATED", "Shared Tasks View Model")

        // Guard for user is not identified
        if (userUid.isEmpty()) {
            Log.d("SharedTasksViewModel user is empty", userUid)
        }


        retrieve(userUid)

        _state.value = state.value.copy(
            isLoading = false
        )

    }

    /**
     * Retrieves the data from the repository
     * */
    private fun retrieve(userUid: String) {
        Log.d("Shared Tasks ViewModel", userUid)
        getTasks(userUid = userUid).onEach {
            when (it) {
                is Response.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                    Log.d("Retrieving tasks", it.message ?: "Loading")
                }

                is Response.Error -> {
                    _state.value = state.value.copy(isLoading = false)
                    Log.d("Retrieving tasks", it.message ?: "Unexpected Error")
                }

                is Response.Success -> {

                    val newData: List<Task> = it.data!!

                    _state.value = SharedTasksState(
                        isLoading = false,
                        tasks = newData
                    )
                    Log.d("Retrieving tasks", it.message ?: "Success")
                    Log.d("Retrieving tasks", it.message ?: it.data.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Responsible to handle view model events
     * @see SharedTasksEvent
     * */
    fun onEvent(event: SharedTasksEvent) {

        when (event) {
            is SharedTasksEvent.AddTask -> add(event.task)
            is SharedTasksEvent.RemoveTask -> remove(event.task)
            is SharedTasksEvent.UpdateTask -> update(event.task)
        }

    }

    /**
     * Responsible for handle add task event
     * */
    private fun add(task: Task) {

        val userUid = getUser().uid

        if (userUid.isEmpty()) return

        insertTask(
            userUid = userUid,
            task = task
        ).onEach {
            when (it) {
                is Response.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                    Log.d("Loading Tasks", it.message ?: "Loading tasks")
                }

                is Response.Error -> {
                    _state.value = state.value.copy(isLoading = false)
                    Log.d("Add task", it.message ?: "Unexpected Error")
                }

                is Response.Success -> {
                    _state.value = state.value.copy(isLoading = false)
                    Log.d("Add task", it.message ?: "Success")
                    refresh()
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Responsible to handle a remove event
     * */
    private fun remove(task: Task) {
        val userUid = getUser().uid

        if (userUid.isEmpty()) return

        deleteTask(
            userUid = userUid,
            taskUid = task.uid
        ).onEach {
            when (it) {
                is Response.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                    Log.d("Add task", it.message ?: "Loading")
                }

                is Response.Error -> {
                    _state.value = state.value.copy(isLoading = false)
                    Log.d("Remove task", it.message ?: "Unexpected Error")
                }

                is Response.Success -> {
                    Log.d("REMOVE", "task removed")
                    _state.value = state.value.copy(
                        isLoading = false,
                        tasks = state.value.tasks.toMutableList().apply {
                            remove(this.find { t -> t == task })
                        }.toList()
                    )
                    Log.d("Remove task", it.message ?: "Success")
                    refresh()
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Responsible to handle a update event
     * */
    private fun update(task: Task) {
        val userUid = getUser().uid

        if (userUid.isEmpty()) return

        updateTask(
            userUid = userUid,
            task = task
        ).onEach {
            when (it) {
                is Response.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                    Log.d("Updating task", it.message ?: "Loading")
                }

                is Response.Error -> {
                    _state.value = state.value.copy(isLoading = false)
                    Log.d("Updating task", it.message ?: "Unexpected Error")
                }

                is Response.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        tasks = state.value.tasks.toMutableList().apply {
                            remove(this.find { t -> t == task })
                            add(task)
                        }.toList()
                    )
                    Log.d("Updating task", it.message ?: "Success")
                    refresh()
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Responsible to handle refresh event
     * */
    private fun refresh() {

        val userUid = getUser().uid

        if (userUid.isEmpty()) return

        retrieve(userUid)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("CLEAR", "Shared Tasks View Model")
    }
}
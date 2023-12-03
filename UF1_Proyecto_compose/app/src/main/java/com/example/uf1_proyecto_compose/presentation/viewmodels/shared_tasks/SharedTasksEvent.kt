package com.example.uf1_proyecto_compose.presentation.viewmodels.shared_tasks

import com.example.uf1_proyecto_compose.domain.model.Task

/**
 * Events for SharedTaskViewModel
 * @see SharedTasksViewModel
 * */
sealed class SharedTasksEvent(val task: Task) {
    class AddTask(task: Task) : SharedTasksEvent(task)
    class RemoveTask(task: Task) : SharedTasksEvent(task)
    class UpdateTask(task: Task) : SharedTasksEvent(task)

}
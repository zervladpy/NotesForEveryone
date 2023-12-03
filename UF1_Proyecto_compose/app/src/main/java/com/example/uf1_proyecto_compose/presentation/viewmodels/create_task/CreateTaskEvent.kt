package com.example.uf1_proyecto_compose.presentation.viewmodels.create_task

/**
 * Event class for CreateTaskViewModel
 * @see CreateTaskViewModel
 * */
sealed class CreateTaskEvent(val value: String) {
    class TaskTitleChanged(value: String) : CreateTaskEvent(value)
    class TaskDescriptionChanged(value: String) : CreateTaskEvent(value)
    class TaskSubtaskTitleChanged(value: String) : CreateTaskEvent(value)
    class TaskSubtaskAdd : CreateTaskEvent(value = "")
    class TaskSubtaskRemove(value: String) : CreateTaskEvent(value)
}

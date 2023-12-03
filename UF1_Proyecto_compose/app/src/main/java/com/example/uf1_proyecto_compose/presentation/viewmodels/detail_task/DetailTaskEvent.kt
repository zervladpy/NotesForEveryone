package com.example.uf1_proyecto_compose.presentation.viewmodels.detail_task

sealed class DetailTaskEvent(
    val value: String? = null,
    val progression: Float? = null,
    val mark: Boolean? = null
) {
    class ResetInitial : DetailTaskEvent()
    class ToggleEditing(value: Boolean) : DetailTaskEvent(mark = value)
    class TitleTaskChanged(value: String) : DetailTaskEvent(value)
    class TaskDescriptionChanged(value: String) : DetailTaskEvent(value)
    class TaskProgressionChanged(value: Float) : DetailTaskEvent(progression = value)
    class TaskSubtaskTitleChanged(value: String) : DetailTaskEvent(value)
    class SubtaskMark(value: String, mark: Boolean) : DetailTaskEvent(value = value, mark = mark)
    class RemoveSubtask(value: String) : DetailTaskEvent(value)
    class AddSubtask : DetailTaskEvent()
}

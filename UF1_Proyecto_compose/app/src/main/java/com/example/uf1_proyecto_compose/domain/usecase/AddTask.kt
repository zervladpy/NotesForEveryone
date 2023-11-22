package com.example.uf1_proyecto_compose.domain.usecase

import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository

class AddTask(
    private val tasksRepository: TaskRepository,
    private val task: Task
) {
    private suspend operator fun invoke() = tasksRepository.createTask(task)
}
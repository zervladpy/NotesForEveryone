package com.example.uf1_proyecto_compose.domain.usecase

import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository

class CreateTask(
    private val taskRepository: TaskRepository,
    task: Task
) {

    suspend operator fun invoke() = taskRepository.saveTask(task)

}
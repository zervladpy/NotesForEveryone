package com.example.uf1_proyecto_compose.domain.use_case.task

import javax.inject.Inject

data class TaskUsecase
@Inject constructor(
    private val getTask: GetTask,
    private val getTasks: GetTasks,
    private val insertTask: InsertTask,
    private val updateTask: UpdateTask,
    private val deleteTask: DeleteTask,
)
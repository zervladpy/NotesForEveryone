package com.example.uf1_proyecto_compose.domain.model

/**
 * Class to handle home screen
 * */
data class UserData(
    val totalTasks: Int = 0,
    val completedTasks: Int = 0,
    val lastCompletedTasks: List<Task>
)

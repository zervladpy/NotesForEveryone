package com.example.uf1_proyecto_compose.data.database.repository

interface TaskApiClient {

    fun getTasks(): List<TaskModel>

}
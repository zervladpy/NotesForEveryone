package com.example.uf1_proyecto_compose.data.database.repository

import com.google.gson.annotations.SerializedName

data class TaskModel(
    @SerializedName("uid") val uid: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("done") val done: Boolean = false
)

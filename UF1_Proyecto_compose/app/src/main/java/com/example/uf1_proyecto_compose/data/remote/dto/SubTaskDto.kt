package com.example.uf1_proyecto_compose.data.remote.dto

import com.example.uf1_proyecto_compose.domain.model.SubTask
import com.google.gson.annotations.SerializedName

data class SubTaskDto(

    @SerializedName("title")
    val title: String,
    @SerializedName("done")
    val done: Boolean
)

fun SubTaskDto.toDomain(): SubTask {
    return SubTask(
        title = title,
        done = done
    )
}
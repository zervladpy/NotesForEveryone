package com.example.uf1_proyecto_compose.data.remote.dto

import com.example.uf1_proyecto_compose.domain.model.Subtask
import com.google.gson.annotations.SerializedName

data class SubtaskDto(

    @SerializedName("uid")
    val uid: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("done")
    val done: Boolean = false
)

fun SubtaskDto.toDomain(): Subtask {
    return Subtask(
        uid = uid,
        title = title,
        done = done
    )
}
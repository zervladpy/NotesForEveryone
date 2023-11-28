package com.example.uf1_proyecto_compose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("uid")
    val uid: String,
    @SerializedName("title")
    val title: String
)
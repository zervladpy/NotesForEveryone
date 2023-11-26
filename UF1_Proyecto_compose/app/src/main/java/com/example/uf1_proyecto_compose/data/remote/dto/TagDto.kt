package com.example.uf1_proyecto_compose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("label") val label: String
)


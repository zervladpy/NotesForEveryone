package com.example.uf1_proyecto_compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_uid")
    val userUid: String,
    @ColumnInfo(name = "title")
    val title: String
)
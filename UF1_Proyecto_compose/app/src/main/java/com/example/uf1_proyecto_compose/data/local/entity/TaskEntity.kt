package com.example.uf1_proyecto_compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uf1_proyecto_compose.utils.constraint.TableConstraints

@Entity(tableName = TableConstraints.TASK_TABLE)
data class TaskEntity(
    @PrimaryKey
    val uid: String,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "done")
    val done: Boolean = false,
    @ColumnInfo(name = "creation_date")
    val creationDate: String = "",
    @ColumnInfo(name = "synced")
    val synced: Boolean = false
)

package com.example.uf1_proyecto_compose.data.source.local.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uf1_proyecto_compose.domain.model.Task

@Entity(tableName = TaskEntity.TABLE_NAME)
data class TaskEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "title") val description: String,
    @ColumnInfo(name = "done") val done: Boolean,
) {

    companion object {
        const val TABLE_NAME = "tasks_table"
    }
}

fun TaskEntity.toTask() = Task(uid, title, description, done)



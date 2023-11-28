package com.example.uf1_proyecto_compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uf1_proyecto_compose.domain.model.Subtask

@Entity(tableName = "subtasks")
data class SubTaskEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val uid: String,
    @ColumnInfo("task_id")
    val taskUid: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo(name = "done")
    val done: Boolean = false
)

fun SubTaskEntity.toDomain(): Subtask {
    return Subtask(
        uid = taskUid,
        title = title,
        done = done
    )
}

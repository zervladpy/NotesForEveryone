package com.example.uf1_proyecto_compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uf1_proyecto_compose.domain.model.SubTask

@Entity(tableName = "subtasks")
data class SubTaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val uid: Int = 0,
    @ColumnInfo("task_id")
    val taskUid: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo(name = "done")
    val done: Boolean = false
)

fun SubTaskEntity.toDomain(): SubTask {
    return SubTask(
        title = title,
        done = done
    )
}

package com.example.uf1_proyecto_compose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.uf1_proyecto_compose.domain.model.Task
import com.example.uf1_proyecto_compose.utils.constraint.TableConstraint
import com.example.uf1_proyecto_compose.utils.formatter.FormatterConstraint
import java.time.LocalDateTime

@Entity(tableName = TableConstraint.TASK_TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val uid: String,
    @ColumnInfo(name = "user_id")
    val userUid: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "done")
    val done: Boolean = false,
    @ColumnInfo(name = "creation_date")
    val creationDate: String = "",
    @ColumnInfo(name = "synced")
    val synced: Boolean = false,
    @ColumnInfo("progress")
    val progress: Int = if (done) 100 else 0
)

fun TaskEntity.toDomain(subTasks: List<SubTaskEntity>): Task {
    return Task(
        uid = uid,
        title = title,
        description = description,
        creationDate = LocalDateTime.parse(creationDate, FormatterConstraint.dateFormat),
        done = done,
        progression = progress.toFloat(),
        synchronized = synced,
        subtasks = subTasks.map { it.toDomain() }
    )
}

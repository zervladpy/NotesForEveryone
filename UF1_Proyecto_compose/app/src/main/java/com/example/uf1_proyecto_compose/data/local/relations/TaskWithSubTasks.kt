package com.example.uf1_proyecto_compose.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.uf1_proyecto_compose.data.local.entity.SubTaskEntity
import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity

data class TaskWithSubTasks(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        entity = SubTaskEntity::class,
    )
    val subtasks: List<SubTaskEntity>
)

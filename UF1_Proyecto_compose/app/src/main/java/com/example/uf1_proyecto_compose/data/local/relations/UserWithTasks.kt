package com.example.uf1_proyecto_compose.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.local.entity.UserEntity

data class UserWithTasks(
    @Embedded
    val task: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id",
    )
    val tasks: List<TaskEntity>
)
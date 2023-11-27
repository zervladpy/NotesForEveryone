package com.example.uf1_proyecto_compose.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.local.entity.UserEntity

data class UserWithTasks(
    @Embedded
    val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id",
        entity = TaskEntity::class
    )
    val tasks: List<TaskEntity>
)
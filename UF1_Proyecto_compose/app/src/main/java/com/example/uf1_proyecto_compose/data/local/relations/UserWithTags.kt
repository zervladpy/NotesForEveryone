package com.example.uf1_proyecto_compose.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.uf1_proyecto_compose.data.local.entity.TagEntity
import com.example.uf1_proyecto_compose.data.local.entity.UserEntity

data class UserWithTags(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val tags: List<TagEntity>
)
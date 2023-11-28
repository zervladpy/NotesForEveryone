package com.example.uf1_proyecto_compose.data.remote.tag

import com.example.uf1_proyecto_compose.data.remote.dto.TagDto

interface TagApi {
    suspend fun getAllTags(userUid: String): List<TagDto>

}
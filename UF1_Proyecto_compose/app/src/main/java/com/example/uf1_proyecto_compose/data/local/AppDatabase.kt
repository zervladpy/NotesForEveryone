package com.example.uf1_proyecto_compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.uf1_proyecto_compose.data.local.dao.AppDao
import com.example.uf1_proyecto_compose.data.local.entity.SubTaskEntity
import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.local.entity.UserEntity

@Database(
    entities = [
        TaskEntity::class,
        UserEntity::class,
        SubTaskEntity::class,
    ], version = 3, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao

}
package com.example.uf1_proyecto_compose.data.source.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.uf1_proyecto_compose.data.source.local.roomdb.dao.TaskDao
import com.example.uf1_proyecto_compose.data.source.local.roomdb.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}
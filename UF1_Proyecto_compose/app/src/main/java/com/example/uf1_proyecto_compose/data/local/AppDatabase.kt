package com.example.uf1_proyecto_compose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.uf1_proyecto_compose.data.local.dao.TaskDao
import com.example.uf1_proyecto_compose.data.local.dao.UserDao
import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.local.entity.UserEntity

@Database(entities = [TaskEntity::class, UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    abstract fun getUserDao(): UserDao

}
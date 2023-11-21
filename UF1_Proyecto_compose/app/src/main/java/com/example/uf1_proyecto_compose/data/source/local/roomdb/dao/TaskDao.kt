package com.example.uf1_proyecto_compose.data.source.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.uf1_proyecto_compose.data.source.local.roomdb.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<TaskEntity>): Unit

    @Query("SELECT * FROM ${TaskEntity.TABLE_NAME}")
    suspend fun getAll(): List<TaskEntity>

    @Delete
    suspend fun deleteAll(tasks: List<TaskEntity>): Unit

}
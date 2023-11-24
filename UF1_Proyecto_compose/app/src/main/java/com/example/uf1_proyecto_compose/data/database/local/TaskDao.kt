package com.example.uf1_proyecto_compose.data.database.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Update(entity = TaskEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun updateTask(task: TaskEntity)

    @Update(entity = TaskEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun updateTasks(tasks: List<TaskEntity>)

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(task: TaskEntity)
}
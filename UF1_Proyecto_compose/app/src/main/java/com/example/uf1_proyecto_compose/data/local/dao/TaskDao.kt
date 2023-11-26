package com.example.uf1_proyecto_compose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.utils.constraint.TableConstraints

@Dao
interface TaskDao {

    @Query("SELECT * FROM ${TableConstraints.TASK_TABLE}")
    suspend fun getAll(): List<TaskEntity>

    @Insert(entity = TaskEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(taskEntities: List<TaskEntity>)

    /**
     * TODO( Add WHERE clause )
     * */
    @Query("DELETE FROM ${TableConstraints.TASK_TABLE}")
    suspend fun deleteAll()

}
package com.example.uf1_proyecto_compose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.utils.constraint.TableConstraints

@Dao
interface TaskDao {

    @Query("SELECT * FROM ${TableConstraints.TASK_TABLE}")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM ${TableConstraints.TASK_TABLE} WHERE uid = :taskUid")
    suspend fun getTaskByUid(taskUid: String): TaskEntity?

    @Insert(entity = TaskEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(taskEntities: List<TaskEntity>)

    @Query("DELETE FROM ${TableConstraints.TASK_TABLE} WHERE synced = 1")
    suspend fun deleteAll()

    @Query("DELETE FROM ${TableConstraints.TASK_TABLE} WHERE uid = :taskUid")
    suspend fun deleteByUid(taskUid: String)

    @Update(entity = TaskEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun update(taskEntity: TaskEntity)

}
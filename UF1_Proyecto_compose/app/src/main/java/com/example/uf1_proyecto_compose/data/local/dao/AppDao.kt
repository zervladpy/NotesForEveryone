package com.example.uf1_proyecto_compose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.uf1_proyecto_compose.data.local.entity.SubTaskEntity
import com.example.uf1_proyecto_compose.data.local.entity.TaskEntity
import com.example.uf1_proyecto_compose.data.local.entity.UserEntity
import com.example.uf1_proyecto_compose.data.local.relations.TaskWithSubTasks
import com.example.uf1_proyecto_compose.data.local.relations.UserWithTasks

/**
 * Dao Interface to access local data
 * */
@Dao
interface AppDao {

    companion object {
        private const val REPLACE = OnConflictStrategy.REPLACE
    }

    /**
     * Inserts a user into local users table
     * @param user UserEntity
     * @see UserEntity
     * */
    @Insert(entity = UserEntity::class, onConflict = REPLACE)
    suspend fun insertUser(user: UserEntity)

    /**
     * Inserts a task into tasks table
     * @param task
     * @see TaskEntity
     * */
    @Insert(entity = TaskEntity::class, onConflict = REPLACE)
    suspend fun insertTask(task: TaskEntity)

    /**
     * Inserts a task into tasks table
     * @param subTask
     * @see SubTaskEntity
     * */
    @Insert(entity = SubTaskEntity::class, onConflict = REPLACE)
    suspend fun insertSubTask(subTask: SubTaskEntity)

    /**
     * Gets a UserEntity from users table
     * @param userUid String
     * @see UserEntity
     * @return UserEntity?
     * */
    @Query("SELECT * FROM users WHERE id = :userUid")
    suspend fun getUserByUid(userUid: String): UserEntity?

    /**
     * Gets a task by Primary Key from table tasks
     * @param taskUid String
     * @see TaskEntity
     * @return TaskWithSubtasks?
     * */
    @Query("SELECT * FROM tasks WHERE id = :taskUid")
    suspend fun getTaskByPk(taskUid: String): TaskWithSubTasks?


    /**
     * Inserts many tasks into tasks table
     * @param tasks
     * @see TaskEntity
     * */
    @Insert(entity = TaskEntity::class, onConflict = REPLACE)
    suspend fun insertManyTasks(tasks: List<TaskEntity>)

    /**
     * Inserts many tasks into tasks table
     * @param tasks
     * @see TaskEntity
     * */
    @Insert(entity = SubTaskEntity::class, onConflict = REPLACE)
    suspend fun insertManySubTasks(tasks: List<SubTaskEntity>)

    /**
     * Deletes a user from users table
     * @param userUid String
     * @see UserEntity
     * */
    @Query("DELETE FROM users WHERE id = :userUid")
    suspend fun deleteUser(userUid: String)

    /**
     * Deletes a task from tasks table
     * @param taskUid String
     * @see TaskEntity
     * */
    @Query("DELETE FROM tasks WHERE id = :taskUid")
    suspend fun deleteTask(taskUid: String)

    /**
     * Gets all Tasks from tasks table related to a user
     * @param userUid String (userUid)
     * @see UserWithTasks
     * @see UserEntity
     * @see TaskEntity
     * @return A list of all tasks related to the user
     * */
    @Transaction
    @Query("SELECT * FROM tasks WHERE id = :userUid")
    suspend fun getUserWithTasks(userUid: String): UserWithTasks

    /**
     * Gets all Tasks from tasks table related to a user
     * @param taskUid String (userUid)
     * @see TaskWithSubTasks
     * @see TaskEntity
     * @see SubTaskEntity
     * @return A list of all tasks related to the user
     * */

    @Transaction
    @Query("SELECT * FROM tasks WHERE id = :taskUid")
    suspend fun getTaskWithSubtasks(taskUid: String): TaskWithSubTasks

    /**
     * Deletes all tasks from tasks table related to a user
     * @param userUid String
     * @see TaskEntity
     * */
    @Query("DELETE FROM tasks WHERE id = :userUid")
    suspend fun deleteTasksWithUser(userUid: String)

}
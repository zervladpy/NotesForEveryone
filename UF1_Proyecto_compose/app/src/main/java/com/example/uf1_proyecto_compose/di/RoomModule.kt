package com.example.uf1_proyecto_compose.di

import android.content.Context
import androidx.room.Room
import com.example.uf1_proyecto_compose.data.database.local.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val TASK_DATABASE = "task_database"

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, TaskDatabase::class.java, TASK_DATABASE).build()

    @Singleton
    @Provides
    fun provideTaskDao(
        db: TaskDatabase
    ) = db.getTaskDao()

}
package com.example.uf1_proyecto_compose.utils.di

import android.content.Context
import androidx.room.Room
import com.example.uf1_proyecto_compose.data.local.AppDatabase
import com.example.uf1_proyecto_compose.data.local.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val TASK_DATABASE = "notes_database"

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, TASK_DATABASE).build()

    @Singleton
    @Provides
    fun provideTaskDao(
        db: AppDatabase
    ): TaskDao = db.getTaskDao()

}
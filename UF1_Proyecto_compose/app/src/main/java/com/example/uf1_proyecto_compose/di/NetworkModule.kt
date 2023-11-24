package com.example.uf1_proyecto_compose.di

import com.example.uf1_proyecto_compose.data.database.repository.task.TaskService
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideFirebaseInstance() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideTaskService(firestore: FirebaseFirestore) = TaskService(firestore)

}
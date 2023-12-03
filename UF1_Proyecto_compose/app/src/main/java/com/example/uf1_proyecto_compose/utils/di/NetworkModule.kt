package com.example.uf1_proyecto_compose.utils.di

import com.example.uf1_proyecto_compose.data.local.dao.AppDao
import com.example.uf1_proyecto_compose.data.remote.task.TaskApi
import com.example.uf1_proyecto_compose.data.remote.task.TaskFirebaseApi
import com.example.uf1_proyecto_compose.data.repository.TaskRepositoryImpl
import com.example.uf1_proyecto_compose.domain.repository.TaskRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
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
    fun provideFirebaseStorageInstance() = FirebaseStorage.getInstance()

    @Singleton
    @Provides
    fun provideTaskApi(
        firestore: FirebaseFirestore,
    ): TaskApi = TaskFirebaseApi(firestore)


    @Singleton
    @Provides
    fun provideTaskRepository(
        taskApi: TaskApi,
        appDao: AppDao,
    ): TaskRepository = TaskRepositoryImpl(taskApi, appDao)


}
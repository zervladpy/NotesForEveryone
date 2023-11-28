package com.example.uf1_proyecto_compose.utils.di

import com.example.uf1_proyecto_compose.data.local.dao.AppDao
import com.example.uf1_proyecto_compose.data.remote.auth.AuthApi
import com.example.uf1_proyecto_compose.data.remote.auth.AuthFirebaseApi
import com.example.uf1_proyecto_compose.data.repository.AuthRepositoryImpl
import com.example.uf1_proyecto_compose.domain.model.User
import com.example.uf1_proyecto_compose.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthApi(
        firebaseAuth: FirebaseAuth
    ): AuthApi = AuthFirebaseApi(firebaseAuth)

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        appDao: AppDao
    ): AuthRepository = AuthRepositoryImpl(authApi, appDao)

    @Provides
    @Singleton
    fun provideCurrentUser(): User {
        return User()
    }
}
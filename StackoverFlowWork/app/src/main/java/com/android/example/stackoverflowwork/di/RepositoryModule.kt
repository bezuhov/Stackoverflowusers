package com.android.example.stackoverflowwork.di

import com.android.example.stackoverflowwork.data.service.RemoteApiService
import com.android.example.stackoverflowwork.domain.mapper.NetworkMapper
import com.android.example.stackoverflowwork.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        remoteApiService: RemoteApiService,
    ):MainRepository{
        return MainRepository(remoteApiService)
    }
}
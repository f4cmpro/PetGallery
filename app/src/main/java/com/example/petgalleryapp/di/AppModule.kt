package com.example.petgalleryapp.di

import com.example.petgalleryapp.data.source.ApiManager
import com.example.petgalleryapp.data.source.remote.animals.GetAnimalsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGetAnimalsApiService(apiManager: ApiManager) : GetAnimalsApiService = apiManager.create(GetAnimalsApiService::class.java)
}
package com.fintech2023.di

import com.fintech2023.api.FilmService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideFilmService(): FilmService {
        return FilmService.create()
    }
}
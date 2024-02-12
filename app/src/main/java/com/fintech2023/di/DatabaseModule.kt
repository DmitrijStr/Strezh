package com.fintech2023.di

import android.content.Context
import com.fintech2023.data.AppDatabase
import com.fintech2023.data.FilmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideFilmDao(appDatabase: AppDatabase): FilmDao {
        return appDatabase.filmDao()
    }
}
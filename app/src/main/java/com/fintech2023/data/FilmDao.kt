package com.fintech2023.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("SELECT * FROM films")
    fun getFilms(): Flow<List<FilmInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmInfo): Long

}
package com.fintech2023.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmDao {

//    @Query("SELECT * FROM films ORDER BY ...")
//    fun getFilms(): Flow<List<Film>>
}
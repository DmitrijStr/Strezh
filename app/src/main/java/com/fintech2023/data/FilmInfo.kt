package com.fintech2023.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmInfo(
    @PrimaryKey @ColumnInfo(name = "id")
    val filmId: String,
    @ColumnInfo(name = "name")
    val name: String?,
    val releaseYear: String,
    val genre: String,
    val posterUrlPreview: String,
)
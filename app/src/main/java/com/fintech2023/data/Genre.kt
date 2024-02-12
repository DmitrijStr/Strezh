package com.fintech2023.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "genre",
    foreignKeys = [
        ForeignKey(entity = Film::class, parentColumns = ["id"], childColumns = ["genre_id"])
    ],
    indices = [Index("genre_id")]
)
data class Genre(
    @ColumnInfo(name = "genre_id") @field:SerializedName("genre") val genre: String,
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var filmGenreId: Long = 0

    override fun toString(): String = genre
}
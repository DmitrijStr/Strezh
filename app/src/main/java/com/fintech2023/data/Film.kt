package com.fintech2023.data

import com.google.gson.annotations.SerializedName

data class Film(
    @field:SerializedName("filmId") val filmId: String,
    @field:SerializedName("nameRu") val name: String?,
    @field:SerializedName("year") val releaseYear: String,
    @field:SerializedName("genres") val genres: List<Genre>,
    @field:SerializedName("posterUrlPreview") val posterUrlPreview: String,
    val inFavorite: Boolean = false
)
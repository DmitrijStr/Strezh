package com.fintech2023.data

import com.google.gson.annotations.SerializedName

data class FilmDetailResponse(
    @field:SerializedName("kinopoiskId") val filmId: String,
    @field:SerializedName("nameRu") val name: String,
    @field:SerializedName("genres") val genres: List<Genre>,
    @field:SerializedName("countries") val countries: List<Country>,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("posterUrl") val posterUrl: String,
)
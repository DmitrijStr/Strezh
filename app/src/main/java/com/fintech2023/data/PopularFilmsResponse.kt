package com.fintech2023.data

import com.google.gson.annotations.SerializedName

data class PopularFilmsResponse(
    @field:SerializedName("films") val results: List<Film>,
    @field:SerializedName("pagesCount") val totalPages: Int
)
package com.fintech2023.data

import com.google.gson.annotations.SerializedName

data class Genre(
    @field:SerializedName("genre") val genre: String,
) {
    override fun toString(): String = genre
}
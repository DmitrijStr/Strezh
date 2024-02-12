package com.fintech2023.data

import com.google.gson.annotations.SerializedName

data class Country(
    @field:SerializedName("country") val country: String,
) {
    override fun toString(): String = country
}
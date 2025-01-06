package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response


import com.squareup.moshi.Json

data class Dates(
    @Json(name = "maximum")
    val maximum: String,
    @Json(name = "minimum")
    val minimum: String
)
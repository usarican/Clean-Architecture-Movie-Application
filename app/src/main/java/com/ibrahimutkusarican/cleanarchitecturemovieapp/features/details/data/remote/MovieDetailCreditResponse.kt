package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote


import com.squareup.moshi.Json

data class MovieDetailCreditResponse(
    @Json(name = "cast")
    val castResponse: List<CastResponse>,
    @Json(name = "id")
    val id: Int
)
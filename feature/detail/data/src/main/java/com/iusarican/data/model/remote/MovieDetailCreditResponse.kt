package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote


import com.squareup.moshi.Json

data class MovieDetailCreditResponse(
    @Json(name = "cast")
    val castResponse: List<MovieDetailCastResponse>,
    @Json(name = "id")
    val id: Int
)
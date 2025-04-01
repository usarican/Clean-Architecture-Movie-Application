package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.model.remote


import com.squareup.moshi.Json

data class MovieDetailCastResponse(
    @Json(name = "cast_id")
    val castId: Int,
    @Json(name = "character")
    val character: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "profile_path")
    val profilePath: String?
)
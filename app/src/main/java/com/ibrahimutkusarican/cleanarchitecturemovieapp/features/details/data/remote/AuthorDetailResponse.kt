package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote


import com.squareup.moshi.Json

data class AuthorDetailResponse(
    @Json(name = "avatar_path")
    val avatarPath: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "username")
    val username: String
)
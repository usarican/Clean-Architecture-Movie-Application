package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.model.remote


import com.squareup.moshi.Json

data class MovieDetailAuthorResponse(
    @Json(name = "author")
    val author: String?,
    @Json(name = "author_details")
    val authorDetailResponse: MovieDetailAuthorResponseItem,
    @Json(name = "content")
    val content: String?,
    @Json(name = "updated_at")
    val updatedAt: String,
)
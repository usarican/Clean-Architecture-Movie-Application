package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote


import com.squareup.moshi.Json

data class AuthorResponse(
    @Json(name = "author")
    val author: String?,
    @Json(name = "author_details")
    val authorDetailResponse: AuthorDetailResponse,
    @Json(name = "content")
    val content: String?,
    @Json(name = "updated_at")
    val updatedAt: String,
)
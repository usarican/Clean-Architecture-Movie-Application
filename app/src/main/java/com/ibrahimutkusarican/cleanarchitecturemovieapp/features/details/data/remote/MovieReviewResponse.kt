package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote


import com.squareup.moshi.Json

data class MovieReviewResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val authorRespons: List<AuthorResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
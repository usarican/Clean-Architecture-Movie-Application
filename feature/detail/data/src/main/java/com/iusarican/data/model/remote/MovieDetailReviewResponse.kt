package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote


import com.squareup.moshi.Json

data class MovieDetailReviewResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val authorResponses: List<MovieDetailAuthorResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
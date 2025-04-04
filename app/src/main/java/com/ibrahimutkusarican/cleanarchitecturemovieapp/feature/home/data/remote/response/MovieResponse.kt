package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.response


import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "dates")
    val dates: Dates?,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val movieResultResponse: List<MovieResultResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
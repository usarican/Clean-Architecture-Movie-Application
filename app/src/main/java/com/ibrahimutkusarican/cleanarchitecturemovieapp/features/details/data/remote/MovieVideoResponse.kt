package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote


import com.squareup.moshi.Json

data class MovieVideoResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "results")
    val videoResponses: List<VideoResponse>
)
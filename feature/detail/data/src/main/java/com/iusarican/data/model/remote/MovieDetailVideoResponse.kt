package com.iusarican.data.model.remote


import com.squareup.moshi.Json

data class MovieDetailVideoResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "results")
    val videoResponses: List<VideoResponse>
)
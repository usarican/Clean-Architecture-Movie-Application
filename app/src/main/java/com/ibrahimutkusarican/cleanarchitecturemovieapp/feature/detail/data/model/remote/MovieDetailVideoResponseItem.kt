package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote


import com.squareup.moshi.Json

data class VideoResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "official")
    val official: Boolean,
    @Json(name = "published_at")
    val publishedAt: String,
    @Json(name = "site")
    val site: String,
    @Json(name = "size")
    val size: Int,
    @Json(name = "type")
    val type: String
)

enum class VideoSite(val value: String) {
    YOUTUBE(value = "YouTube")
}

enum class VideoType(val value: String) {
   TRAILER(value = "Trailer")
}
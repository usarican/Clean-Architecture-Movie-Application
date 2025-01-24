package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote


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
    YOUTUBE(value = "Youtube");

    companion object {
        fun fromValue(value: String): VideoSite? = when (value) {
            "Youtube" -> YOUTUBE
            else -> null
        }
    }
}

enum class VideoType(val value: String) {
    CLIP(value = "Clip"), TRAILER(value = "Trailer");

    companion object {
        fun fromValue(value: String): VideoType = when (value) {
            "Clip" -> CLIP
            "Trailer" -> TRAILER
            else -> throw IllegalArgumentException()
        }
    }
}
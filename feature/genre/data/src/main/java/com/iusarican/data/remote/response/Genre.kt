package com.iusarican.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id") val genreId : Int,
    @Json(name = "name") val genreName : String
)

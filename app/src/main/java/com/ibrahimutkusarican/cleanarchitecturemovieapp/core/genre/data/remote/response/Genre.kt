package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote.response

import com.squareup.moshi.Json

data class Genre(
    @Json(name = "id") val genreId : Int,
    @Json(name = "name") val genreName : String
)

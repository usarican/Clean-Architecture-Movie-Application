package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.data.remote.response

import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "genres") val genreList : List<Genre>
)

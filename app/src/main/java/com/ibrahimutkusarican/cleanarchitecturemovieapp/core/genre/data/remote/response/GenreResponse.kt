package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote.response

import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "genres") val genreList : List<Genre>
)

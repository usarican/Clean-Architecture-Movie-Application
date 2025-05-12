package com.iusarican.data.remote.response

import com.iusarican.data.remote.response.Genre
import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "genres") val genreList : List<Genre>
)

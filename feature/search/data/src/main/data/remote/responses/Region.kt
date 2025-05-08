package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.remote.responses


import com.squareup.moshi.Json

data class Region(
    @Json(name = "iso_3166_1")
    val regionCode: String,
    @Json(name = "native_name")
    val regionName: String
)
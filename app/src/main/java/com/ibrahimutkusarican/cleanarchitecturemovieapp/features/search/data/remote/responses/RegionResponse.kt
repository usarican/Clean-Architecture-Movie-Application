package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote.responses


import com.squareup.moshi.Json

data class RegionResponse(
    @Json(name = "results")
    val regions: List<Region>
)
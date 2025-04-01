package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model

data class MovieDetailTrailerModel(
    val trailers : List<TrailerModel>
)

data class TrailerModel(
    val name : String,
    val key : String,
)
package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model


data class MovieDetailAboutModel(
    val budget: String?,
    val revenue: String?,
    val overview : String,
    val status: String?,
    val genres : List<String>,
    val fullReleaseDate : String,
    val rating: String,
    val casts : List<MovieDetailCastModel>
)
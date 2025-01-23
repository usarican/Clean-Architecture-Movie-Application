package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model


data class MovieDetailAboutModel(
    val budget: String?,
    val revenue: String?,
    val status: String?,
    val genres : List<String>,
    val fullReleaseDate : String,
    val voteCount: Int,
    val voteAverage: Double,
    val casts : List<CastModel>
)

data class CastModel(
    val characterName: String,
    val gender: Int,
    val order: Int,
    val originalName: String?,
    val profileImage: String?
)
package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model

data class MovieDetailCastModel(
    val characterName: String,
    val gender: Int,
    val order: Int,
    val originalName: String?,
    val profileImage: String?
)
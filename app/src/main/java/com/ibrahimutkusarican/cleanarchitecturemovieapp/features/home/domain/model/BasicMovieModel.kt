package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model


data class BasicMovieModel(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val moviePosterImageUrl : String?,
    val movieBackdropImageUrl : String?,
    val movieOverview : String,
    val releaseDate : String,
    val movieVotePoint : String,
)

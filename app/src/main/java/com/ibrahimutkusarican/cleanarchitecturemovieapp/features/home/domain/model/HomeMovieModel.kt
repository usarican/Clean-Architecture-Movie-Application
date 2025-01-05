package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model

data class HomeMovieModel(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val moviePosterImageUrl : String?,
    val movieBackdropImageUrl : String?,
    val movieTMDBScore : Double
)

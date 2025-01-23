package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model

data class MovieDetailRecommendedMovieModel(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val moviePosterImageUrl : String?,
    val movieTMDBScore : Double,
)
package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model

data class MovieDetailRecommendedMovieModel(
    val recommendedMovies : List<RecommendedMovieModel>
)

data class RecommendedMovieModel(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val moviePosterImageUrl : String?,
    val movieTMDBScore : Double,
)
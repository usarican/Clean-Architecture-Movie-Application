package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model

data class SearchMovieModel(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val movieContent : String,
    val moviePosterImageUrl : String?,
    val movieTMDBScore : Double,
    val movieReleaseTime : String,
)
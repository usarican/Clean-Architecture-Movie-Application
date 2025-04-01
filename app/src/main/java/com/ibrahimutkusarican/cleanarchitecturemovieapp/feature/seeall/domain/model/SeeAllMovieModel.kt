package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.model

data class SeeAllMovieModel(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val movieContent : String,
    val moviePosterImageUrl : String?,
    val movieTMDBScore : Double,
    val movieReleaseTime : String,
)

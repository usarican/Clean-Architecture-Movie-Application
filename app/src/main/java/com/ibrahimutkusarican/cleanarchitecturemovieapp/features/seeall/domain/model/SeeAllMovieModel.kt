package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model

data class SeeAllMovieModel(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val movieContent : String,
    val moviePosterImageUrl : String?,
    val movieTMDBScore : Double,
    val movieTime : String,
)

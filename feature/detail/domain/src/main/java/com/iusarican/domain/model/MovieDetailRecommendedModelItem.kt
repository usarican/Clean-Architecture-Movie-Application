package com.iusarican.domain.model

data class MovieDetailRecommendedModelItem(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val moviePosterImageUrl : String?,
    val movieTMDBScore : Double,
)
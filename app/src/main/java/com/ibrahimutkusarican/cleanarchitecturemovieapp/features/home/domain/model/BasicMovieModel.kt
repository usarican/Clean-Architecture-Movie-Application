package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class BasicMovieModel(
    val movieId : Int,
    val movieTitle : String,
    val movieGenres : List<String>,
    val moviePosterImageUrl : String?,
    val movieBackdropImageUrl : String?,
    val movieOverview : String,
    val releaseDate : String,
    val movieVotePoint : String,
    val isFavorite : Boolean = false,
    val isAddedToWatchList : Boolean = false,
    val sharedAnimationKey : String = UUID.randomUUID().toString()
)

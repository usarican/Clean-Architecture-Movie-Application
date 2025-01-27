package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model

data class MyListMovieModel(
    val movieId: Int,
    val genres: List<String>,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val movieRating: String,
    var isFavorite: Boolean = false,
    var isAddedWatchList: Boolean = false,
)
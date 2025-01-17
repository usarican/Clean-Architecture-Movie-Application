package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.model

data class ExploreGenreModel(
    val genreId : Int,
    val genreName : String,
    val genreIsSelected : Boolean = false
)

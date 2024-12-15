package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieModel

data class GenreModel(
    val movieGenreId : Int,
    val movieGenreName : String
) : MovieModel

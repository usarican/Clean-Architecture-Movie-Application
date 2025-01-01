package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import javax.inject.Inject

class GenreIdsToGenreNameListMapper @Inject constructor() {
    fun getGenreNames(genreIds: List<Int>, genres: List<GenreModel>): List<String> {
        return genreIds.mapNotNull { id ->
            genres.find { it.movieGenreId == id }?.movieGenreName
        }
    }
}
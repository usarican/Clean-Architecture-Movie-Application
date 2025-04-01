package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.data.remote

import javax.inject.Inject

class GenreRemoteDataSource @Inject constructor(
    private val genreService: GenreService
) {
    suspend fun getMovieGenreList() = genreService.getMovieGenres()
}
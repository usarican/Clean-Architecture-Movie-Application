package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.data.remote

import javax.inject.Inject

class GenreRemoteDataSource @Inject constructor(
    private val genreService: GenreService
) {
    suspend fun getMovieGenreList() = genreService.getMovieGenres()
}
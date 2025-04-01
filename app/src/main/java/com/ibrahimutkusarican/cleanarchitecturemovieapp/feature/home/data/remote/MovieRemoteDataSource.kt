package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote

import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun getNowPlayingMovies(page: Int) = movieService.getNowPlayingMovies(page)
    suspend fun getPopularMovies(page: Int) = movieService.getPopularMovies(page)
    suspend fun getTopRatedMovies(page: Int) = movieService.getTopRatedMovies(page)
    suspend fun getUpComingMovies(page: Int) = movieService.getUpComingMovies(page)
}
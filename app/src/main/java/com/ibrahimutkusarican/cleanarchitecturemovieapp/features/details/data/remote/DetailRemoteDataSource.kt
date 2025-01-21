package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote

import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(
    private val movieDetailService: MovieDetailService
) {
    suspend fun getMovieDetail(movieId: Int) = movieDetailService.getMovieDetailByMovieId(movieId)
}
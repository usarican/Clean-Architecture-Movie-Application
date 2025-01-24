package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote

import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(
    private val movieDetailService: MovieDetailService
) {
    suspend fun getMovieDetail(movieId: Int) = movieDetailService.getMovieDetailByMovieId(movieId)
    suspend fun getMovieCredits(movieId: Int) = movieDetailService.getMovieCreditsByMovieId(movieId)
    suspend fun getMovieRecommendations(movieId: Int) =
        movieDetailService.getMovieRecommendationsByMovieId(movieId)

    suspend fun getMovieReviews(movieId: Int) = movieDetailService.getMovieReviewsByMovieId(movieId)
    suspend fun getMovieTrailers(movieId: Int) = movieDetailService.getMovieVideosByMovieId(movieId)

}
package com.iusarican.data.datasource

import com.iusarican.data.network.MovieDetailService
import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(
    private val movieDetailService: MovieDetailService
) {
    suspend fun getMovieDetail(movieId: Int) = movieDetailService.getMovieDetailByMovieId(movieId)
    suspend fun getMovieCredits(movieId: Int) = movieDetailService.getMovieCreditsByMovieId(movieId)
    suspend fun getMovieRecommendations(movieId: Int, page: Int) =
        movieDetailService.getMovieRecommendationsByMovieId(movieId, page)

    suspend fun getMovieReviews(movieId: Int, page: Int) =
        movieDetailService.getMovieReviewsByMovieId(movieId, page)

    suspend fun getMovieTrailers(movieId: Int) = movieDetailService.getMovieVideosByMovieId(movieId)
}
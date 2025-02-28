package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.STARTING_PAGE_INDEX
import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(
    private val movieDetailService: MovieDetailService
) {
    suspend fun getMovieDetail(movieId: Int) = movieDetailService.getMovieDetailByMovieId(movieId)
    suspend fun getMovieCredits(movieId: Int) = movieDetailService.getMovieCreditsByMovieId(movieId)
    suspend fun getMovieRecommendations(movieId: Int,page : Int = STARTING_PAGE_INDEX) =
        movieDetailService.getMovieRecommendationsByMovieId(movieId,page)

    suspend fun getMovieReviews(movieId: Int,page: Int = STARTING_PAGE_INDEX) = movieDetailService.getMovieReviewsByMovieId(movieId,page)
    suspend fun getMovieTrailers(movieId: Int) = movieDetailService.getMovieVideosByMovieId(movieId)

}
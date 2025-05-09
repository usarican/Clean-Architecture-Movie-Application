package com.iusarican.data.repository.datasourceImpl

import com.iusarican.data.network.MovieDetailService
import com.iusarican.data.repository.datasource.DetailRemoteDataSource
import javax.inject.Inject

class DetailRemoteDataSourceImpl @Inject constructor(
    private val movieDetailService: MovieDetailService
): DetailRemoteDataSource {
    override suspend fun getMovieDetail(movieId: Int) = movieDetailService.getMovieDetailByMovieId(movieId)
    override suspend fun getMovieCredits(movieId: Int) = movieDetailService.getMovieCreditsByMovieId(movieId)
    override suspend fun getMovieRecommendations(movieId: Int,page : Int) = movieDetailService.getMovieRecommendationsByMovieId(movieId,page)
    override suspend fun getMovieReviews(movieId: Int,page: Int) = movieDetailService.getMovieReviewsByMovieId(movieId,page)
    override suspend fun getMovieTrailers(movieId: Int) = movieDetailService.getMovieVideosByMovieId(movieId)
}
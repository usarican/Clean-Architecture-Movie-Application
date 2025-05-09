package com.iusarican.data.repository.datasource

import com.iusarican.data.model.remote.MovieDetailCreditResponse
import com.iusarican.data.model.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.model.remote.MovieDetailReviewResponse
import com.iusarican.data.model.remote.MovieDetailVideoResponse
import com.iusarican.common.utils.Constants.STARTING_PAGE_INDEX

interface DetailRemoteDataSource {
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse
    suspend fun getMovieCredits(movieId: Int): MovieDetailCreditResponse
    suspend fun getMovieRecommendations(movieId: Int,page : Int = STARTING_PAGE_INDEX): MovieResponse
    suspend fun getMovieReviews(movieId: Int,page: Int = STARTING_PAGE_INDEX): MovieDetailReviewResponse
    suspend fun getMovieTrailers(movieId: Int): MovieDetailVideoResponse
}
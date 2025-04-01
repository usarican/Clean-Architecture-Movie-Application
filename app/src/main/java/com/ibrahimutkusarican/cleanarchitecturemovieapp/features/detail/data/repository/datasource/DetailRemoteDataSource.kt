package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.repository.datasource

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieDetailCreditResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieDetailResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieReviewResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.remote.MovieVideoResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.STARTING_PAGE_INDEX

interface DetailRemoteDataSource {
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse
    suspend fun getMovieCredits(movieId: Int): MovieDetailCreditResponse
    suspend fun getMovieRecommendations(movieId: Int,page : Int = STARTING_PAGE_INDEX): MovieResponse
    suspend fun getMovieReviews(movieId: Int,page: Int = STARTING_PAGE_INDEX): MovieReviewResponse
    suspend fun getMovieTrailers(movieId: Int): MovieVideoResponse
}
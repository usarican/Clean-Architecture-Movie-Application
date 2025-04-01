package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.model.remote.MovieDetailAuthorResponse
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import kotlinx.coroutines.flow.Flow

interface SeeAllRepository {
    fun getSeeAllMoviesByType(movieType: MovieType) : Flow<PagingData<MovieResultResponse>>
    fun getRecommendedSeeAllMovies(movieId : Int) : Flow<PagingData<MovieResultResponse>>
    fun getMovieReviewsSeeAll(movieId: Int) : Flow<PagingData<MovieDetailAuthorResponse>>
}
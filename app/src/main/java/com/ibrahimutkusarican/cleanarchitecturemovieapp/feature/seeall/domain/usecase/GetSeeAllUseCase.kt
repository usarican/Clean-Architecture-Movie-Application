package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.usecase

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailReviewModelItem
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.model.SeeAllMovieModel
import kotlinx.coroutines.flow.Flow

interface GetSeeAllUseCase {
    fun getSeeAllMoviesByType(movieType: MovieType) : Flow<PagingData<SeeAllMovieModel>>
    fun getSeeAllRecommendedMovies(movieId : Int) : Flow<PagingData<SeeAllMovieModel>>
    fun getMovieReviewsSeeAll(movieId: Int) : Flow<PagingData<MovieDetailReviewModelItem>>
}
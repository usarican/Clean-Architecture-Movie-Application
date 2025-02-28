package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import kotlinx.coroutines.flow.Flow

interface GetSeeAllMoviesUseCase {
    fun getSeeAllMoviesByType(movieType: MovieType) : Flow<PagingData<SeeAllMovieModel>>
    fun getSeeAllRecommendedMovies(movieId : Int) : Flow<PagingData<SeeAllMovieModel>>
}
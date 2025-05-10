package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailUseCase {
    fun getMovieDetail(movieId : Int) : Flow<UiState<MovieDetailModel>>
}
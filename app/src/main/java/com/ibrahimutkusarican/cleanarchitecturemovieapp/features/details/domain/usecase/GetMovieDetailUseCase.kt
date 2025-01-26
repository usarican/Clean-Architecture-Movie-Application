package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailUseCase {
    fun getMovieDetail(movieId : Int) : Flow<UiState<MovieDetailModel>>
}
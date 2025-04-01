package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieShareModel
import kotlinx.coroutines.flow.Flow

interface GetMovieShareModelUseCase {
    fun getMovieUri(movieDetail : MovieDetailInfoModel?) : Flow<UiState<MovieShareModel>>
}
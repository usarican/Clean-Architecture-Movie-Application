package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.usecase

import android.net.Uri
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieShareModel
import kotlinx.coroutines.flow.Flow

interface GetMovieShareModelUseCase {
    fun getMovieUri(movieDetail : MovieDetailInfoModel?) : Flow<UiState<MovieShareModel>>
}
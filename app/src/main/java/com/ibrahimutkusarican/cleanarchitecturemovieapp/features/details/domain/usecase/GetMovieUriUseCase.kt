package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.usecase

import android.net.Uri
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import kotlinx.coroutines.flow.Flow

interface GetMovieUriUseCase {
    fun getMovieUri(moviePosterUrl : String?) : Flow<UiState<Uri>>
}
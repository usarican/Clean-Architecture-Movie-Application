package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.usecase

import android.net.Uri
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageShareHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUriUseCaseImpl @Inject constructor(
    private val imageShareHelper: ImageShareHelper
) : BaseUseCase(), GetMovieUriUseCase {
    override fun getMovieUri(moviePosterUrl: String?): Flow<UiState<Uri>> {
        return execute {
            if (moviePosterUrl == null) throw NullPointerException()
            imageShareHelper.getShareableImageUri(moviePosterUrl) ?: throw NullPointerException()
        }
    }
}
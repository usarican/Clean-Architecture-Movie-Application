package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieShareModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.ImageShareHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieShareModelUseCaseImpl @Inject constructor(
    private val imageShareHelper: ImageShareHelper
) : BaseUseCase(), GetMovieShareModelUseCase {
    override fun getMovieUri(movieDetail: MovieDetailInfoModel?): Flow<UiState<MovieShareModel>> {
        return execute {
            if (movieDetail == null) throw NullPointerException()
            val uri = imageShareHelper.getShareableImageUri(
                imageUrl = movieDetail.posterImageUrl,
                movieTitle = movieDetail.title
            )
            MovieShareModel(
                movieImageUri = uri ?: throw NullPointerException(),
                movieTitle = movieDetail.title
            )
        }
    }
}
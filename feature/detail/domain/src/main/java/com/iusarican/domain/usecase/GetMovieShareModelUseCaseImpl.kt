package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.usecase

import com.iusarican.common.base.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.domain.model.MovieShareModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.ImageShareHelper
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
                movieTitle = movieDetail.title,
                movieId = movieDetail.movieId
            )
        }
    }
}
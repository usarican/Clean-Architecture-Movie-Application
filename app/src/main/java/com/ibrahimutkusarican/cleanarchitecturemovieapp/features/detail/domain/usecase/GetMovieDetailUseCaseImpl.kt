package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.repository.MovieDetailRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.mapper.MovieDetailModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailRecommendedModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data.UserSettingsRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.Language
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    private val movieDetailModelMapper: MovieDetailModelMapper,
    private val myListRepository: MyListRepository,
    private val genresUseCase: GetMovieGenresUseCase,
    private val userSettingsRepository: UserSettingsRepository
) : BaseUseCase(), GetMovieDetailUseCase {
    override fun getMovieDetail(movieId: Int): Flow<UiState<MovieDetailModel>> {
        return execute {
            val movieDetailRecommendedMovieFlow = combine(
                genresUseCase.getMovieGenresUseCase(),
                movieDetailRepository.getMovieDetailRecommendationMovies(movieId)
            ) { genreState, movieState ->
                val genreList = genreState.getSuccessOrThrow()
                val recommendedMovieResponse = movieState.getSuccessOrThrow()
                recommendedMovieResponse.movieResultResponse.map { movieResultResponse ->
                    movieDetailModelMapper.movieResponseToMovieDetailRecommendedMovieModel(
                        movieResultResponse,
                        genreList
                    )
                }
            }

            val movieDetailModelWithoutRecommendedMoviesFlow = combine(
                movieDetailRepository.getMovieDetailResponse(movieId),
                movieDetailRepository.getMovieDetailCredits(movieId),
                movieDetailRepository.getMovieDetailReviews(movieId),
                movieDetailRepository.getMovieDetailTrailers(movieId),
                userSettingsRepository.getLanguageCode()
            ) { movieDetailResponseState, movieDetailCreditResponseState, movieDetailReviewsState, movieDetailTrailersState, languageCode ->
                val movieDetailResponse = movieDetailResponseState.getSuccessOrThrow()
                val movieDetailCreditResponse = movieDetailCreditResponseState.getSuccessOrThrow()
                val movieDetailReviewsResponse = movieDetailReviewsState.getSuccessOrThrow()
                val movieDetailTrailersResponse = movieDetailTrailersState.getSuccessOrThrow()

                val movieDetailInfoModel =
                    movieDetailModelMapper.movieDetailResponseToMovieDetailInfoModel(
                        movieDetailResponse = movieDetailResponse,
                        language = Language.fromLanguageCode(languageCode)
                    )
                val movieDetailAboutModel =
                    movieDetailModelMapper.movieDetailResponseToMovieDetailAboutModel(
                        movieDetailResponse =movieDetailResponse,
                        movieDetailCreditResponse = movieDetailCreditResponse,
                        language = Language.fromLanguageCode(languageCode)
                    )
                val movieDetailReviewModel =
                    movieDetailModelMapper.movieReviewResponseToMovieDetailReviewModel(
                        movieDetailReviewsResponse
                    )
                val movieDetailTrailerModel =
                    movieDetailModelMapper.movieTrailerResponseToMovieDetailTrailerModel(
                        movieDetailTrailersResponse
                    )

                MovieDetailModel(
                    movieDetailInfoModel = movieDetailInfoModel,
                    movieDetailAboutModel = movieDetailAboutModel,
                    movieDetailRecommendedMovies = MovieDetailRecommendedModel(
                        recommendedMovies = emptyList()
                    ),
                    movieDetailReviewModel = movieDetailReviewModel,
                    movieDetailTrailerModel = movieDetailTrailerModel
                )
            }

            combine(
                movieDetailModelWithoutRecommendedMoviesFlow,
                movieDetailRecommendedMovieFlow,
                myListRepository.getMyListMovieFavoriteAndWatchListStatus(movieId)
            ) { movieDetailModel, recommendedMovies, myListStatus ->
                val favoriteAndWatchListAddStatus = myListStatus.getSuccessOrThrow()
                movieDetailModel.copy(
                    movieDetailInfoModel = movieDetailModel.movieDetailInfoModel.copy(
                        isFavorite = favoriteAndWatchListAddStatus?.isAddedFavorite ?: false,
                        isAddedToWatchList = favoriteAndWatchListAddStatus?.isAddedWatchList ?: false
                    ),
                    movieDetailRecommendedMovies = MovieDetailRecommendedModel(
                        recommendedMovies = recommendedMovies
                    )
                )
            }.first()
        }
    }
}
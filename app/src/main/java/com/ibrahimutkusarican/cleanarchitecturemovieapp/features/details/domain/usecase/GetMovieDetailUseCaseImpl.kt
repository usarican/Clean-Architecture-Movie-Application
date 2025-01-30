package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.MovieDetailRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.mapper.MovieDetailModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailRecommendedMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.MyListRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    private val movieDetailModelMapper: MovieDetailModelMapper,
    private val myListRepository: MyListRepository,
    private val genresUseCase: GetMovieGenresUseCase
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
            ) { movieDetailResponseState, movieDetailCreditResponseState, movieDetailReviewsState, movieDetailTrailersState ->
                val movieDetailResponse = movieDetailResponseState.getSuccessOrThrow()
                val movieDetailCreditResponse = movieDetailCreditResponseState.getSuccessOrThrow()
                val movieDetailReviewsResponse = movieDetailReviewsState.getSuccessOrThrow()
                val movieDetailTrailersResponse = movieDetailTrailersState.getSuccessOrThrow()

                val movieDetailInfoModel =
                    movieDetailModelMapper.movieDetailResponseToMovieDetailInfoModel(
                        movieDetailResponse
                    )
                val movieDetailAboutModel =
                    movieDetailModelMapper.movieDetailResponseToMovieDetailAboutModel(
                        movieDetailResponse,
                        movieDetailCreditResponse
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
                    movieDetailRecommendedMovies = MovieDetailRecommendedMovieModel(
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
                    movieDetailRecommendedMovies = MovieDetailRecommendedMovieModel(
                        recommendedMovies = recommendedMovies
                    )
                )
            }.first()
        }
    }
}
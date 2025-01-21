package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieException
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.mapper.HomeMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchScreenModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.mapper.SeeAllMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetSearchScreenModelUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
    private val homeMovieModelMapper: HomeMovieModelMapper,
    private val seeAllMovieModelMapper: SeeAllMovieModelMapper,
) : GetSearchScreenModelUseCase, BaseUseCase() {
    override fun getScreenModelUseCase(movieId: Int?): Flow<UiState<SearchScreenModel>> {
        return execute {
            /// TODO: LastSearch Keys Implementation Need Now just Custom
            movieId?.let {
                combine(
                    getMovieGenreUseCase.getMovieGenresUseCase(),
                    searchRepository.getRecommendedMovieById(movieId),
                    searchRepository.getRecentlyViewedMovies()
                ) { genreState, recommendedMovieState, recentlyViewedMovieState ->
                    val genreList = genreState.getSuccessOrThrow()
                    val recommendedMovieResponse = recommendedMovieState.getSuccessOrThrow()
                    val recentlyViewedMovie = recentlyViewedMovieState.getSuccessOrThrow()

                    val recommendedMovieList = recommendedMovieResponse.map { response ->
                        homeMovieModelMapper.mapResponseToModel(response, genreList)
                    }

                    val recentlyViewedMovieList = recentlyViewedMovie.map { entity ->
                        seeAllMovieModelMapper.entityToModel(entity, genreList)
                    }
                    SearchScreenModel(
                        recommendedMoviesForYou = recommendedMovieList,
                        recentlyViewedMovies = recentlyViewedMovieList
                    )
                }.first()
            } ?: throw MovieException.GeneralException(message = null)

        }
    }
}
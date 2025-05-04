package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BaseUseCase
import com.iusarican.common.exception.MovieException
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.mapper.HomeMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.mapper.SearchItemModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.SearchScreenModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.englishTopSearchedMovies
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model.turkishTopSearchedMovies
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.mapper.SeeAllMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.data.UserSettingsRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import com.iusarican.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetSearchScreenModelUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
    private val homeMovieModelMapper: HomeMovieModelMapper,
    private val seeAllMovieModelMapper: SeeAllMovieModelMapper,
    private val searchItemModelMapper: SearchItemModelMapper,
    private val userSettingsRepository: UserSettingsRepository
) : GetSearchScreenModelUseCase, BaseUseCase() {
    override fun getScreenModelUseCase(movieId: Int?): Flow<UiState<SearchScreenModel>> {
        return execute {
            movieId?.let {
                combine(
                    getMovieGenreUseCase.getMovieGenresUseCase(),
                    searchRepository.getRecommendedMovieById(movieId),
                    searchRepository.getRecentlyViewedMovies(),
                    searchRepository.getLastSearchQueries(),
                    userSettingsRepository.getLanguageCode()
                ) { genreState, recommendedMovieState, recentlyViewedMovieState, lastSearchQueryState, languageCode ->
                    val genreList = genreState.getSuccessOrThrow()
                    val recommendedMovieResponse = recommendedMovieState.getSuccessOrThrow()
                    val recentlyViewedMovie = recentlyViewedMovieState.getSuccessOrThrow()
                    val lastSearchEntities = lastSearchQueryState.getSuccessOrThrow()
                    val language = Language.fromLanguageCode(languageCode)

                    val recommendedMovieList = recommendedMovieResponse.map { response ->
                        homeMovieModelMapper.mapResponseToModel(
                            movieResultResponse = response,
                            genreList = genreList,
                            language = language
                        )
                    }

                    val recentlyViewedMovieList = recentlyViewedMovie.map { entity ->
                        seeAllMovieModelMapper.entityToModel(entity)
                    }
                    val lastSearchItems = lastSearchEntities.map { lastSearchEntity ->
                        searchItemModelMapper.entityToModel(lastSearchEntity)
                    }
                    val topSearchedMovies =
                        when (Language.fromLanguageCode(languageCode = languageCode)) {
                            Language.TR -> turkishTopSearchedMovies
                            Language.EN -> englishTopSearchedMovies
                        }
                    SearchScreenModel(
                        recommendedMoviesForYou = recommendedMovieList,
                        recentlyViewedMovies = recentlyViewedMovieList,
                        lastSearchQueries = lastSearchItems,
                        topSearchedMovies = topSearchedMovies
                    )
                }.first()
            } ?: throw MovieException.GeneralException(message = null)

        }
    }
}
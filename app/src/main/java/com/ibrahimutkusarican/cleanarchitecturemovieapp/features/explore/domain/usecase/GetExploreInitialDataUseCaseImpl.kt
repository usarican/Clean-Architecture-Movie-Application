package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.model.ExploreInitialDataModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.mapper.HomeMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data.UserSettingsDataStore
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.Language
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.MoviePosterSize
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetExploreInitialDataUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieModelMapper: HomeMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
    private val userSettingsDataStore: UserSettingsDataStore
) : GetExploreInitialDataUseCase, BaseUseCase() {
    override fun getExploreInitialData(): Flow<UiState<ExploreInitialDataModel>> {
        return execute {
            combine(
                getMovieGenreUseCase.getMovieGenresUseCase(),
                movieRepository.getMoviesByType(MovieType.UPCOMING, limit = 10),
                movieRepository.getMoviesByType(MovieType.POPULAR),
                userSettingsDataStore.getLanguageCode(),
            ) { stateGenres, stateUpcoming, statePopular, languageCode ->
                val genreModelList = stateGenres.getSuccessOrThrow()
                val upcomingList = stateUpcoming.getSuccessOrThrow()
                val popularList = statePopular.getSuccessOrThrow()
                val language = Language.fromLanguageCode(languageCode)

                val bannerMovieList = upcomingList.map { movieEntity ->
                    movieModelMapper.mapEntityToModel(
                        entity = movieEntity,
                        genreList = genreModelList,
                        posterSize = MoviePosterSize.W780,
                        language = language
                    )
                }
                val popularMovieModelList = popularList.map { movieEntity ->
                    movieModelMapper.mapEntityToModel(
                        entity = movieEntity,
                        genreList = genreModelList,
                        language = language,
                        posterSize = MoviePosterSize.W500
                    )
                }

                ExploreInitialDataModel(
                    bannerMovies = bannerMovieList,
                    popularMovies = popularMovieModelList,
                    forYouMovie = popularMovieModelList[(0..10).random()]
                )
            }.first()
        }
    }
}
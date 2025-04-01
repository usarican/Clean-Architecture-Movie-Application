package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.mapper.HomeMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.data.UserSettingsDataStore
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.Language
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.MoviePosterSize
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RefreshHomeMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieModelMapper: HomeMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
    private val userSettingsDataStore: UserSettingsDataStore
) : RefreshHomeMoviesUseCase, BaseUseCase() {
    override fun refreshHomeMovies(): Flow<UiState<Map<MovieType, List<BasicMovieModel>>>> {
        return execute {
            val language = Language.fromLanguageCode(userSettingsDataStore.getLanguageCode().first())
            combine(
                getMovieGenreUseCase.getMovieGenresUseCase(),
                movieRepository.refreshMoviesByType(MovieType.NOW_PLAYING, limit = 10),
                movieRepository.refreshMoviesByType(MovieType.POPULAR),
                movieRepository.refreshMoviesByType(MovieType.TOP_RATED),
                movieRepository.refreshMoviesByType(MovieType.UPCOMING)
            ) { stateGenres, stateNowPlaying, statePopular, stateTopRated, stateUpcoming ->

                val genreModelList = stateGenres.getSuccessOrThrow()
                val nowPlayingList = stateNowPlaying.getSuccessOrThrow()
                val popularList = statePopular.getSuccessOrThrow()
                val topRatedList = stateTopRated.getSuccessOrThrow()
                val upcomingList = stateUpcoming.getSuccessOrThrow()

                val nowPlayingMovieModelList = nowPlayingList.map { movieEntity ->
                    movieModelMapper.mapEntityToModel(
                        entity = movieEntity,
                        genreList = genreModelList,
                        posterSize = MoviePosterSize.W780,
                        language = language
                    )
                }
                val popularMovieModelList = popularList.map { movieEntity ->
                    movieModelMapper.mapEntityToModel(
                        entity = movieEntity, genreList = genreModelList, language = language
                    )
                }
                val topRatedMovieModelList = topRatedList.map { movieEntity ->
                    movieModelMapper.mapEntityToModel(
                        entity = movieEntity, genreList = genreModelList, language = language
                    )
                }
                val upComingMovieModelList = upcomingList.map { movieEntity ->
                    movieModelMapper.mapEntityToModel(
                        entity = movieEntity, genreList = genreModelList, language = language
                    )
                }

                mapOf(
                    MovieType.NOW_PLAYING to nowPlayingMovieModelList,
                    MovieType.POPULAR to popularMovieModelList,
                    MovieType.TOP_RATED to topRatedMovieModelList,
                    MovieType.UPCOMING to upComingMovieModelList
                )
            }.first()
        }
    }
}
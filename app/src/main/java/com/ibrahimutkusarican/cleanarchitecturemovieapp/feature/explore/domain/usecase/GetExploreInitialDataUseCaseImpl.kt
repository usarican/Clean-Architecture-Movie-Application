package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.usecase

import com.iusarican.common.base.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.model.ExploreInitialDataModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.mapper.HomeMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.helper.MoviePosterSize
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import com.iusarican.Language
import com.iusarican.datastore.UserSettingsDataStore
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
                        posterSize = MoviePosterSize.W500,
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
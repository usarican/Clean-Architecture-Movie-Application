package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.mapper.ExploreGenreModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.model.ExploreInitialDataModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.mapper.HomeMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.MoviePosterSize
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetExploreInitialDataUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieModelMapper: HomeMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
    private val exploreGenreModelMapper: ExploreGenreModelMapper
) : GetExploreInitialDataUseCase, BaseUseCase() {
    override fun getExploreInitialData(): Flow<UiState<ExploreInitialDataModel>> {
        return execute {
            combine(
                getMovieGenreUseCase.getMovieGenresUseCase(),
                movieRepository.getMoviesByType(MovieType.UPCOMING, limit = 10),
                movieRepository.getMoviesByType(MovieType.POPULAR),
            ) { stateGenres, stateUpcoming, statePopular ->
                val genreModelList = stateGenres.getSuccessOrThrow()
                val upcomingList = stateUpcoming.getSuccessOrThrow()
                val popularList = statePopular.getSuccessOrThrow()

                val exploreGenreModel = genreModelList.map { genreModel ->
                    exploreGenreModelMapper.genreModelToExploreGenreModel(genreModel)
                }
                val bannerMovieList = upcomingList.map { movieEntity ->
                    movieModelMapper.mapEntityToModel(
                        entity = movieEntity,
                        genreList = genreModelList,
                        posterSize = MoviePosterSize.W780
                    )
                }
                val popularMovieModelList = popularList.map { movieEntity ->
                    movieModelMapper.mapEntityToModel(
                        entity = movieEntity, genreList = genreModelList
                    )
                }

                ExploreInitialDataModel(
                    genreList = exploreGenreModel,
                    bannerMovies = bannerMovieList,
                    popularMovies = popularMovieModelList
                )
            }.first()
        }
    }
}
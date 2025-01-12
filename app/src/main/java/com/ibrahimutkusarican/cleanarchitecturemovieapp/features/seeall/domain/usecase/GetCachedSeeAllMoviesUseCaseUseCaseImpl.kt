package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase.GetMovieGenresUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.mapper.SeeAllMovieModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GetCachedSeeAllMoviesUseCaseUseCaseImpl @Inject constructor(
    private val seeAllRepository: SeeAllRepository,
    private val seeAllMovieModelMapper: SeeAllMovieModelMapper,
    private val getMovieGenreUseCase: GetMovieGenresUseCase,
) : GetCachedSeeAllMoviesUseCase, BaseUseCase() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCachedMoviesByType(movieType: MovieType): Flow<UiState<List<SeeAllMovieModel>>> {
        return getMovieGenreUseCase.getMovieGenresUseCase().flatMapLatest { genreState ->
            execute {
                val genreList = genreState.getSuccessOrThrow()
                seeAllRepository.getCachedMoviesByType(movieType).map { movieEntity ->
                    seeAllMovieModelMapper.entityToModel(
                        movieEntity = movieEntity, genreList = genreList
                    )
                }
            }
        }
    }

}
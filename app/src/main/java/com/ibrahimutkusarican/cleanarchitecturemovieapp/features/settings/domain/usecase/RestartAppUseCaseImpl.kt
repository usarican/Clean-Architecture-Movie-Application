package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.base.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.feature.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.SearchRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RestartAppUseCaseImpl @Inject constructor(
    private val genreRepository: GenreRepository,
    private val movieRepository: MovieRepository,
    private val searchRepository: SearchRepository
) : BaseUseCase(), RestartAppUseCase {
    override fun restartApp(): Flow<UiState<Unit>> {
        return execute {
            combine(
                genreRepository.deleteAllGenreList(),
                movieRepository.deleteAllCachedMovies(),
                searchRepository.deleteAllSearchQueries()
            ) { genreDeleteState, movieDeleteState, lastSearchDeleteState ->
                genreDeleteState.getSuccessOrThrow()
                movieDeleteState.getSuccessOrThrow()
                lastSearchDeleteState.getSuccessOrThrow()
            }.first()
        }
    }
}
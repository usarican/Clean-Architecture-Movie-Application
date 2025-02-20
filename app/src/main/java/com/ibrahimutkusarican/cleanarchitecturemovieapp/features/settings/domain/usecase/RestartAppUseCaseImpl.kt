package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.getSuccessOrThrow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class RestartAppUseCaseImpl @Inject constructor(
    private val genreRepository: GenreRepository,
    private val movieRepository: MovieRepository
) : BaseUseCase(), RestartAppUseCase {
    override fun restartApp(): Flow<UiState<Unit>> {
        return execute {
            combine(
                genreRepository.deleteAllGenreList(),
                movieRepository.deleteAllCachedMovies()
            ) { genreDeleteState, movieDeleteState ->
                genreDeleteState.getSuccessOrThrow()
                movieDeleteState.getSuccessOrThrow()
            }.first()
        }
    }
}
package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.main.domain

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseUseCase
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/// TODO: Restart App'de Genre,Movies tarafını silebilirim fakat favorite ve watchlist'te text olan değerleri kaldırıp, detayı çekip tekrardan updatelemem gerekiyor 

class RestartAppUseCaseImpl @Inject constructor(
    private val genreRepository: GenreRepository,
    private val movieRepository: MovieRepository
) : BaseUseCase(), RestartAppUseCase {
    override fun restartApp(): Flow<UiState<Boolean>> {
        return execute {
            true
        }
    }
}
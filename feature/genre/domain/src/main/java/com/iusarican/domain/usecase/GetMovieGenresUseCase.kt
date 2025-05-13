package com.iusarican.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.iusarican.common.base.BaseUseCase
import com.iusarican.domain.model.GenreModel
import com.iusarican.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieGenresUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) : BaseUseCase() {
    operator fun invoke(): Flow<UiState<List<GenreModel>>> {
        return execute {
            genreRepository.getMovieGenreList()
        }
    }
}
package com.iusarican.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.iusarican.domain.model.GenreModel
import com.iusarican.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieGenresUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {
    operator fun invoke(): Flow<ApiState<List<GenreModel>>> {
        return genreRepository.getMovieGenreList()
    }
}
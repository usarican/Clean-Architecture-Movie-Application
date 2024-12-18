package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository.GenreRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.mapper.GenreModelMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.domain.model.GenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieGenresUseCaseImpl @Inject constructor(
    private val genreRepository: GenreRepository, private val genreModelMapper: GenreModelMapper
) : GetMovieGenresUseCase {
    override fun getMovieGenresUseCase(): Flow<ApiState<List<GenreModel>>> {
        return genreRepository.getMovieGenreList().map { state ->
                state.map { genreEntities ->
                    genreEntities.map { genreEntity -> genreModelMapper.mapEntityToModel(genreEntity) }
                }
            }
    }
}
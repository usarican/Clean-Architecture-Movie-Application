package com.iusarican.data.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.iusarican.common.base.BaseRepository
import com.iusarican.data.mapper.GenreResponseMapper
import com.iusarican.data.local.GenreLocalDataSource
import com.iusarican.data.remote.GenreRemoteDataSource
import com.iusarican.data.mapper.GenreModelMapper
import com.iusarican.domain.model.GenreModel
import com.iusarican.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val genreLocalDataSource: GenreLocalDataSource,
    private val genreResponseMapper: GenreResponseMapper,
    private val genreModelMapper: GenreModelMapper
) : BaseRepository(), GenreRepository {

    override suspend fun getMovieGenreList(): List<GenreModel> {
        val entities = genreLocalDataSource.getAllGenres().ifEmpty {
            val genreResponse = genreRemoteDataSource.getMovieGenreList()
            genreLocalDataSource.insertAllGenres(genreResponse.genreList.map { genre ->
                genreResponseMapper.mapResponseToEntity(
                    genre
                )
            })
            genreLocalDataSource.getAllGenres()
        }
        return entities.map { entity ->
            genreModelMapper.mapEntityToModel(entity)
        }
    }

    override suspend fun deleteAllGenreList(): Boolean {
        genreLocalDataSource.deleteAllGenres()
        return true
    }
}
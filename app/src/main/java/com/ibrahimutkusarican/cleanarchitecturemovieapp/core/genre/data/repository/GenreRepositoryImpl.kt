package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.mapper.GenreResponseMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.GenreLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote.GenreRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject
import kotlin.math.exp

class GenreRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val genreLocalDataSource: GenreLocalDataSource,
    private val genreResponseMapper: GenreResponseMapper
) : BaseRepository(), GenreRepository {

    override fun getMovieGenreList(): Flow<ApiState<List<GenreEntity>>> {
        return apiCall {
            genreLocalDataSource.getAllGenres().ifEmpty {
                val genreResponse = genreRemoteDataSource.getMovieGenreList()
                genreLocalDataSource.insertAllGenres(genreResponse.genreList.map { genre ->
                    genreResponseMapper.mapResponseToEntity(
                        genre
                    )
                })
                genreLocalDataSource.getAllGenres()
            }
        }
    }
}
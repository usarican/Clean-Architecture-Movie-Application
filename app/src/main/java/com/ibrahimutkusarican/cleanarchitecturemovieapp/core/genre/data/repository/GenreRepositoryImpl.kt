package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.repository

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.State
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.mapper.GenreResponseMapper
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.GenreLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.remote.GenreRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreRemoteDataSource: GenreRemoteDataSource,
    private val genreLocalDataSource: GenreLocalDataSource,
    private val genreResponseMapper: GenreResponseMapper
) : BaseRepository(), GenreRepository {

    override fun getMovieGenreList(): Flow<State<List<GenreEntity>>> {
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
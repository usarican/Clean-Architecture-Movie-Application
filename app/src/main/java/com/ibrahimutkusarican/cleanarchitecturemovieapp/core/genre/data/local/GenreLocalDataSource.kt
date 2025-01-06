package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity
import javax.inject.Inject

class GenreLocalDataSource @Inject constructor(
    private val genreDao: GenreDao
) {
    suspend fun insertAllGenres(genreList: List<GenreEntity>) {
        genreDao.insertAll(genreList)
    }

    suspend fun getAllGenres(): List<GenreEntity> =
        genreDao.getGenres()
}
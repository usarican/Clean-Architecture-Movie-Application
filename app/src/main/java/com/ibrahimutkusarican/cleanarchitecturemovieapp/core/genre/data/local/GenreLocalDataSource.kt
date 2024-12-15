package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.genre.data.local.entity.GenreEntity
import javax.inject.Inject

class GenreLocalDataSource @Inject constructor(
    private val genreDao: GenreDao
) {
    suspend fun insertAllGenres(vararg genreEntity: GenreEntity) {
        genreDao.insertAll(*genreEntity)
    }

    suspend fun getAllGenres(): List<GenreEntity> =
        genreDao.getGenres()
}
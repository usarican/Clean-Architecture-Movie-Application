package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.genre.data.local

import com.iusarican.data.local.GenreDao
import com.iusarican.data.local.entity.GenreEntity
import javax.inject.Inject

class GenreLocalDataSource @Inject constructor(
    private val genreDao: GenreDao
) {
    suspend fun insertAllGenres(genreList: List<GenreEntity>) {
        genreDao.insertAll(genreList)
    }

    suspend fun getAllGenres(): List<GenreEntity> =
        genreDao.getGenres()

    suspend fun deleteAllGenres() =
        genreDao.deleteAllGenres()
}
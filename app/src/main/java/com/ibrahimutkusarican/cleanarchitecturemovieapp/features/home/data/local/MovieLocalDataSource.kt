package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }
    suspend fun getAllMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }
    suspend fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }
}
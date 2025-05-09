package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun insertMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

    suspend fun getMovieById(movieId: Int): MovieEntity? {
        return movieDao.getMovieById(movieId)
    }

    suspend fun getMoviesByType(movieType: MovieType): List<MovieEntity> {
        return movieDao.getMoviesByType(movieType)
    }

    suspend fun deleteMoviesByType(movieType: MovieType) {
        movieDao.deleteMoviesByType(movieType)
    }

    suspend fun deleteAllMovies(){
        movieDao.deleteAllMovies()
    }
}
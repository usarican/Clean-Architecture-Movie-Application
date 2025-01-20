package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local

import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    private val lastVisitedMovieDao: LastVisitedMovieDao
) {
    suspend fun insertLastVisitedMovie(entity: LastVisitedMovieEntity) {
        lastVisitedMovieDao.insertLastVisitedMovie(movie = entity)
    }

    suspend fun getLastVisitedMovies() : List<LastVisitedMovieEntity> =
        lastVisitedMovieDao.getLastVisitedMovies()
}
package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.LastVisitedMovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.LastVisitedMovieEntity
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
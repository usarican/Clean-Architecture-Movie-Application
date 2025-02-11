package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local

import javax.inject.Inject

class DetailLocalDataSource @Inject constructor(
    private val visitedMovieDao: VisitedMovieDao
) {
    suspend fun insertVisitedMovie(visitedMovieEntity: VisitedMovieEntity) {
        visitedMovieDao.insertVisitedMovie(visitedMovieEntity)
        val count = visitedMovieDao.getMovieCount()
        if (count > 10) {
            visitedMovieDao.deleteOldestMovie()
        }
    }
}
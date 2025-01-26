package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local

import javax.inject.Inject

class DetailLocalDataSource @Inject constructor(
    private val visitedMovieDao: VisitedMovieDao
) {
    suspend fun getVisitedMovies() = visitedMovieDao.getVisitedMovies()
    suspend fun insertVisitedMovie(visitedMovieEntity: VisitedMovieEntity) =
        visitedMovieDao.insertVisitedMovie(visitedMovieEntity)

    suspend fun deleteVisitedMovie(visitedMovieEntity: VisitedMovieEntity) {
        visitedMovieDao.deleteVisitedMovie(visitedMovieEntity)
    }

}
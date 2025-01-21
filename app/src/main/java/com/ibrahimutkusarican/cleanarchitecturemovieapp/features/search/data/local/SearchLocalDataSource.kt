package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.VisitedMovieDao
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local.VisitedMovieEntity
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    private val visitedMovieDao: VisitedMovieDao
) {
    suspend fun insertLastVisitedMovie(entity: VisitedMovieEntity) {
        visitedMovieDao.insertLastVisitedMovie(movie = entity)
    }

    suspend fun getLastVisitedMovies() : List<VisitedMovieEntity> =
        visitedMovieDao.getLastVisitedMovies()
}
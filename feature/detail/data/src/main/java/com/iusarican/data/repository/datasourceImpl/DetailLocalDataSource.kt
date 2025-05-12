package com.iusarican.data.repository.datasourceImpl

import com.iusarican.data.database.VisitedMovieDao
import com.iusarican.data.model.local.VisitedMovieEntity
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
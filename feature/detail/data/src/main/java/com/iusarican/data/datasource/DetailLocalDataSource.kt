package com.iusarican.data.datasource

import com.iusarican.common.utils.Constants.VISIBLE_VISITED_MOVIE_THRESHOLD
import com.iusarican.data.database.VisitedMovieDao
import com.iusarican.data.model.local.VisitedMovieEntity
import javax.inject.Inject

class DetailLocalDataSource @Inject constructor(
    private val visitedMovieDao: VisitedMovieDao
) {
    suspend fun insertVisitedMovie(visitedMovieEntity: VisitedMovieEntity) {
        visitedMovieDao.insertVisitedMovie(visitedMovieEntity)
        val count = visitedMovieDao.getMovieCount()
        if (count > VISIBLE_VISITED_MOVIE_THRESHOLD) {
            visitedMovieDao.deleteOldestMovie()
        }
    }
}
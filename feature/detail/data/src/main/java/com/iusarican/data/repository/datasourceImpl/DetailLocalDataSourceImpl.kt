package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasourceImpl

import com.iusarican.data.database.VisitedMovieDao
import com.iusarican.data.model.local.VisitedMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasource.DetailLocalDataSource
import javax.inject.Inject

class DetailLocalDataSourceImpl @Inject constructor(
    private val visitedMovieDao: VisitedMovieDao
): DetailLocalDataSource {

    override suspend fun insertVisitedMovie(visitedMovieEntity: VisitedMovieEntity) {
        visitedMovieDao.insertVisitedMovie(visitedMovieEntity)
        val count = visitedMovieDao.getMovieCount()
        if (count > 10) {
            visitedMovieDao.deleteOldestMovie()
        }
    }
}
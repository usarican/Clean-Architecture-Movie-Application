package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.data.repository.datasource

import com.iusarican.data.model.local.VisitedMovieEntity

interface DetailLocalDataSource {

    suspend fun insertVisitedMovie(visitedMovieEntity: VisitedMovieEntity)

}
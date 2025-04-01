package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.repository.datasource

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.local.VisitedMovieEntity

interface DetailLocalDataSource {

    suspend fun insertVisitedMovie(visitedMovieEntity: VisitedMovieEntity)

}
package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface VisitedMovieDao {
    @Upsert
    suspend fun insertLastVisitedMovie(movie : VisitedMovieEntity)

    @Query("SELECT * FROM visited_movie")
    suspend fun getLastVisitedMovies() : List<VisitedMovieEntity>

    @Delete
    suspend fun deleteLastVisitedMovie(movie : VisitedMovieEntity)

}
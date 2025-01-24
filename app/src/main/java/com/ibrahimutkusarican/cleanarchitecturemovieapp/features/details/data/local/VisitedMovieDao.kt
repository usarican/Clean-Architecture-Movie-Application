package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface VisitedMovieDao {
    @Upsert
    suspend fun insertVisitedMovie(movie : VisitedMovieEntity)

    @Query("SELECT * FROM visited_movie")
    suspend fun getVisitedMovies() : List<VisitedMovieEntity>

    @Delete
    suspend fun deleteVisitedMovie(movie : VisitedMovieEntity)

}
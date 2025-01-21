package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface LastVisitedMovieDao {
    @Upsert
    suspend fun insertLastVisitedMovie(movie : LastVisitedMovieEntity )

    @Query("SELECT * FROM last_visited_movie")
    suspend fun getLastVisitedMovies() : List<LastVisitedMovieEntity>

    @Delete
    suspend fun deleteLastVisitedMovie(movie : LastVisitedMovieEntity)

}
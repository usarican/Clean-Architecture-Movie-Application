package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.data.model.local.VisitedMovieEntity

@Dao
interface VisitedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisitedMovie(movie : VisitedMovieEntity)

    @Query("SELECT * FROM visited_movie ORDER BY visited_time DESC")
    suspend fun getVisitedMovies() : List<VisitedMovieEntity>

    @Delete
    suspend fun deleteVisitedMovie(movie : VisitedMovieEntity)

    @Query("DELETE FROM visited_movie WHERE movie_id = (SELECT movie_id FROM visited_movie ORDER BY visited_time ASC LIMIT 1)")
    suspend fun deleteOldestMovie()

    @Query("SELECT COUNT(*) FROM visited_movie")
    suspend fun getMovieCount(): Int
}
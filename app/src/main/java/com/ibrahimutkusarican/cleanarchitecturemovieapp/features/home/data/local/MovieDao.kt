package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_table WHERE movie_types LIKE '%' || :movieType || '%'")
    suspend fun getMoviesByType(movieType: MovieType): List<MovieEntity>

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie_table WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?
}
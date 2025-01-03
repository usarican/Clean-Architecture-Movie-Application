package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType

@Dao
interface MovieDao {
    @Upsert
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_table WHERE movie_types LIKE '%' || :movieType || '%' ORDER BY popularity DESC")
    suspend fun getMoviesByType(movieType: MovieType): List<MovieEntity>

    @Query("DELETE FROM movie_table WHERE movie_types LIKE '%' || :movieType || '%'")
    suspend fun deleteMoviesByType(movieType: MovieType)

    @Query("SELECT * FROM movie_table WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?
}
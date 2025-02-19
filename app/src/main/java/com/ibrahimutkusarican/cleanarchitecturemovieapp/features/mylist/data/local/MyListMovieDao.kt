package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface MyListMovieDao {
    @Upsert
    suspend fun insertMyListMovie(movie: MyListMovieEntity)

    @Query("SELECT * FROM my_list_movie WHERE is_favorite = 1 ORDER BY added_time DESC")
    fun getFavoriteMoviesPaging(): PagingSource<Int, MyListMovieEntity>

    @Query("SELECT * FROM my_list_movie WHERE add_watch_list = 1 ORDER BY added_time DESC")
    fun getWatchListMoviesPaging(): PagingSource<Int, MyListMovieEntity>

    @Delete
    suspend fun deleteMyListMovie(movie: MyListMovieEntity): Int

    @Query("SELECT is_favorite as isAddedFavorite ,add_watch_list as isAddedWatchList FROM my_list_movie WHERE movie_id = :movieId")
    suspend fun getMyListMovieFavoriteAndWatchListStatus(movieId: Int): MovieFavoriteAndWatchListStatus?

    @Query("SELECT movie_id FROM my_list_movie")
    suspend fun getMyListMovieIds(): List<Int>

}
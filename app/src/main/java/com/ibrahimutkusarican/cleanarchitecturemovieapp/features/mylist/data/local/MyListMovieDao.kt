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

    @Query("SELECT * FROM my_list_movie WHERE is_favorite = 1")
    fun getFavoriteMoviesPaging(): PagingSource<Int, MyListMovieEntity>

    @Query("SELECT * FROM my_list_movie WHERE add_watch_list = 1")
    fun getWatchListMoviesPaging(): PagingSource<Int, MyListMovieEntity>

    @Delete
    suspend fun deleteMyListMovie(movie: MyListMovieEntity)
}
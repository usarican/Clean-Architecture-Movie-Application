package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.local.MovieFavoriteAndWatchListStatus
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.local.MyListMovieEntity
import kotlinx.coroutines.flow.Flow

interface MyListRepository {
    fun getFavoriteMoviesPaging(): Flow<PagingData<MyListMovieEntity>>
    fun getWatchListMoviesPaging(): Flow<PagingData<MyListMovieEntity>>
    fun insertMyListMovie(movieEntity: MyListMovieEntity): Flow<ApiState<Unit>>
    fun deleteMyListMovie(movieEntity: MyListMovieEntity): Flow<ApiState<Int>>
    fun getMyListMovieFavoriteAndWatchListStatus(movieId: Int): Flow<ApiState<MovieFavoriteAndWatchListStatus?>>
    suspend fun getMyListMoviesIdList() : List<Int>
    suspend fun updateMyListMovie(movieId: Int, title: String, overview: String, releaseDate: String)
}
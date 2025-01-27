package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.local.MyListMovieEntity
import kotlinx.coroutines.flow.Flow

interface MyListRepository {
    fun getFavoriteMoviesPaging(): Flow<PagingData<MyListMovieEntity>>
    fun getWatchListMoviesPaging(): Flow<PagingData<MyListMovieEntity>>
    fun insertMyListMovie(movieEntity: MyListMovieEntity) : Flow<ApiState<MyListMovieEntity>>
    fun deleteMyListMovie(movieEntity: MyListMovieEntity) : Flow<ApiState<MyListMovieEntity>>
}
package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.local.MyListLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.local.MyListMovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.MY_LIST_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyListRepositoryImpl @Inject constructor(
    private val myListLocalDataSource: MyListLocalDataSource
) : BaseRepository(), MyListRepository {
    override fun getFavoriteMoviesPaging(): Flow<PagingData<MyListMovieEntity>> {
        return Pager(config = PagingConfig(pageSize = MY_LIST_PAGE_SIZE), pagingSourceFactory = {
            myListLocalDataSource.getFavoriteMoviesPaging()
        }).flow
    }

    override fun getWatchListMoviesPaging(): Flow<PagingData<MyListMovieEntity>> {
        return Pager(config = PagingConfig(pageSize = MY_LIST_PAGE_SIZE), pagingSourceFactory = {
            myListLocalDataSource.getWatchListMoviesPaging()
        }).flow
    }

    override fun insertMyListMovie(movieEntity: MyListMovieEntity): Flow<ApiState<MyListMovieEntity>> {
        TODO("Not yet implemented")
    }

    override fun deleteMyListMovie(movieEntity: MyListMovieEntity): Flow<ApiState<MyListMovieEntity>> {
        TODO("Not yet implemented")
    }
}
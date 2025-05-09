package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.iusarican.common.base.BaseRepository
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.local.MovieFavoriteAndWatchListStatus
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.local.MyListLocalDataSource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.data.local.MyListMovieEntity
import com.iusarican.common.utils.Constants.MY_LIST_PAGE_SIZE
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

    override fun insertMyListMovie(movieEntity: MyListMovieEntity): Flow<ApiState<Unit>> {
        return apiCall { myListLocalDataSource.insertMyListMovie(movieEntity) }
    }

    override fun deleteMyListMovie(movieEntity: MyListMovieEntity): Flow<ApiState<Int>> {
        return apiCall { myListLocalDataSource.deleteMyListMovie(movieEntity) }
    }

    override fun getMyListMovieFavoriteAndWatchListStatus(movieId: Int): Flow<ApiState<MovieFavoriteAndWatchListStatus?>> =
        apiCall{ myListLocalDataSource.getMyListMovieFavoriteAndWatchListStatus(movieId) }

    override suspend fun getMyListMoviesIdList(): List<Int> {
        return myListLocalDataSource.getMyListMoviesIdList()
    }

    override suspend fun updateMyListMovie(
        movieId: Int,
        title: String,
        overview: String,
        releaseDate: String
    ) {
        myListLocalDataSource.updateMyListMovie(movieId, title, overview, releaseDate)
    }
}
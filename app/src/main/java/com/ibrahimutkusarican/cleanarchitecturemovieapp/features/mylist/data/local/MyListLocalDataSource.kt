package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.data.local

import javax.inject.Inject

class MyListLocalDataSource @Inject constructor(
    private val myListMovieDao: MyListMovieDao
) {
    suspend fun insertMyListMovie(movieEntity: MyListMovieEntity) {
        myListMovieDao.insertMyListMovie(movieEntity)
    }

    fun getFavoriteMoviesPaging() = myListMovieDao.getFavoriteMoviesPaging()
    fun getWatchListMoviesPaging() = myListMovieDao.getWatchListMoviesPaging()
    suspend fun deleteMyListMovie(movieEntity: MyListMovieEntity) {
        myListMovieDao.deleteMyListMovie(movieEntity)
    }
}
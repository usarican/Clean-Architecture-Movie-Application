package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data

import androidx.paging.PagingData
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.response.MovieResultResponse
import kotlinx.coroutines.flow.Flow

interface SeeAllRepository {
    suspend fun getCachedMoviesByType(movieType: MovieType) : List<MovieEntity>
    fun getSeeAllMoviesByType(movieType: MovieType) : Flow<PagingData<MovieResultResponse>>
}
package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieEntity
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.local.entity.MovieType
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun getMoviesByType(movieType: MovieType,limit : Int = 20) : Flow<ApiState<List<MovieEntity>>>
    fun refreshMoviesByType(movieType: MovieType,limit : Int = 20) : Flow<ApiState<List<MovieEntity>>>
    fun deleteAllCachedMovies() : Flow<ApiState<Unit>>
}
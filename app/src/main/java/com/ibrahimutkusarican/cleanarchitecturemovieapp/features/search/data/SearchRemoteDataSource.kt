package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.MovieService
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val searchService: SearchService,
    private val movieService: MovieService
) {
   suspend fun searchMovies(searchText: String, page: Int) = searchService.searchMovies(searchText, page)
    suspend fun getRecommendedMovieById(movieId : Int) = movieService.getRecommendedMovies(movieId)
}
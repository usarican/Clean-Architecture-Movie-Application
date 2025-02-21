package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.remote.MovieService
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val searchService: SearchService,
    private val movieService: MovieService,
    private val regionsService: RegionsService
) {
    suspend fun searchMovies(searchText: String, page: Int) =
        searchService.searchMovies(searchText, page)

    suspend fun getRecommendedMovieById(movieId: Int) = movieService.getRecommendedMovies(movieId)

    suspend fun getRegionsFromRemote() = regionsService.getRegions()
}
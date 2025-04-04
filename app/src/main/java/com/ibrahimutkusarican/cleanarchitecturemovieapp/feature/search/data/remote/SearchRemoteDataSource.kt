package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.data.remote

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.data.remote.MovieService
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

    suspend fun sortOrFilterMovies(
        page: Int,
        releaseYear : Int? = null,
        sortBy: String = "popularity_desc",
        genre : String? = null,
        region : String? = null
    ) = searchService.filterAndSortMovies(
        page = page,
        releaseYear = releaseYear,
        sortBy = sortBy,
        genres = genre,
        region = region
    )
}
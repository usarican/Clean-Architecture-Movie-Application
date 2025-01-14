package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.data

import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val searchService: SearchService
) {
   suspend fun searchMovies(searchText: String, page: Int) = searchService.searchMovies(searchText, page)
}
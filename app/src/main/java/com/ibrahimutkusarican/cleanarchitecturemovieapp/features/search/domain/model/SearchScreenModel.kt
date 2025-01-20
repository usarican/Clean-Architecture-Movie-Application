package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.domain.model.SeeAllMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.EMPTY_STRING

data class SearchScreenModel(
    val searchText : String = EMPTY_STRING,
    val lastSearchKeys : List<String> = listOf("Batman","Spiderman","Avengers"),
    val topSearchedMovies : List<String> = listOf("Batman","Spiderman","Avengers"),
    val recommendedMoviesForYou : List<BasicMovieModel> = emptyList(),
    val recentlyViewedMovies : List<SeeAllMovieModel> = emptyList()
)

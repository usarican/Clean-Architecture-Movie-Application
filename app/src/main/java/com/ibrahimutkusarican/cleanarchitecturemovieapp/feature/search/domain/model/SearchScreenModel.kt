package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.search.domain.model

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.domain.model.SeeAllMovieModel
import com.iusarican.common.utils.Constants.EMPTY_STRING

data class SearchScreenModel(
    val searchText: String = EMPTY_STRING,
    val lastSearchQueries: List<SearchItemModel> = emptyList(),
    val topSearchedMovies: List<SearchItemModel> = emptyList(),
    val recommendedMoviesForYou: List<BasicMovieModel> = emptyList(),
    val recentlyViewedMovies: List<SeeAllMovieModel> = emptyList()
)

val turkishTopSearchedMovies = listOf(
    SearchItemModel(1, "Esaretin Bedeli"),
    SearchItemModel(2, "Baba"),
    SearchItemModel(3, "Baba 2"),
    SearchItemModel(4, "Kara Şövalye"),
)

val englishTopSearchedMovies = listOf(
    SearchItemModel(1, "The Shawshank Redemption"),
    SearchItemModel(2, "The Godfather"),
    SearchItemModel(3, "The Godfather: Part II"),
    SearchItemModel(4, "The Dark Knight"),
)

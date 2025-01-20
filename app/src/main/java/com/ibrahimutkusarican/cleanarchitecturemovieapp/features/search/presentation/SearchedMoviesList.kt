package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.domain.model.SearchMovieModel

@Composable
fun SearchedMoviesList(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<SearchMovieModel>,
) {

}
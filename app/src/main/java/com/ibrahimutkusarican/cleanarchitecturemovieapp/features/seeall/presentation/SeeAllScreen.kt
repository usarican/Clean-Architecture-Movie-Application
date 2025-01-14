package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MySearchBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.TopBar

@Composable
fun SeeAllScreen(
    modifier: Modifier = Modifier, viewModel: SeeAllViewModel
) {
    val movieType by viewModel.movieType.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val pagingMovies = viewModel.pagingMovies.collectAsLazyPagingItems()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopBar(
            title = stringResource(
                when (movieType) {
                    MovieType.NOW_PLAYING, null -> R.string.see_all
                    MovieType.POPULAR -> R.string.popular_movies
                    MovieType.TOP_RATED -> R.string.top_rated_movies
                    MovieType.UPCOMING -> R.string.up_coming_movies
                }
            )
        )
        MySearchBar(searchText = searchText, onSearch = { searchText ->
            viewModel.handleUiActions(SeeAllUiAction.SearchAction(searchText))
        })
        SeeAllMovies(
            modifier = Modifier.weight(1F),
            pagingMovies = pagingMovies,
        )
    }
}
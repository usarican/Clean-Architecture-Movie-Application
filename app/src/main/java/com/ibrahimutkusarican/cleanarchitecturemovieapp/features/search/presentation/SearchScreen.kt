package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MySearchBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MyTopBar

@Composable
@Preview(showBackground = true)
fun SearchScreen() {
    val viewModel = hiltViewModel<SearchViewModel>()
    val searchScreenModel by viewModel.searchScreenModel.collectAsStateWithLifecycle()
    val searchedMovies = viewModel.searchedMovies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MyTopBar(
            title = stringResource(R.string.search),
            onBackClick = {
                viewModel.handleSearchScreenAction(SearchUiAction.OnBackPress)
            }
        )
        MySearchBar(
            searchText = searchScreenModel.searchText,
            onSearch = { searchText ->
                viewModel.handleSearchScreenAction(
                    SearchUiAction.SearchAction(
                        searchText
                    )
                )
            }
        )
        TopSearch(
            topSearchMovieNames = searchScreenModel.topSearchedMovies
        )
        LastSearch(
            lastSearch = searchScreenModel.lastSearchKeys
        )
        RecommendedMoviesForYou(
            movies = searchScreenModel.recommendedMoviesForYou
        )
        RecentlyViewedMovies(
            movies = searchScreenModel.recentlyViewedMovies
        )
        if (searchScreenModel.searchText.isNotEmpty()) {
            SearchedMoviesList(
                pagingMovies = searchedMovies
            )
        }

    }
}






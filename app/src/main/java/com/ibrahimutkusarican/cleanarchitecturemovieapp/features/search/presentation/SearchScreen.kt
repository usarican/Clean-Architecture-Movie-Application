package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.BaseUiStateComposable
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MySearchBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MyTopBar

@Composable
fun SearchScreen(viewModel: SearchViewModel, recommendedMovieId: Int?) {
    LaunchedEffect(recommendedMovieId) {
        viewModel.getSearchScreenModel(recommendedMovieId)
    }
    val searchScreenModel by viewModel.searchScreenModel.collectAsStateWithLifecycle()
    val searchedMovies = viewModel.searchedMovies.collectAsLazyPagingItems()
    val uiState by viewModel.searchScreenUiState.collectAsStateWithLifecycle()

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
        BaseUiStateComposable(
            uiState = uiState,
            tryAgainOnClickAction = {}
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.medium_padding))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
            ) {
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
    }
}






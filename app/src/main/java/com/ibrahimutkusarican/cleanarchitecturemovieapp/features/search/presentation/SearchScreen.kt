package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.BaseUiStateComposable
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MySearchBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MyTopBar

@SuppressLint("RememberReturnType")
@Composable
fun SearchScreen(viewModel: SearchViewModel, recommendedMovieId: Int?) {
    LaunchedEffect(recommendedMovieId) {
        viewModel.getSearchScreenModel(recommendedMovieId)
    }
    val searchScreenModel by viewModel.searchScreenModel.collectAsStateWithLifecycle()
    val searchedMovies = viewModel.searchedMovies.collectAsLazyPagingItems()
    val uiState by viewModel.searchScreenUiState.collectAsStateWithLifecycle()
    val searchFilterState by viewModel.searchFilterState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        MyTopBar(
            title = stringResource(R.string.search),
            onBackClick = {
                if (searchScreenModel.searchText.isEmpty()) {
                    viewModel.handleSearchScreenAction(SearchUiAction.OnBackPress)
                } else {
                    viewModel.handleSearchScreenAction(SearchUiAction.SearchAction(Constants.EMPTY_STRING))
                }
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(R.dimen.large_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            MySearchBar(
                modifier = Modifier
                    .animateContentSize()
                    .weight(1F),
                searchText = searchScreenModel.searchText,
                onSearch = { searchText ->
                    viewModel.handleSearchScreenAction(
                        SearchUiAction.SearchAction(
                            searchText
                        )
                    )
                },
            )
            Card(
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.small_padding))
                    .size(dimensionResource(R.dimen.circle_icon_size)),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.small_card_elevation))
            ) {
                Box(Modifier
                    .fillMaxSize()
                    .clickable {
                        viewModel.handleSearchScreenAction(
                            SearchUiAction.FilterAndSortActions.FilterAndSortButtonClickAction(
                                searchFilterState.second
                            )
                        )
                    }) {
                    Icon(
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.icon_size))
                            .padding(dimensionResource(R.dimen.small_padding))
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.ic_filter),
                        contentDescription = "Filter Icon",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

            }

        }

        if (searchScreenModel.searchText.isEmpty()) {
            BaseUiStateComposable(
                uiState = uiState,
                tryAgainOnClickAction = {
                    viewModel.handleSearchScreenAction(SearchUiAction.ErrorTryAgainAction)
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(R.dimen.medium_padding)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
                ) {
                    TopSearch(
                        topSearchMovieNames = searchScreenModel.topSearchedMovies,
                        handleSearchUiAction = viewModel::handleSearchScreenAction
                    )
                    LastSearch(
                        lastSearch = searchScreenModel.lastSearchKeys,
                        handleSearchUiAction = viewModel::handleSearchScreenAction
                    )
                    RecommendedMoviesForYou(
                        movies = searchScreenModel.recommendedMoviesForYou,
                        movieClickAction = { movieId ->
                            viewModel.handleSearchScreenAction(SearchUiAction.MovieClick(movieId))
                        },
                        seeAllClickAction = {
                            viewModel.handleSearchScreenAction(SearchUiAction.RecommendedMovieSeeAllClickAction)
                        }
                        // TODO: Handle see All action
                    )
                    if (searchScreenModel.recentlyViewedMovies.isNotEmpty()) {
                        RecentlyViewedMovies(
                            movies = searchScreenModel.recentlyViewedMovies,
                            movieClickAction = { movieId ->
                                viewModel.handleSearchScreenAction(SearchUiAction.MovieClick(movieId))
                            }
                        )
                    }
                }
            }
        } else {
            SearchedMoviesList(
                pagingMovies = searchedMovies,
                handleUiAction = viewModel::handleSearchScreenAction
            )
        }
    }
}






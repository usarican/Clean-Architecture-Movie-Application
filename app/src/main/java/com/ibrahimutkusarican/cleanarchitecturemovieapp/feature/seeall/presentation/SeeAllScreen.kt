package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.exception.MovieException
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.component.MovieSearchBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget.MovieTopBar

@Composable
fun SeeAllScreen(
    modifier: Modifier = Modifier, viewModel: SeeAllViewModel
) {
    val seeAllType by viewModel.seeAllType.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val pagingMovies = viewModel.pagingMovies.collectAsLazyPagingItems()
    val pagingReviews = viewModel.pagingReviews.collectAsLazyPagingItems()
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        MovieTopBar(title = stringResource(
            when (seeAllType) {
                is SeeAllType.MovieReviews -> R.string.reviews
                is SeeAllType.RecommendationMovies -> R.string.recommended_for_you
                SeeAllType.SeeAllMovieType.NowPlaying -> R.string.see_all
                SeeAllType.SeeAllMovieType.Popular -> R.string.popular_movies
                SeeAllType.SeeAllMovieType.TopRated -> R.string.top_rated_movies
                SeeAllType.SeeAllMovieType.UpComing -> R.string.up_coming_movies
                null -> R.string.see_all
            }
        ), onBackClick = {
            viewModel.handleUiActions(SeeAllUiAction.OnBackPress)
        })
        when (seeAllType) {
            is SeeAllType.MovieReviews -> {
                SeeAllReviews(modifier = Modifier.weight(1F), pagingReviews = pagingReviews)
            }

            null -> ErrorScreen(exception = MovieException.NotFoundException(stringResource(R.string.error_content_not_found)))
            else -> {
                MovieSearchBar(
                    modifier = Modifier.padding(dimensionResource(R.dimen.large_padding)),
                    searchText = searchText, onSearch = { searchText ->
                        viewModel.handleUiActions(SeeAllUiAction.SearchAction(searchText))
                    })
                SeeAllMovies(
                    modifier = Modifier.weight(1F),
                    pagingMovies = pagingMovies,
                    handleUiActions = viewModel::handleUiActions
                )
            }
        }

    }
}
package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.model.ExploreInitialDataModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.presentation.MovieCategory
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.component.MovieSearchBar

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ExploreScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val viewModel = hiltViewModel<ExploreViewModel>()
    val uiState by viewModel.exploreUiState.collectAsStateWithLifecycle()
    val data by viewModel.exploreInitialData.collectAsStateWithLifecycle()
    when (uiState) {
        is UiState.Error -> ErrorScreen(
            exception = (uiState as UiState.Error).exception,
            tryAgainOnClickAction = { viewModel.handleUiAction(ExploreUiAction.ErrorRetryAction) })

        UiState.Loading -> LoadingScreen()
        is UiState.Success -> {
            ExploreSuccessScreen(
                data = data,
                handleUiAction = viewModel::handleUiAction,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )
        }
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MostPopularMovies(
    modifier: Modifier = Modifier,
    movies: List<BasicMovieModel>,
    seeAllClickAction: (seeAllType: SeeAllType) -> Unit = {},
    movieClickAction: (movieId: Int, sharedAnimationKey: String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    MovieCategory(
        modifier = modifier,
        seeAllMovieType = SeeAllType.SeeAllMovieType.Popular,
        title = stringResource(R.string.most_popular),
        movies = movies,
        seeAllClickAction = seeAllClickAction,
        movieClickAction = movieClickAction,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun ExploreSuccessScreen(
    data: ExploreInitialDataModel = ExploreInitialDataModel(
        forYouMovie = BasicMovieModel(
            movieId = 1,
            movieTitle = "The Batman",
            movieGenres = listOf("Action", "Drama", "Scientfic"),
            releaseDate = "2022-03-01",
            movieOverview = LoremIpsum(100).values.joinToString(),
            moviePosterImageUrl = null,
            movieBackdropImageUrl = null,
            movieVotePoint = "8.2"
        )
    ),
    handleUiAction: (action: ExploreUiAction) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(R.dimen.medium_padding))
    ) {
        MovieSearchBar(
            modifier = Modifier.padding(dimensionResource(R.dimen.large_padding)),
            searchText = "",
            showFilterIcon = true,
            isEnable = false,
            onClickAction = {
                handleUiAction(ExploreUiAction.SearchBarClickAction(data.forYouMovie?.movieId))
            })
        ExploreBannerMovies(
            modifier = Modifier.wrapContentHeight(),
            bannerMovies = data.bannerMovies,
            handleUiAction = handleUiAction
        )
        data.forYouMovie?.let {
            ExploreForYouMovieView(
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.large_padding),
                    end = dimensionResource(R.dimen.large_padding),
                    top = dimensionResource(R.dimen.medium_padding)
                ), movie = data.forYouMovie, handleUiAction = handleUiAction
            )
        }

        MostPopularMovies(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(R.dimen.large_padding),
                    vertical = dimensionResource(R.dimen.medium_padding)
                ), movies = data.popularMovies,
            seeAllClickAction = { seeAllType ->
                handleUiAction(ExploreUiAction.SeeAllClickAction(seeAllType))
            },
            movieClickAction = { movieId,sharedAnimationKey ->
                handleUiAction(ExploreUiAction.MovieClickAction(movieId,sharedAnimationKey))
            },
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope
        )
    }
}





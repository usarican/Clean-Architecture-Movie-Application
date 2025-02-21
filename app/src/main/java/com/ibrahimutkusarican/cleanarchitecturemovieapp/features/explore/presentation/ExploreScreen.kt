package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.model.ExploreGenreModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.domain.model.ExploreInitialDataModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation.MovieCategory
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MySearchBar

@Composable
fun ExploreScreen() {
    val viewModel = hiltViewModel<ExploreViewModel>()
    val uiState by viewModel.exploreUiState.collectAsStateWithLifecycle()
    val data by viewModel.exploreInitialData.collectAsStateWithLifecycle()
    when (uiState) {
        is UiState.Error -> ErrorScreen(exception = (uiState as UiState.Error).exception,
            tryAgainOnClickAction = { viewModel.handleUiAction(ExploreUiAction.ErrorRetryAction) })

        UiState.Loading -> LoadingScreen()
        is UiState.Success -> {
            ExploreSuccessScreen(data, handleUiAction = viewModel::handleUiAction)
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun ExploreSuccessScreen(
    data: ExploreInitialDataModel = ExploreInitialDataModel(
        genreList = listOf(
            ExploreGenreModel(-1, "All", true),
            ExploreGenreModel(-1, "Action"),
            ExploreGenreModel(-1, "Horror"),
            ExploreGenreModel(-1, "Comedy")
        ), forYouMovie = BasicMovieModel(
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
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(R.dimen.medium_padding))
    ) {
        MySearchBar(
            modifier = Modifier.padding(dimensionResource(R.dimen.large_padding)),
            searchText = "",
            showFilterIcon = true,
            isEnable = false,
            readOnly = true,
            onClickAction = {
                handleUiAction(ExploreUiAction.SearchBarClickAction(data.forYouMovie?.movieId))
            })
        ExploreBannerMovies(
            modifier = Modifier.height(dimensionResource(R.dimen.explore_banner_movie_height)),
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

        MostPopularMovies(modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.large_padding),
                vertical = dimensionResource(R.dimen.medium_padding)
            ), movies = data.popularMovies, seeAllClickAction = { movieType ->
            handleUiAction(ExploreUiAction.SeeAllClickAction(movieType))
        }, movieClickAction = { movieId ->
            handleUiAction(ExploreUiAction.MovieClickAction(movieId))
        })
    }
}

@Composable
fun MostPopularMovies(
    modifier: Modifier = Modifier,
    movies: List<BasicMovieModel>,
    seeAllClickAction: (movieType: MovieType) -> Unit = {},
    movieClickAction: (movieId: Int) -> Unit = {}
) {
    MovieCategory(
        modifier = modifier,
        movieType = MovieType.POPULAR,
        title = stringResource(R.string.most_popular),
        movies = movies,
        seeAllClickAction = seeAllClickAction,
        movieClickAction = movieClickAction
    )
}





package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.explore.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.data.local.entity.MovieType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.home.presentation.MovieCategory
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.CategoriesView
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MySearchBar

@Composable
@Preview(showBackground = true)
fun ExploreScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        MySearchBar(
            showFilterIcon = true,
            isEnable = false
        )
        ExploreBannerMovies(bannerMovies = emptyList())
        CategoriesView()
        MostPopularMovies(movies = emptyList())
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





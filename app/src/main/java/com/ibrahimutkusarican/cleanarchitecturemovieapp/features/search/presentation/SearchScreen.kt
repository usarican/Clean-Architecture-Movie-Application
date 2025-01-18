package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MySearchBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MyTopBar

@Composable
@Preview(showBackground = true)
fun SearchScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MyTopBar(
            title = stringResource(R.string.search)
        )
        MySearchBar()
        LastSearchedMovies()
        TopSearchedMovies()
        RecommendedMoviesForYou()
        RecentlyViewedMovies()
        SearchedMoviesList()

    }
}

@Composable
fun RecommendedMoviesForYou() {
    TODO("Not yet implemented")
}

@Composable
fun RecentlyViewedMovies() {
    TODO("Not yet implemented")
}

@Composable
fun TopSearchedMovies() {
    TODO("Not yet implemented")
}



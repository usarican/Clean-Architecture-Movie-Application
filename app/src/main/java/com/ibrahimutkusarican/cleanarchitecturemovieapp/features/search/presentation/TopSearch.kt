package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun TopSearch(
    modifier: Modifier = Modifier,
    topSearchMovieNames: List<String> = listOf("Movie1", "Movie2", "Movie3")
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

    }
}



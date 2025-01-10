package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieExceptions

@Composable
@Preview
fun ErrorScreen(
    modifier : Modifier = Modifier,
    exception: MovieExceptions = MovieExceptions.NoInternetException("Error")){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        ErrorImage(exception = exception)
        ErrorText(exception = exception)

    }
}

@Composable
fun ErrorText(exception : MovieExceptions) {
    Text(text = exception.message ?: stringResource(R.string.error_message))
}

@Composable
fun ErrorImage(exception : MovieExceptions) {
    Image(
        imageVector = Icons.Default.Warning,
        contentDescription = exception.message,
        modifier = Modifier.fillMaxSize(0.3f),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
    )
}

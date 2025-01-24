package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.BuildConfig
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

@Composable
fun MovieImage(
    modifier : Modifier = Modifier,
    imageUrl : String?,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    AsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = "AsyncImage",
        contentScale = contentScale,
    )
}





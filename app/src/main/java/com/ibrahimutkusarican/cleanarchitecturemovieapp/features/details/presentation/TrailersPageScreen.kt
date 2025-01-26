package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailTrailerModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.TrailerModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.mockMovieDetailModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun TrailersPageScreen(
    modifier: Modifier = Modifier,
    movieDetailTrailerModel: MovieDetailTrailerModel = mockMovieDetailModel.movieDetailTrailerModel
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            .padding(bottom = dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding))
    ) {
        items(movieDetailTrailerModel.trailers) { model ->
            TrailerItem(trailerModel = model)
        }
    }


}

@Composable
@Preview(showBackground = true)
private fun TrailerItem(trailerModel: TrailerModel = mockMovieDetailModel.movieDetailTrailerModel.trailers.first()) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_padding)),
            text = trailerModel.name, style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W600
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        YoutubePlayerView(trailerModel.key)
    }
}

@Composable
private fun YoutubePlayerView(videoKey: String) {
    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.small_border))),
        factory = {
            YouTubePlayerView(context).apply {
                lifeCycleOwner.lifecycle.addObserver(this)
                val youTubePlayerListener = object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(videoKey, VIDEO_START_TIME)
                    }
                }
                addYouTubePlayerListener(youTubePlayerListener)
            }
        }
    )
}

private const val VIDEO_START_TIME = 0F
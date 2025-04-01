package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailAboutModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailCastModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.mockMovieDetailModel

@Composable
fun MovieAboutCastComponent(
    movieDetailAboutModel: MovieDetailAboutModel
) {
    Column {
        Text(
            text = stringResource(R.string.cast), style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.small_padding),
                    bottom = dimensionResource(R.dimen.medium_padding)
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.twelve_padding))
        ) {
            items(movieDetailAboutModel.casts) { cast ->
                MovieCastItem(castModel = cast)
            }
        }
    }
}

@Composable
private fun MovieCastItem(
    castModel: MovieDetailCastModel
) {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.cast_card_radius)),
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.six_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CastImage(imageUrl = castModel.profileImage)
            Column(
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.six_padding))
            ) {
                Text(
                    text = castModel.characterName, style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                castModel.originalName?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Light
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun CastImage(
    imageUrl: String?,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    Card(
        modifier = Modifier.size(dimensionResource(R.dimen.cast_image_size)),
        shape = CircleShape
    ) {
        AsyncImage(
            modifier = Modifier,
            placeholder = painterResource(R.drawable.ic_no_image_person),
            model = imageUrl,
            contentDescription = "CastImage",
            contentScale = contentScale,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieAboutCastComponent() {
    MovieAboutCastComponent(
        movieDetailAboutModel = mockMovieDetailModel.movieDetailAboutModel
    )
}

@Preview
@Composable
private fun PreviewMovieCastItem() {
    MovieCastItem(
        castModel = mockMovieDetailModel.movieDetailAboutModel.casts.first()
    )
}
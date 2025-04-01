package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.EmptyScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.EmptyScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.AuthorModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.domain.model.mockMovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.seeall.data.SeeAllType

@Composable
@Preview(showBackground = true)
fun ReviewsPageScreen(
    modifier: Modifier = Modifier,
    movieDetailModel: MovieDetailModel = mockMovieDetailModel,
    handleUiAction: (action: DetailUiAction) -> Unit = {}
) {
    if (movieDetailModel.movieDetailReviewModel.reviews.isEmpty()){
        EmptyScreen(emptyScreenType = EmptyScreenType.REVIEWS)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            .padding(bottom = dimensionResource(R.dimen.medium_padding)),
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.reviews),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W700
                )
            )
            Text(
                modifier = Modifier.clickable {
                    handleUiAction(
                        DetailUiAction.SeeAllClickAction(
                            SeeAllType.MovieReviews(
                                movieDetailModel.movieDetailInfoModel.movieId
                            )
                        )
                    )
                },
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.medium_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        ) {
            items(movieDetailModel.movieDetailReviewModel.reviews) { model ->
                ReviewItem(model = model)
            }
        }
    }

}

@Composable
fun ReviewItem(model: AuthorModel) {
    model.review?.let {
        Row {
            AuthorImage(imageUrl = model.authorProfilePhoto)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimensionResource(R.dimen.medium_padding)),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = model.authorNickName ?: model.authorName
                        ?: stringResource(R.string.anonymous),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W600
                        )
                    )
                    model.rating?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = "StarIcon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                modifier = Modifier.padding(start = dimensionResource(R.dimen.x_small_padding)),
                                text = model.rating.toString(),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.W700
                                )
                            )
                        }
                    }

                }
                Text(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.small_padding)),
                    text = model.review,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.outline
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun AuthorImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    Card(
        modifier = modifier.size(dimensionResource(R.dimen.author_image_size)),
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

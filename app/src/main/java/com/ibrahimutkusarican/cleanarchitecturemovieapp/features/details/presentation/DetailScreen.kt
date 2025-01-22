package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.mockMovieDetail
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.fontDimensionResource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage

@Composable
@Preview(showBackground = true)
fun MovieDetailScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        MovieDetailImage()
        MovieDetailInfo()
        MovieDetailActionButtons()
        MovieDetailPager()
    }

}

@Composable
private fun MovieDetailActionButtons(
    modifier: Modifier = Modifier
) {
    val actionButtons = listOf(
        MovieDetailActionButtonData(
            selectIcon = R.drawable.ic_share,
            selectText = R.string.share,
            type = MovieDetailActionButtonType.SHARE
        ),
        MovieDetailActionButtonData(
            selectIcon = R.drawable.ic_like,
            unSelectIcon = R.drawable.ic_dislike,
            type = MovieDetailActionButtonType.ADD_FAVORITE,
            isSelected = false
        ),
        MovieDetailActionButtonData(
            selectIcon = R.drawable.ic_my_list,
            unSelectIcon = R.drawable.ic_un_my_list,
            type = MovieDetailActionButtonType.ADD_WATCH_LIST
        ),
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.dp_64),
                end = dimensionResource(R.dimen.dp_64),
                bottom = dimensionResource(R.dimen.twelve_padding)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        actionButtons.forEach { button ->
            MovieDetailActionButton(
                movieDetailActionButtonData = button
            )
        }
    }
}

@Composable
private fun MovieDetailActionButton(
    movieDetailActionButtonData: MovieDetailActionButtonData,
    clickAction: (type: MovieDetailActionButtonType) -> Unit = {}
) {
    when (movieDetailActionButtonData.type) {
        MovieDetailActionButtonType.SHARE -> {
            OutlinedButton(
                onClick = { clickAction(movieDetailActionButtonData.type) },
                shape =  RoundedCornerShape(dimensionResource(R.dimen.medium_border)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (!movieDetailActionButtonData.isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background,
                    contentColor = if (!movieDetailActionButtonData.isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground,
                ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(movieDetailActionButtonData.getIcon()),
                        contentDescription = "MovieDetailActionButtonIcon"
                    )
                    Text(
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.small_padding)),
                        text = stringResource(movieDetailActionButtonData.selectText),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        MovieDetailActionButtonType.ADD_FAVORITE, MovieDetailActionButtonType.ADD_WATCH_LIST -> {
            OutlinedButton(
                modifier = Modifier.size(dimensionResource(R.dimen.circle_icon_radius_size)),
                onClick = { clickAction(movieDetailActionButtonData.type) },
                shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (!movieDetailActionButtonData.isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background,
                    contentColor = if (!movieDetailActionButtonData.isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground,
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(movieDetailActionButtonData.getIcon()),
                        contentDescription = "MovieDetailActionButtonIcon"
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieDetailPager(
    modifier: Modifier = Modifier
) {
    val pages = listOf(
        MovieDetailPage(R.string.about, 0),
        MovieDetailPage(R.string.trailers, 1),
        MovieDetailPage(R.string.reviews, 2),
        MovieDetailPage(R.string.recommended, 3)
    )
    val pagerState = rememberPagerState { pages.size }
    Column {
        TabLayout(pages = pages, currentPageIndex = pagerState.currentPage)
        HorizontalPager(
            modifier = modifier
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.large_padding)), state = pagerState
        ) { page ->
            when (page) {
                0 -> AboutPageScreen()
                1 -> TrailersPageScreen()
                2 -> ReviewsPageScreen()
                3 -> RecommendedPageScreen()
            }
        }
    }
}

@Composable
private fun TabLayout(
    modifier: Modifier = Modifier, pages: List<MovieDetailPage>, currentPageIndex: Int
) {

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = dimensionResource(R.dimen.medium_padding))
        ) {
            val itemWidth = maxWidth / 3
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(pages) { page ->
                    TabLayoutItem(
                        modifier = Modifier.width(itemWidth),
                        page = page,
                        isSelected = currentPageIndex == page.index
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = dimensionResource(R.dimen.one_dp),
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun TabLayoutItem(
    modifier: Modifier = Modifier,
    page: MovieDetailPage = MovieDetailPage(R.string.about, 0),
    isSelected: Boolean = true
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.medium_padding)),
            text = stringResource(page.title),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
            ),
            textAlign = TextAlign.Center
        )

        if (isSelected) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape),
                thickness = dimensionResource(R.dimen.three_dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun MovieDetailInfo(
    modifier: Modifier = Modifier, movieDetailModel: MovieDetailModel = mockMovieDetail
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.medium_padding),
                horizontal = dimensionResource(R.dimen.large_padding)
            ), verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movieDetailModel.title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movieDetailModel.tagline,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconWithText(
                iconId = R.drawable.ic_calender_2, text = movieDetailModel.releaseYear
            )
            IconWithText(
                iconId = R.drawable.ic_time, text = movieDetailModel.runtime
            )
            IconWithText(
                iconId = R.drawable.ic_genre_2, text = movieDetailModel.genre
            )
        }

    }
}

@Composable
private fun IconWithText(
    @DrawableRes iconId: Int, text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = "Icon",
            tint = MaterialTheme.colorScheme.outline
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(R.dimen.x_small_padding)),
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = fontDimensionResource(
                    R.dimen.movie_detail_info_row_text_size
                )
            )
        )
    }
}

@Composable
private fun MovieDetailImage(
    modifier: Modifier = Modifier,
    movieDetailModel: MovieDetailModel = mockMovieDetail,
    backClickAction: () -> Unit = {}
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (backdropImage, posterImage, backIcon) = createRefs()
        val topMargin = dimensionResource(R.dimen.x_x_large_padding)

        IconButton(onClick = backClickAction, modifier = Modifier
            .constrainAs(backIcon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            .zIndex(2F)) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }

        MovieImage(modifier = Modifier
            .fillMaxWidth()
            .height((screenHeight / 4).dp)
            .constrainAs(backdropImage) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .blur(dimensionResource(R.dimen.blur)),
            imageUrl = movieDetailModel.backgroundImageUrl)
        Card(
            modifier = Modifier
                .height(dimensionResource(R.dimen.movie_detail_poster_height))
                .width(dimensionResource(R.dimen.movie_detail_poster_width))
                .constrainAs(posterImage) {
                    top.linkTo(parent.top, margin = topMargin)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.card_elevation)),
            shape = RoundedCornerShape(dimensionResource(R.dimen.medium_border))
        ) {
            MovieImage(
                modifier = Modifier.fillMaxSize(), imageUrl = movieDetailModel.posterImageUrl
            )
        }
    }
}

data class MovieDetailPage(
    @StringRes val title: Int, val index: Int
)

data class MovieDetailActionButtonData(
    @DrawableRes val selectIcon: Int,
    @DrawableRes val unSelectIcon: Int? = null,
    @StringRes val selectText: Int = 0,
    val type: MovieDetailActionButtonType,
    val isSelected: Boolean = true
) {
    fun getIcon() = (if (!isSelected) selectIcon else unSelectIcon) ?: selectIcon
}

enum class MovieDetailActionButtonType {
    SHARE, ADD_FAVORITE, ADD_WATCH_LIST
}

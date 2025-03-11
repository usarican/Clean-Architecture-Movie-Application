package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.presentation

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBarModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.SnackBarType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailInfoModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.MovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.details.domain.model.mockMovieDetailModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListPage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.BaseUiStateComposable
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.fontDimensionResource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier, viewModel: MovieDetailViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val movieDetailModel by viewModel.movieDetailModel.collectAsStateWithLifecycle()
    var snackBarModel by remember { mutableStateOf<MySnackBarModel?>(null) }
    var snackBarMyListPageIndex by remember { mutableIntStateOf(MyListPage.FAVORITE.index) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.showSnackBar.collectLatest { model ->
            snackBarModel = model.first
            snackBarMyListPageIndex = model.second.index
            coroutineScope.launch {
                delay(3000)
                snackBarModel = null
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.shareImageData.collectLatest { uri ->
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/jpeg"
            }
            startActivity(context,Intent.createChooser(shareIntent, null),null)
        }
    }
    

    BaseUiStateComposable(uiState = uiState, tryAgainOnClickAction = {
        viewModel.handleUiAction(DetailUiAction.ErrorRetryAction)
    }, backButtonClickAction = {
        viewModel.handleUiAction(DetailUiAction.OnBackPressClickAction)
    }) {
        movieDetailModel?.let { model ->
            Box(modifier = modifier.fillMaxSize()) {
                MovieDetailSuccessScreen(
                    modifier = modifier,
                    movieDetailModel = model,
                    backClickAction = { viewModel.handleUiAction(DetailUiAction.OnBackPressClickAction) },
                    action = viewModel::handleUiAction
                )
                snackBarModel?.let {
                    MySnackBar(snackBarModel = it,
                        visible = true,
                        modifier = Modifier.align(Alignment.BottomCenter),
                        actionLabel = if (it.type == SnackBarType.SUCCESS) stringResource(R.string.see_all) else Constants.EMPTY_STRING,
                        action = {
                            if (it.type == SnackBarType.SUCCESS) {
                                viewModel.handleUiAction(
                                    DetailUiAction.GoToMyListPage(
                                        snackBarMyListPageIndex
                                    )
                                )
                            }
                        })
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MovieDetailSuccessScreen(
    modifier: Modifier = Modifier,
    movieDetailModel: MovieDetailModel = mockMovieDetailModel,
    backClickAction: () -> Unit = {},
    action: (action: DetailUiAction) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        MovieDetailImage(
            movieDetailInfoModel = movieDetailModel.movieDetailInfoModel,
            backClickAction = backClickAction
        )
        MovieDetailInfo(
            movieDetailInfoModel = movieDetailModel.movieDetailInfoModel
        )
        MovieDetailActionButtons(
            action = action, movieDetailInfoModel = movieDetailModel.movieDetailInfoModel
        )
        MovieDetailPager(movieDetailModel = movieDetailModel, handleUiAction = action)
    }
}

@Composable
private fun MovieDetailActionButtons(
    modifier: Modifier = Modifier,
    movieDetailInfoModel: MovieDetailInfoModel = mockMovieDetailModel.movieDetailInfoModel,
    action: (action: DetailUiAction) -> Unit
) {
    val actionButtons = listOf(
        MovieDetailActionButtonData(
            selectIcon = R.drawable.ic_play,
            selectText = R.string.play,
            type = MovieDetailActionButtonType.PLAY
        ),
        MovieDetailActionButtonData(
            selectIcon = R.drawable.ic_share,
            selectText = R.string.share,
            type = MovieDetailActionButtonType.SHARE,
            isSelected = false
        ),
        MovieDetailActionButtonData(
            selectIcon = R.drawable.ic_favorite,
            unSelectIcon = R.drawable.ic_not_favorite,
            type = MovieDetailActionButtonType.ADD_FAVORITE,
            isSelected = movieDetailInfoModel.isFavorite
        ),
        MovieDetailActionButtonData(
            selectIcon = R.drawable.ic_my_list,
            unSelectIcon = R.drawable.ic_un_my_list,
            type = MovieDetailActionButtonType.ADD_WATCH_LIST,
            isSelected = movieDetailInfoModel.isAddedToWatchList
        ),
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.x_x_large_padding))
            .padding(
                top = dimensionResource(R.dimen.small_padding),
                bottom = dimensionResource(R.dimen.twelve_padding)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        actionButtons.forEach { button ->
            MovieDetailActionButton(movieDetailActionButtonData = button,
                clickAction = { action(DetailUiAction.DetailButtonClickAction(it)) })
        }
    }
}

@Composable
private fun MovieDetailActionButton(
    movieDetailActionButtonData: MovieDetailActionButtonData,
    clickAction: (data: MovieDetailActionButtonData) -> Unit = {}
) {
    when (movieDetailActionButtonData.type) {
        MovieDetailActionButtonType.PLAY -> {
            OutlinedButton(
                modifier = Modifier.height(dimensionResource(R.dimen.detail_icon_size)),
                onClick = { clickAction(movieDetailActionButtonData) },
                shape = RoundedCornerShape(dimensionResource(R.dimen.large_border)),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (movieDetailActionButtonData.isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background,
                    contentColor = if (movieDetailActionButtonData.isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground,
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(R.dimen.twenty_dp),
                    ), verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(dimensionResource(R.dimen.small_icon_size)),
                        painter = painterResource(movieDetailActionButtonData.getIcon()),
                        contentDescription = "MovieDetailActionButtonIcon"
                    )
                    Text(
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.small_padding)),
                        text = stringResource(movieDetailActionButtonData.selectText),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W600
                        )
                    )
                }
            }
        }

        MovieDetailActionButtonType.SHARE, MovieDetailActionButtonType.ADD_FAVORITE, MovieDetailActionButtonType.ADD_WATCH_LIST -> {
            OutlinedButton(
                modifier = Modifier.size(dimensionResource(R.dimen.detail_icon_size)),
                onClick = { clickAction(movieDetailActionButtonData) },
                shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = if (movieDetailActionButtonData.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
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
    modifier: Modifier = Modifier,
    movieDetailModel: MovieDetailModel,
    handleUiAction: (action: DetailUiAction) -> Unit
) {
    val pages = listOf(
        MovieDetailPage(R.string.about, 0),
        MovieDetailPage(R.string.trailers, 1),
        MovieDetailPage(R.string.reviews, 2),
        MovieDetailPage(R.string.similar, 3)
    )
    val pagerState = rememberPagerState { pages.size }
    val coroutineScope = rememberCoroutineScope()
    Column {
        TabLayout(pages = pages, currentPageIndex = pagerState.currentPage, clickAction = { index ->
            coroutineScope.launch { pagerState.animateScrollToPage(index) }
        })
        HorizontalPager(
            modifier = modifier
                .fillMaxSize()
                .padding(top = dimensionResource(R.dimen.medium_padding)), state = pagerState
        ) { page ->
            when (page) {
                0 -> AboutPageScreen(movieDetailAboutModel = movieDetailModel.movieDetailAboutModel)
                1 -> TrailersPageScreen(movieDetailTrailerModel = movieDetailModel.movieDetailTrailerModel)
                2 -> ReviewsPageScreen(
                    movieDetailModel = movieDetailModel, handleUiAction = handleUiAction
                )

                3 -> RecommendedPageScreen(
                    movieDetailModel = movieDetailModel, handleUiAction = handleUiAction
                )
            }
        }
    }
}

@Composable
private fun TabLayout(
    modifier: Modifier = Modifier,
    pages: List<MovieDetailPage>,
    currentPageIndex: Int,
    clickAction: (index: Int) -> Unit = {}
) {
    val rowState = rememberLazyListState()
    LaunchedEffect(currentPageIndex) {
        rowState.animateScrollToItem(currentPageIndex)
    }
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
                modifier = Modifier.fillMaxWidth(), state = rowState
            ) {
                items(pages) { page ->
                    TabLayoutItem(
                        modifier = Modifier.width(itemWidth),
                        page = page,
                        isSelected = currentPageIndex == page.index,
                        clickAction = clickAction
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
    isSelected: Boolean = true,
    clickAction: (index: Int) -> Unit = {}
) {
    Column(
        modifier = modifier.clickable { clickAction(page.index) },
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.small_padding))
                .padding(bottom = dimensionResource(R.dimen.small_padding)),
            text = stringResource(page.title),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isSelected) FontWeight.W600 else FontWeight.W400,
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
    modifier: Modifier = Modifier, movieDetailInfoModel: MovieDetailInfoModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.large_padding)
            )
            .padding(top = dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movieDetailInfoModel.title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        if (movieDetailInfoModel.tagline.isNotEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movieDetailInfoModel.tagline,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.large_padding)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconWithText(
                iconId = R.drawable.ic_calender_2, text = movieDetailInfoModel.releaseYear
            )
            IconWithText(
                iconId = R.drawable.ic_time, text = movieDetailInfoModel.runtime
            )
            IconWithText(
                iconId = R.drawable.ic_genre_2, text = movieDetailInfoModel.genre
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
    movieDetailInfoModel: MovieDetailInfoModel,
    backClickAction: () -> Unit = {}
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (backdropImage, posterImage, backIcon) = createRefs()
        val topMargin = dimensionResource(R.dimen.x_x_large_padding)

        IconButton(onClick = backClickAction,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .constrainAs(backIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(start = dimensionResource(R.dimen.small_padding))
                .zIndex(2F)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }

        MovieImage(modifier = Modifier
            .fillMaxWidth()
            .height((screenHeight / 2.5).dp)
            .constrainAs(backdropImage) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .blur(dimensionResource(R.dimen.blur)),
            imageUrl = movieDetailInfoModel.backgroundImageUrl,
            contentScale = ContentScale.Crop)
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
            shape = RoundedCornerShape(dimensionResource(R.dimen.medium_border)),
            border = BorderStroke(
                dimensionResource(R.dimen.one_dp), MaterialTheme.colorScheme.onBackground
            )
        ) {
            MovieImage(
                modifier = Modifier.fillMaxSize(), imageUrl = movieDetailInfoModel.posterImageUrl
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
    fun getIcon() = (if (isSelected) selectIcon else unSelectIcon) ?: selectIcon
}

enum class MovieDetailActionButtonType {
    PLAY, SHARE, ADD_FAVORITE, ADD_WATCH_LIST
}

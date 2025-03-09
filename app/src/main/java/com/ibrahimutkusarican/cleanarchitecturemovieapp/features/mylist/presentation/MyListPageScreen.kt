package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.paging.compose.LazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.EmptyScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListUpdatePage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.BasePagingComposable
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.fontDimensionResource
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.widgets.MovieImage
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun MyListPageScreen(
    movies: LazyPagingItems<MyListMovieModel>,
    handleUiAction: (action: MyListUiAction) -> Unit,
    pageIndex: Int,
    emptyScreenType: EmptyScreenType
) {
    BasePagingComposable(
        pagingItems = movies,
        emptyScreenType = emptyScreenType,
        emptyScreenGoToExploreAction = {
            handleUiAction(MyListUiAction.GoToExploreAction)
        }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(R.dimen.medium_padding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        ) {
            items(count = movies.itemCount, key = { index -> index }) { index ->
                movies[index]?.let { movie ->
                    MyListMovieItem(myListMovie = movie, movieClickAction = {
                        handleUiAction.invoke(MyListUiAction.MovieClickAction(movie.movieId))
                    }, onDelete = {
                        handleUiAction(
                            MyListUiAction.MovieDeleteAction(
                                MyListViewModel.DeleteMovieData(
                                    movie = movie,
                                    page = MyListUpdatePage.findPageByIndex(pageIndex)
                                )
                            )
                        )
                    }, onInstantDelete = {
                        handleUiAction(
                            MyListUiAction.InstantMovieDeleteAction(
                                MyListViewModel.DeleteMovieData(
                                    movie = movie,
                                    page = MyListUpdatePage.findPageByIndex(pageIndex)
                                )
                            )
                        )
                    })
                }
            }

        }
    }
}

@Composable
fun MyListMovieItem(
    modifier: Modifier = Modifier,
    myListMovie: MyListMovieModel,
    movieClickAction: (movieId: Int) -> Unit = {},
    onDelete: () -> Unit = {},
    onInstantDelete: () -> Unit = {}
) {

    val offsetX = remember { Animatable(0f) }
    var itemWidth by remember { mutableIntStateOf(1) }
    var textViewWidth by remember { mutableFloatStateOf(1F) }
    val cardShape = RoundedCornerShape(dimensionResource(R.dimen.medium_border))
    val scope = rememberCoroutineScope()

    val deleteTextPosition = remember {
        Animatable(
            0F
        )
    }

    Box(modifier = modifier
        .fillMaxWidth()
        .onGloballyPositioned { coords ->
            itemWidth = coords.size.width
        }) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(cardShape)
                .background(MaterialTheme.colorScheme.error)
        ) {
            Box(
                modifier = Modifier
                    .graphicsLayer(translationX = deleteTextPosition.value)
                    .onGloballyPositioned { coords ->
                        textViewWidth = coords.size.width.toFloat()
                    }
                    .fillMaxHeight()
                    .clip(cardShape)
                    .background(MaterialTheme.colorScheme.error)
                    .clickable {
                        scope.launch {
                            onDelete()
                        }
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(R.dimen.small_padding))
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.delete),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.W700, color = MaterialTheme.colorScheme.onError
                    )
                )
            }
        }

        Card(shape = cardShape, border = BorderStroke(
            dimensionResource(R.dimen.one_dp), MaterialTheme.colorScheme.outlineVariant
        ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ), modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .fillMaxWidth()
                .draggable(orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        val newOffset = offsetX.value + delta
                        scope.launch {
                            launch {
                                offsetX.snapTo(newOffset.coerceIn(-itemWidth.toFloat(), 0f))
                            }
                            val percentSwiped = -offsetX.value / itemWidth

                            if (percentSwiped <= 0.7f) {
                                deleteTextPosition.animateTo(itemWidth - textViewWidth)

                            } else {
                                deleteTextPosition.animateTo((itemWidth + offsetX.value))
                            }
                        }
                    },
                    onDragStopped = { velocity ->
                        scope.launch {
                            if (velocity < -2000) {
                                offsetX.animateTo(
                                    targetValue = -itemWidth.toFloat(), animationSpec = spring()
                                )
                                onInstantDelete()
                            } else {
                                val offsetVal = offsetX.value
                                val seventyFivePercentWidth = 3f * (-itemWidth / 4f)

                                when {
                                    offsetVal <= seventyFivePercentWidth -> {
                                        offsetX.animateTo(
                                            targetValue = -itemWidth.toFloat(),
                                            animationSpec = spring()
                                        )
                                        onInstantDelete()
                                    }

                                    offsetVal <= -(textViewWidth / 2) -> {
                                        offsetX.animateTo(
                                            targetValue = -textViewWidth, animationSpec = spring()
                                        )
                                    }

                                    else -> {
                                        offsetX.animateTo(0f, animationSpec = spring())
                                    }
                                }
                            }
                        }
                    })
                .clickable {
                    movieClickAction(myListMovie.movieId)
                }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.see_all_category_movie_width))
            ) {
                Card(
                    shape = RoundedCornerShape(dimensionResource(R.dimen.medium_border))
                ) {
                    MovieImage(
                        imageUrl = myListMovie.posterPath,
                        modifier = Modifier
                            .width(dimensionResource(R.dimen.see_all_category_movie_width))
                            .height(dimensionResource(R.dimen.see_all_category_movie_width))
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = dimensionResource(R.dimen.medium_padding)),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = myListMovie.title, style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = fontDimensionResource(R.dimen.movie_category_item_title_size),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                    Text(
                        text = myListMovie.overview,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = fontDimensionResource(R.dimen.see_all_movie_item_content_text_size),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (myListMovie.genres.isNotEmpty()) {
                            Text(
                                text = myListMovie.genres.first(),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            )
                            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium_padding)))
                        }
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star Rating",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(4.dp)) // Space between star and text
                        Text(
                            text = myListMovie.movieRating,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                }
            }
        }
    }
}




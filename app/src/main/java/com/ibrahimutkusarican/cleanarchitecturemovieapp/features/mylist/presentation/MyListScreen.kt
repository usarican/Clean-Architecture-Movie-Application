package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.EmptyScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBar
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.MySnackBarModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.SnackBarType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.model.MyListPage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MyListScreen(
    pageIndex : Int
) {
    Box(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(vertical = dimensionResource(R.dimen.medium_padding))
            .fillMaxSize(),
    ) {
        val viewModel = hiltViewModel<MyListViewModel>()
        val state = rememberPagerState { MyListPage.entries.size }
        val coroutineScope = rememberCoroutineScope()
        val favoriteMovies = viewModel.favoriteMovies.collectAsLazyPagingItems()
        val watchListMovies = viewModel.watchListMovies.collectAsLazyPagingItems()
        var snackBarModel by remember { mutableStateOf<MySnackBarModel?>(null) }

        LaunchedEffect(Unit) {
            viewModel.showSnackBar.collectLatest { model ->
                snackBarModel = model
                coroutineScope.launch {
                    delay(3000)
                    snackBarModel = null
                }
            }
        }

        LaunchedEffect(pageIndex) {
            state.animateScrollToPage(pageIndex)
        }

        Column(Modifier.fillMaxSize()) {
            MyListTabLayout(
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.medium_padding)),
                currentPage = state.currentPage,
                onClickAction = { index ->
                    coroutineScope.launch { state.animateScrollToPage(index) }
                }
            )
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = dimensionResource(R.dimen.medium_padding)),
                state = state,
            ) { page ->
                when (page) {
                    0 -> MyListPageScreen(
                        movies = favoriteMovies,
                        handleUiAction = viewModel::handleUiAction,
                        pageIndex = page,
                        emptyScreenType = EmptyScreenType.FAVORITE
                    )

                    1 -> MyListPageScreen(
                        movies = watchListMovies,
                        handleUiAction = viewModel::handleUiAction,
                        pageIndex = page,
                        emptyScreenType = EmptyScreenType.WATCH_LIST
                    )
                }
            }
        }
        snackBarModel?.let {
            when (it.type) {
                SnackBarType.SUCCESS -> {
                    MySnackBar(
                        snackBarModel = it,
                        visible = true,
                        modifier = Modifier.align(Alignment.BottomCenter),
                        actionLabel = stringResource(R.string.undo),
                        action = {
                            viewModel.handleUiAction(MyListUiAction.UndoAction)
                        }
                    )
                }

                SnackBarType.INFO -> TODO()
                SnackBarType.WARNING -> {
                    MySnackBar(
                        snackBarModel = it,
                        visible = true,
                        modifier = Modifier.align(Alignment.BottomCenter),
                        actionLabel = stringResource(R.string.delete),
                        action = {
                            viewModel.handleUiAction(MyListUiAction.SnackBarDeleteAction)
                        }
                    )
                }

                SnackBarType.ERROR -> TODO()
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun MyListTabLayout(
    modifier: Modifier = Modifier,
    currentPage: Int = 0,
    onClickAction: (index: Int) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Card(
            border = BorderStroke(
                dimensionResource(R.dimen.one_dp),
                MaterialTheme.colorScheme.outline
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.small_border))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.small_padding)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
            ) {
                MyListPage.entries.forEach { page ->
                    MyListTabLayoutItem(
                        modifier = Modifier.weight(1f),
                        clickAction = onClickAction,
                        page = page,
                        isSelected = currentPage == page.index,
                    )
                }
            }
        }
    }
}

@Composable
private fun MyListTabLayoutItem(
    modifier: Modifier = Modifier,
    clickAction: (index: Int) -> Unit,
    page: MyListPage,
    isSelected: Boolean
) {
    Card(
        modifier = modifier,
        onClick = {
            clickAction(page.index)
        },
        shape = RoundedCornerShape(dimensionResource(R.dimen.s_medium_border)),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceVariant,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(R.dimen.small_padding),
                    horizontal = dimensionResource(R.dimen.medium_padding)
                ),
            textAlign = TextAlign.Center,
            text = stringResource(page.title), style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = if (isSelected) FontWeight.W700 else FontWeight.W400,
            )
        )
    }
}
package com.iusarican.ui.base

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.iusarican.ui.screen.EmptyScreen
import com.iusarican.ui.screen.EmptyScreenType
import com.iusarican.ui.screen.ErrorScreen
import com.iusarican.ui.screen.LoadingScreen
import com.iusarican.common.exception.toMovieException

@Composable
fun <T : Any> BasePagingComposable(
    pagingItems: LazyPagingItems<T>,
    emptyScreenType: EmptyScreenType,
    emptyScreenGoToExploreAction: () -> Unit = {},
    notLoadingScreen: @Composable (LazyPagingItems<T>) -> Unit,
) {
    when (pagingItems.loadState.refresh) {
        is LoadState.Error -> {
            ErrorScreen(exception = (pagingItems.loadState.refresh as LoadState.Error).error.toMovieException())
        }

        LoadState.Loading -> LoadingScreen()
        is LoadState.NotLoading -> {
            if (pagingItems.itemCount == 0) {
                EmptyScreen(
                    emptyScreenType = emptyScreenType,
                    seeExploreAction = emptyScreenGoToExploreAction
                )
            }
            notLoadingScreen(pagingItems)
        }
    }
}
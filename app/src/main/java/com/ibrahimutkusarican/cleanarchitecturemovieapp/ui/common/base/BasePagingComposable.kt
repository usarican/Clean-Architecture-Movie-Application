package com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.base

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.EmptyScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.EmptyScreenType
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.LoadingScreen
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
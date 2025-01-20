package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.LoadingScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions.convertMovieException

@Composable
fun <T : Any> BasePagingComposable(
    pagingItems: LazyPagingItems<T>,
    notLoadingScreen: @Composable (LazyPagingItems<T>) -> Unit,
) {
    when (pagingItems.loadState.refresh) {
        is LoadState.Error -> {
            ErrorScreen(exception = (pagingItems.loadState.refresh as LoadState.Error).error.convertMovieException())
        }

        LoadState.Loading -> LoadingScreen()
        is LoadState.NotLoading -> notLoadingScreen(pagingItems)
    }
}
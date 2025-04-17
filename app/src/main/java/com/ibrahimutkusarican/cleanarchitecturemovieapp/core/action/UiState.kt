package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action

import com.iusarican.common.exception.MovieException

sealed class UiState<out T> {

    class Success<T>(val data: T) : UiState<T>()

    class Error(val exception: MovieException) : UiState<Nothing>()

    data object Loading : UiState<Nothing>()
}
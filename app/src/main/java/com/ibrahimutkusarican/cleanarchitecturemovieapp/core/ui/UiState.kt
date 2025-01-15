package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieException

sealed class UiState<out T> {

    class Success<T>(val data: T) : UiState<T>()

    class Error(val exception: MovieException) : UiState<Nothing>()

    data object Loading : UiState<Nothing>()
}
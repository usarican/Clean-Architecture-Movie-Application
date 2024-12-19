package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.MovieExceptions

sealed class UiState<out T> {

    class Success<T>(val data: T) : UiState<T>()

    class Error(val exception: MovieExceptions) : UiState<Nothing>()

    data object Loading : UiState<Nothing>()
}
package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

sealed class UiState<out T> {

    class Success<T>(val data: T) : UiState<T>()

    class Error(val exception: Throwable) : UiState<Nothing>()

    data object Loading : UiState<Nothing>()
}
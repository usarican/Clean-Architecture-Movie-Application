package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extensions

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ApiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T, R> ApiState<T>.map(transform: (T) -> R): ApiState<R> {
    return when (this) {
        is ApiState.Success -> ApiState.Success(transform(data))
        is ApiState.Error -> ApiState.Error(exception)
    }
}

fun <T> Flow<UiState<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<UiState<T>> =
    transform { value ->
        if (value is UiState.Success) {
            action(value.data)
        }
        return@transform emit(value)
    }

fun <T> Flow<UiState<T>>.doOnError(action: suspend (Throwable) -> Unit): Flow<UiState<T>> =
    transform { value ->
        if (value is UiState.Error) {
            action(value.exception)
        }
        return@transform emit(value)
    }

fun <T> Flow<UiState<T>>.doOnLoading(action: suspend () -> Unit): Flow<UiState<T>> =
    transform { value ->
        if (value is UiState.Loading) {
            action()
        }
        return@transform emit(value)
    }


fun <T> ApiState<T>.getSuccessOrThrow(): T {
    return when (this) {
        is ApiState.Success -> data
        is ApiState.Error -> throw exception
    }
}
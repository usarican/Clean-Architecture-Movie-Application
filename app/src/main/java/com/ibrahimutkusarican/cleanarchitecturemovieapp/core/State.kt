package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

sealed class State<out T> {

    class Success<T>(val data: T) : State<T>()

    class Error(val exception: Throwable) : State<Nothing>()

    data object Loading : State<Nothing>()
}
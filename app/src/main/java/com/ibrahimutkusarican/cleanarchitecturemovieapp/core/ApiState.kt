package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

sealed class ApiState<out T> {
    class Success<out T>(val data: T) : ApiState<T>()
    class Error(val throwable: Throwable) : ApiState<Nothing>()
}
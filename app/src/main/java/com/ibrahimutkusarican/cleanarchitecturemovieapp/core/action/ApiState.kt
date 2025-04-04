package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.exception.MovieException

sealed class ApiState<out T> {
    class Success<out T>(val data: T) : ApiState<T>()
    class Error(val exception: MovieException) : ApiState<Nothing>()
}
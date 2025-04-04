package com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension

import android.database.sqlite.SQLiteException
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.exception.MovieException
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException

fun Throwable.convertMovieException(): MovieException {
    return when (this) {
        is IOException -> MovieException.NoInternetException("No Internet Connection")
        is HttpException -> {
            when (this.code()) {
                401 -> MovieException.UnauthorizedException("Unauthorized Access")
                404 -> MovieException.NotFoundException("Resource Not Found")
                500 -> MovieException.InternalServerErrorException("Internal Server Error")
                else -> MovieException.GeneralHttpException(
                    code = this.code(), message = "HTTP Error: ${this.code()} ${this.message}"
                )

            }
        }

        is coil3.network.HttpException -> MovieException.CoilHttpException(this.message)
        is SQLiteException -> MovieException.GeneralException("Database Error: ${this.message}")
        is TimeoutCancellationException -> MovieException.GeneralException("Request Timeout")
        else -> MovieException.GeneralException("Unknown Error: ${this.localizedMessage}")
    }
}
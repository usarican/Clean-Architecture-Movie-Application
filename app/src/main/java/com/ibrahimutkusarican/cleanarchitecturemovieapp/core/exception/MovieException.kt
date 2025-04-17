package com.ibrahimutkusarican.cleanarchitecturemovieapp.core.exception

import android.database.sqlite.SQLiteException
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException

sealed class MovieException(message: String?) : Throwable(message) {
    class NoInternetException(message: String?) : MovieException(message)
    class CoilHttpException(message: String?) : MovieException(message)
    class UnauthorizedException(message: String?) : MovieException(message)
    class NotFoundException(message: String?) : MovieException(message)
    class InternalServerErrorException(message: String?) : MovieException(message)
    class GeneralHttpException(val code: Int, message: String?) : MovieException(message)
    class GeneralException(message: String?) : MovieException(message)
}

fun Throwable.toMovieException(): MovieException {
    return when (this) {
        is IOException -> MovieException.NoInternetException("No Internet Connection")
        is HttpException -> {
            when (code()) {
                UNAUTHORIZED_ERROR_CODE -> MovieException.UnauthorizedException("Unauthorized Access")
                NOT_FOUND_ERROR_CODE -> MovieException.NotFoundException("Resource Not Found")
                INTERNAL_SERVER_ERROR_CODE -> MovieException.InternalServerErrorException("Internal Server Error")
                else -> MovieException.GeneralHttpException(
                    code = code(),
                    message = "HTTP Error: ${code()} $message"
                )

            }
        }

        is coil3.network.HttpException -> MovieException.CoilHttpException(message)
        is SQLiteException -> MovieException.GeneralException("Database Error: $message")
        is TimeoutCancellationException -> MovieException.GeneralException("Request Timeout")
        else -> MovieException.GeneralException("Unknown Error: $localizedMessage")
    }
}


private const val UNAUTHORIZED_ERROR_CODE = 401
private const val NOT_FOUND_ERROR_CODE = 404
private const val INTERNAL_SERVER_ERROR_CODE = 500

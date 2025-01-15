package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

import android.database.sqlite.SQLiteException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    fun <T : Any> apiCall(call: suspend () -> T): Flow<ApiState<T>> = flow {
        emit(ApiState.Success(data = call.invoke()) as ApiState<T>)
    }.catch { exp ->
        exp.printStackTrace()
        val error = when (exp) {
            is IOException -> MovieException.NoInternetException("No Internet Connection")
            is HttpException -> {
                when (exp.code()) {
                    401 -> MovieException.UnauthorizedException("Unauthorized Access")
                    404 -> MovieException.NotFoundException("Resource Not Found")
                    500 -> MovieException.InternalServerErrorException("Internal Server Error")
                    else -> MovieException.GeneralHttpException(
                        code = exp.code(), message = "HTTP Error: ${exp.code()} ${exp.message}"
                    )

                }
            }
            is coil3.network.HttpException -> MovieException.CoilHttpException(exp.message)
            is SQLiteException -> MovieException.GeneralException("Database Error: ${exp.message}")
            is TimeoutCancellationException -> MovieException.GeneralException("Request Timeout")
            else -> MovieException.GeneralException("Unknown Error: ${exp.localizedMessage}")
        }
        emit(ApiState.Error(error))
    }.flowOn(Dispatchers.IO)
}
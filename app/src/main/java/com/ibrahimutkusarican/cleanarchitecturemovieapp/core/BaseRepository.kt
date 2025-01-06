package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {
    fun <T : Any> apiCall(call: suspend () -> T): Flow<ApiState<T>> =
        flow {
            emit(ApiState.Success(data = call.invoke()) as ApiState<T>)
        }.catch { error ->
            error.printStackTrace()
            emit(ApiState.Error(error))
        }.flowOn(Dispatchers.IO)
}
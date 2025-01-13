package com.ibrahimutkusarican.cleanarchitecturemovieapp.core

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase {
    fun <T : Any> execute(call: suspend () -> T): Flow<UiState<T>> = flow {
        emit(UiState.Loading)
        val data = call.invoke()
        emit(UiState.Success(data = data))
    }.catch { exp ->
        exp.printStackTrace()
        when (exp) {
            is MovieException -> emit(UiState.Error(exp))
            else -> emit(UiState.Error(MovieException.GeneralException(exp.message)))
        }
    }.flowOn(Dispatchers.IO)
}
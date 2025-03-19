package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import kotlinx.coroutines.flow.Flow

interface DeleteMyListMovieUseCase : DeleteCommand {
    fun deleteMyListMovie() : Flow<UiState<Any>>
    fun undoDeleteMyListMovie() : Flow<UiState<Any>>

    override fun delete(): Flow<UiState<Any>> {
        return deleteMyListMovie()
    }

    override fun undo(): Flow<UiState<Any>> {
        return undoDeleteMyListMovie()
    }
}
package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import kotlinx.coroutines.flow.Flow

interface DeleteCommand {
    fun delete() : Flow<UiState<Any>>
    fun undo() : Flow<UiState<Any>>
}
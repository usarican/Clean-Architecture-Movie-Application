package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.mylist.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.ui.UiState
import kotlinx.coroutines.flow.Flow

interface DeleteCommand {
    fun delete() : Flow<UiState<Any>>
    fun undo() : Flow<UiState<Any>>
}
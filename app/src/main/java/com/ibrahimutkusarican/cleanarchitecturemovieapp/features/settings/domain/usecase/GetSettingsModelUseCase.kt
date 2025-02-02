package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.features.settings.domain.model.SettingsModel
import kotlinx.coroutines.flow.Flow

interface GetSettingsModelUseCase {
    fun getSettingsModel(): Flow<SettingsModel>
}
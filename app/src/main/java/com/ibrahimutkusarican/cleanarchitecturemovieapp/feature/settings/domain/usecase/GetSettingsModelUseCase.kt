package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.usecase

import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.settings.domain.model.SettingsModel
import kotlinx.coroutines.flow.Flow

interface GetSettingsModelUseCase {
    fun getSettingsModel(): Flow<SettingsModel>
}
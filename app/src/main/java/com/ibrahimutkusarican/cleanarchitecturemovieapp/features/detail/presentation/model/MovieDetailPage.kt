package com.ibrahimutkusarican.cleanarchitecturemovieapp.features.detail.presentation.model

import androidx.annotation.StringRes

data class MovieDetailPage(
    @StringRes val title: Int,
    val index: Int
)
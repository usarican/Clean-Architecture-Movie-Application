package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.detail.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MovieDetailActionButtonData(
    @DrawableRes val selectIcon: Int,
    @DrawableRes val unSelectIcon: Int? = null,
    @StringRes val selectText: Int = 0,
    val type: MovieDetailActionButtonType,
    val isSelected: Boolean = true
) {
    fun getIcon() = (if (isSelected) selectIcon else unSelectIcon) ?: selectIcon
}